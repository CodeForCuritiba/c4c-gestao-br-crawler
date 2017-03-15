package org.openbrazil.crawler

import akka.actor.{Actor, ActorLogging, ActorRef}
import org.openbrazil.crawler.plugin.{Plugin, Site}

/**
  * Crawler actor
  */
class CrawlerActor(readerActor: ActorRef) extends Actor with ActorLogging {

  override def receive = {
    case site: Site =>
//      plugin.page()
  }

}
