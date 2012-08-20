package controllers

import play.api._
import play.api.mvc._
import play.api.mvc.Cookies._
import play.api.data._
import play.api.data.Forms._


object Chainer extends Controller {
  
  	val chainer = new Chainer()

  	val chainForm = Form(
	single(
		"link" -> text
		)
	)

	def search() = Action { implicit request => {
	  	val query = Application.queryForm.bindFromRequest.get
	    Ok(chainer.search(query))
	  }
    }

    def chain() = Action { implicit request => {
    		val link = chainForm.bindFromRequest.get
    		val chain = request.cookies.get("chain") match{
    			case Some(cookie) => chainer.parseChain(cookie.value)
    			case None =>  List()
    		}
    		Ok(chainer.chain(chain, link))
    	}

    }
  
  
}

class Chainer {

	val data_path = "app/data/data_types/"
	val api_calls_path = "app/data/api_calls"

	def search(query: String) : String = {
		searchDataType(query) match  {
			case Some(q) => return q
			case None => return Response.error("unknown data type!");
		}
	}

	def searchDataType(query: String) : Option[String] = {
		val filename = data_path+query+".json"
		try{
			return Option(scala.io.Source.fromFile(filename).mkString)
		}catch{
			case fnfe: java.io.FileNotFoundException => return None
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