package controllers

import play.api._
import play.api.mvc._

object Data extends Controller {
  
  def search(query: String) = Action {
    	val data = new Data()
    	Ok(data.search(query))
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