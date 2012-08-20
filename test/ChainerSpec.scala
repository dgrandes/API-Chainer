import org.specs2.mutable._
import org.specs2.mock._
import org.mockito.Mockito.doReturn
import play.api.test._
import play.api.test.Helpers._
import controllers._

  class ChainerSpec extends Specification with Mockito {


    "The Chainer.parseChain method" should {
      "return List('') if given: ''" in {
        new Chainer().parseChain("") must beEqualTo(List(""))
      }
      "return List('a') if given: 'a'" in {
        new Chainer().parseChain("a") must beEqualTo(List("a"))
      }
      "return List('a','b') if given: 'a->b'" in {
        new Chainer().parseChain("a->b") must beEqualTo(List("a","b"))
      }
      "return List('a','b','c') if given: 'a->b->c'" in {
        new Chainer().parseChain("a->b->c") must beEqualTo(List("a","b","c"))
      }
    }

    //Mockito Variables
    val m = mock[controllers.Chainer]
    val s = spy(new Chainer())

    //Mocked Responses from the file accessing methods
    doReturn(Option("{\"name\":\"dummy_data_type\"}")).when(s).searchDataType("dummy_data_type")    
    doReturn(None).when(s).searchDataType("unknown")

    "The Chainer.search method" should{
      "call searchDataType in the search method" in {
          val v = () => {
              s.search("dummy_data_type")
              there was one(s).searchDataType("dummy_data_type") 
          }
          v()
      }
      "return error response if data type is not found" in {
          val v = () => {
              s.search("unknown") must contain("unknown data type!")
          }          
          v()
      }

    }
  }