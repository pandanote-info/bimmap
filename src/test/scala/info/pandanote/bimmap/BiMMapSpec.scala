package info.pandanote.bimmap

import org.scalatest._
import org.scalatest.matchers.must.Matchers
import org.scalatest.flatspec._
import info.pandanote.bimmap.BiMMap

class BiMMapSpec extends AnyFlatSpec with Matchers {
  val bimmap: BiMMap[Int,Int] = new BiMMap[Int,Int]()
  val bimmap2: BiMMap[String,Int] = new BiMMap[String,Int]()

  "BiMMap" should "say hello" in {
    bimmap += (1 -> 2)
    bimmap.toString() must equal("Map(1 -> 2)")

    bimmap += (2 -> 2)
    bimmap.toString() must equal("Map(1 -> 2)")

    bimmap += (3 -> 1)
    bimmap.toString() must equal("Map(1 -> 2, 3 -> 1)")

    val ibimmap: BiMMap[Int,Int] = bimmap.inverse()
    ibimmap.toString() must equal("Map(1 -> 3, 2 -> 1)")

    bimmap += (4 -> 8)
    bimmap.toString() must equal("Map(1 -> 2, 3 -> 1, 4 -> 8)")
  }

  "BiMMap" should "get key by value" in {
    bimmap.getValue(8) must equal(Some(4))
  }

  "BiMMap" should "handle key as String and value as Int and vice versa (part 1)." in {
    bimmap2 += ("A" -> 10)
    bimmap2 += ("B" -> 52)
    bimmap2 += ("C" -> 130)
    bimmap2.toString() must equal("Map(A -> 10, B -> 52, C -> 130)")
  }

  "BiMMap" should "handle key as String and value as Int and vice versa (part 2)." in {
    val ibimmap2 = bimmap2.inverse()
    ibimmap2.toString() must equal("Map(130 -> C, 52 -> B, 10 -> A)")
  }
}
