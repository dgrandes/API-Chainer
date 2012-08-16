package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

object Data extends Controller {
  
	def search() = Action { implicit request => {
	  	val query = Application.queryForm.bindFromRequest.get
	  	val data = new Data()
	    Ok(data.search(query))
	  }
    }
  
  
}

class Data {

	val data_path = "app/data/data_types/";

	def search(query: String) : String = {

		val filename = data_path+query+".json"

		try{
			val fileContent = scala.io.Source.fromFile(filename).mkString	
			return fileContent
		}catch{
			case fnfe: java.io.FileNotFoundException => return Response.error("unknown data type!");
		}
	}
}