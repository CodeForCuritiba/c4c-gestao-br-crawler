package org.openbrazil.crawler

import java.io.InputStreamReader

import akka.actor.Actor.Receive
import akka.actor.{Actor, ActorLogging}
import org.ccil.cowan.tagsoup.jaxp.SAXFactoryImpl
import org.xml.sax.InputSource

import scala.xml.parsing.NoBindingFactoryAdapter

/**
  * Created by mauriciofernandesdecastro on 17/03/17.
  */
class DocumentActor extends Actor with ActorLogging {

  override def receive = {
    case (input: InputStreamReader, pageNumber:Int) =>

  }
}