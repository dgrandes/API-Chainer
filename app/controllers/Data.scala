package controllers

import play.api._
import play.api.mvc._
import play.api.mvc.Cookies._
import play.api.data._
import play.api.data.Forms._


object Data extends Controller {
  
  	val data = new Data()

  	val chainForm = Form(
	single(
		"link" -> text
		)
	)

	def search() = Action { implicit request => {
	  	val query = Application.queryForm.bindFromRequest.get
	    Ok(data.search(query))
	  }
    }

    def chain() = Action { implicit request => {
    		val link = chainForm.bindFromRequest.get
    		val chain = request.cookies.get("chain") match{
    			case Some(cookie) => data.parseChain(cookie.value)
    			case None =>  List()
    		}
    		Ok(data.chain(chain, link))
    	}

    }
  
  
}

class Data {

	val data_path = "app/data/data_types/";

	def search(query: String) : String = {

		val filename = data_path+query+".json"

		try{
			return scala.io.Source.fromFile(filename).mkString
		}catch{
			case fnfe: java.io.FileNotFoundException => return Response.error("unknown data type!");
		}
	}


	def parseChain(chain: String) : List[String] = {
				
		val index = chain.indexOfSlice("->")

		if(index < 0)
			return List(chain)

		return chain.slice(0,index) :: parseChain(chain.slice(index+2, chain.length))
		
	}

	def chain(chain: List[String], link: String ) : String = {
		return "Chained"
	}
}