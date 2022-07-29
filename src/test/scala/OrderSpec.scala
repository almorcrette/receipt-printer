import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class OrderSpec extends AnyWordSpec with Matchers {

  "An Order" should {
    "be able to add items to it" which {
      "manages different menu items" in {
        val order = new Order
        order.add("Cafe Latte")
        order.add("Cappuccino")
        order.items shouldEqual Map(
          "Cafe Latte" -> 1,
          "Cappuccino" -> 1
        )
      }
      "manages multiples of a single menu item" in {
        val order = new Order
        order.add("Cafe Latte")
        order.add("Cafe Latte")
        order.items shouldEqual Map(
          "Cafe Latte" -> 2
        )
      }
    }
  }
}