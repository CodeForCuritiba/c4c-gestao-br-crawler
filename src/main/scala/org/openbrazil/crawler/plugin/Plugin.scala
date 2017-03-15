package org.openbrazil.crawler.plugin

import java.io.{ByteArrayInputStream, InputStreamReader}
import java.net.URLEncoder
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpCharsets._
import akka.http.scaladsl.model.HttpMethods._
import akka.http.scaladsl.model.MediaTypes._
import akka.http.scaladsl.model.{ContentType, HttpEntity, HttpRequest, RequestEntity}
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Sink, Source}
import akka.util.ByteString

import scala.concurrent.Future
import scala.concurrent.duration.FiniteDuration

/**
  * Plugin base class
  */
trait Plugin {

  implicit val materializer = ActorMaterializer()

  /**
    * Actual page reader.
    */
  def page(site: Site, pageNumber: Int = 1): Future[(InputStreamReader, Int)] = {
    Source
      .single(request(site.uri, urlEncodedForm, pageNumber))
      .via(Http().outgoingConnection(site.host))
      .runWith(Sink.head)
      .flatMap( response =>
      response.entity.toStrict(FiniteDuration(120, "seconds")) collect {
        case entity =>
          (new InputStreamReader(
            new ByteArrayInputStream(entity.data.decodeString(site.encoding).getBytes)), pageNumber)
      }
    )
  }

  def urlEncodedForm: String

  def dateEncode(date: LocalDateTime)(implicit formatter: DateTimeFormatter, encoding: String) = URLEncoder.encode(date.format(formatter), encoding)

  private[plugin] def request(uri: String, urlEncodedForm: String, pageNumber: Int) = {
    val data = ByteString(urlEncodedForm + s"&numpag=$pageNumber")
    val requestEntity:RequestEntity = HttpEntity(ContentType(`application/x-www-form-urlencoded`, `UTF-8`), data)
    HttpRequest().withUri(uri).withMethod(POST).withEntity(requestEntity)
  }

}
