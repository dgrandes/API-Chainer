package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

val chainForm = Form(
	single(
		"link" -> text
		)
	)
object Data extends Controller {
  
  	val data = new Data()
	def search() = Action { implicit request => {
	  	val query = Application.queryForm.bindFromRequest.get
	    Ok(data.search(query))
	  }
    }

    def chain() = Action { implicit => {
    		val link = chainForm.bindFromRequest.get
    		Ok(data.chain(link))
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

	def chain(link: String) = {
		val cookie :Option[Cookie] = Cookies.get("chain")
	}
}