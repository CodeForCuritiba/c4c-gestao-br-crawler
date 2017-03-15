package org.openbrazil.crawler

import akka.actor.{Actor, ActorLogging}
import technology.tabula.{RectangularTextContainer, Table, TextElement}

/**
  * Extractor actor.
  */
class ExtractorActor extends Actor with ActorLogging {

  import collection.JavaConverters._

  override def receive = {
    case t:Table =>
      for {
        row <- t.getRows.asScala
        cell <- row.asScala
        c = cell.asInstanceOf[RectangularTextContainer[TextElement]]
        if c.getText.nonEmpty
      } println(c.getText)
  }

}
