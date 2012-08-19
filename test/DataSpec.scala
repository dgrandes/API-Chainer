import org.specs2.mutable._

import play.api.test._
import play.api.test.Helpers._
import controllers._

  class DataSpec extends Specification {

    "The Data.parseChain method" should {
      "return List('') if given: ''" in {
        new Data().parseChain("") must beEqualTo(List(""))
      }
      "return List('a') if given: 'a'" in {
        new Data().parseChain("a") must beEqualTo(List("a"))
      }
      "return List('a','b') if given: 'a->b'" in {
        new Data().parseChain("a->b") must beEqualTo(List("a","b"))
      }
      "return List('a','b','c') if given: 'a->b->c'" in {
        new Data().parseChain("a->b->c") must beEqualTo(List("a","b","c"))
      }
    }
  }