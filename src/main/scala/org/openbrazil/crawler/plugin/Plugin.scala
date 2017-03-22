package org.openbrazil.crawler.plugin

import java.net.URLEncoder
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import akka.http.scaladsl.model.HttpCharsets._
import akka.http.scaladsl.model.HttpMethods._
import akka.http.scaladsl.model.MediaTypes._
import akka.http.scaladsl.model.{HttpEntity, _}
import akka.util.ByteString
import org.openbrazil.crawler.service.HttpClientService

import scala.concurrent.{ExecutionContext, Future}


/**
  * Plugin base class
  */
trait Plugin extends HttpClientService {

  val encoding: String = "ISO-8859-1"

  // TODO use host from class Site
  val host ="portal.marmeleiro.pr.gov.br"

  /**
    * Actual page reader.
    */
  def page(site: Site, pageNumber: Int = 1)(implicit ec: ExecutionContext): Future[HttpResponse] =
    queueRequest(request2(site, pageNumber))

  def urlEncodedForm: String

  def dateEncode(date: LocalDateTime)(implicit formatter: DateTimeFormatter) =
    URLEncoder.encode(date.format(formatter), encoding)

  def request2(site: Site, pageNumber: Int, method: HttpMethod = POST) = {
    val data = ByteString(urlEncodedForm + s"&numpag=$pageNumber")
    val requestEntity:RequestEntity = HttpEntity(ContentType(`application/x-www-form-urlencoded`, `UTF-8`), data)
    HttpRequest().withUri(site.uri).withMethod(method).withEntity(requestEntity)
  }

}
