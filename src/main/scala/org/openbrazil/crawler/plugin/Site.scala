package org.openbrazil.crawler.plugin

/**
  * Site data.
  */
case class Site(host: String, uri: String, pluginName: String, encoding: String = "ISO-8859-1") {

  val plugin = Class.forName(pluginName).newInstance().asInstanceOf[Plugin]

  def page(pageNumber: Int = 1) = plugin.page(this, pageNumber)

}