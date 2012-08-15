package controllers

import play.api._
import play.api.mvc._

object Response extends Controller {
  
  def error(message: String) :String = {
    	"{\"error\":true,\"message\":\""+message+"\"}"
  }
  
}
