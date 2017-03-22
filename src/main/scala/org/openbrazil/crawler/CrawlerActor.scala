package org.openbrazil.crawler

import java.io.{ByteArrayInputStream, InputStreamReader, StringReader}

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpResponse, StatusCodes}
import akka.pattern.pipe
import akka.stream.{ActorMaterializer, ActorMaterializerSettings}
import akka.util.ByteString
import org.ccil.cowan.tagsoup.jaxp.SAXFactoryImpl
import org.openbrazil.crawler.plugin.{PronimPlugin, Site}
import org.xml.sax.InputSource

import scala.xml.Node
import scala.xml.parsing.NoBindingFactoryAdapter

/**
  * Crawler actor
  */
class CrawlerActor(readerActor: ActorRef) extends Actor with ActorLogging {

  final implicit val materializer: ActorMaterializer = ActorMaterializer(ActorMaterializerSettings(context.system))
  implicit val ec = context.dispatcher

  import CrawlerActor._

  override def receive = {
    case site: Site =>
      log.debug(s"Reading page from $site")
      val plugin = PronimPlugin()
      plugin.page(site, 1) pipeTo self
    case HttpResponse(StatusCodes.OK, headers, entity, _) =>
      entity.dataBytes.runFold(ByteString(""))(_ ++ _).foreach { body: ByteString =>
        self ! body
//
//        val divs = (adapter.loadXML(new InputSource(new StringReader(body.utf8String)), parser) \\ "div").filter(t => (t.attribute("id").map(_.text)).getOrElse("") == "conteudo")
////        val tables = (div.child)
//        divs.foreach(println(_))

      }
    case resp @ HttpResponse(code, _, _, _) =>
      log.info("Request failed, response code: " + code)
      resp.discardEntityBytes()
    case body: ByteString =>
      log.info("Got response, body: " + body.utf8String)
    case (input: InputStreamReader, pageNumber:Int) =>
      log.debug(s"Parsing page from")
      val divs = adapter.loadXML(new InputSource(input), parser) \\ "div"
//      val div: Node = divs.filter(t => (t.attribute("id").map(_.text)).getOrElse("") == "conteudo").head
//      val tables = (div.child)
//      tables.foreach(println(_))
      println(divs)

  }
}
object CrawlerActor {

  lazy val adapter = new NoBindingFactoryAdapter
  lazy val parser = (new SAXFactoryImpl).newSAXParser

}