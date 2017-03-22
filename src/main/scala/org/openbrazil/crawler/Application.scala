package org.openbrazil.crawler

import akka.actor.{ActorSystem, Props}
import org.openbrazil.crawler.plugin.Site

/**
  * Web crawler
  */
object Application extends App {

  val system = ActorSystem("gestao-br")

  val readerActor = system.actorOf(Props[ReaderActor], "reader")

  val crawlerActor = system.actorOf(Props(new CrawlerActor(readerActor)), "crawler")

  // TODO ler sites da configuração

  // val sites = pureconfig.loadConfigOrThrow("sites")

  val marmeleiro = Site("portal.marmeleiro.pr.gov.br", "/pronimtb/index.asp?acao=1&item=2", "org.openbrazil.crawler.plugin.PronimPlugin")

  val sites = Array(marmeleiro)

  sites.foreach(crawlerActor ! _)

//  system.terminate()

}
