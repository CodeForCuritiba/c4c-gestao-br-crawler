package org.openbrazil.crawler

import akka.actor.{ActorSystem, Props}

/**
  * Web crawler
  */
object Application extends App {

  val system = ActorSystem("gestao-br")

  val readerActor = system.actorOf(Props[ReaderActor], "reader")

  val crawlerActor = system.actorOf(Props(new CrawlerActor(readerActor)), "crawler")

  val sites = pureconfig.loadConfigOrThrow("sites")

  system.terminate()

}
