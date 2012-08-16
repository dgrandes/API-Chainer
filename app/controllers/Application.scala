package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

object Application extends Controller {
  
  val queryForm = Form(
  	single(
  		"query" -> text
  		)
  )

  def index = Action {
    Ok(views.html.index(queryForm))
  }
  
}