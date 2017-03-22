package org.openbrazil.crawler.service

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpRequest, HttpResponse}
import akka.stream.{ActorMaterializer, OverflowStrategy, QueueOfferResult}
import akka.stream.scaladsl.{Keep, Sink, Source}

import scala.concurrent.{Future, Promise}
import scala.util.{Failure, Success}

/**
  * Akka http client service.
  */
trait HttpClientService {

  implicit val system = ActorSystem()
  import system.dispatcher // to get an implicit ExecutionContext into scope
  implicit val materializer = ActorMaterializer()

  val QueueSize = 10

  val host: String

  // TODO substituir string de host por variável.
  val poolClientFlow = Http().cachedHostConnectionPool[Promise[HttpResponse]]("portal.marmeleiro.pr.gov.br")

  val queue =
    Source.queue[(HttpRequest, Promise[HttpResponse])](QueueSize, OverflowStrategy.dropNew)
      .via(poolClientFlow)
      .toMat(Sink.foreach({
        case ((Success(resp), p)) => p.success(resp)
        case ((Failure(e), p))    => p.failure(e)
      }))(Keep.left)
      .run()

  def queueRequest(request: HttpRequest): Future[HttpResponse] = {
    val responsePromise = Promise[HttpResponse]()
    queue.offer(request -> responsePromise).flatMap {
      case QueueOfferResult.Enqueued    => responsePromise.future
      case QueueOfferResult.Dropped     => Future.failed(new RuntimeException("Queue overflowed. Try again later."))
      case QueueOfferResult.Failure(ex) => Future.failed(ex)
      case QueueOfferResult.QueueClosed => Future.failed(new RuntimeException("Queue was closed (pool shut down) while running the request. Try again later."))
    }
  }

}
