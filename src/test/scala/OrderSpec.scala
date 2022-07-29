import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class OrderSpec extends AnyWordSpec with Matchers {
  val coffeeConnectionCafe = new CafeDetails(
    "The Coffee Connection",
    "123 Lakeside Way",
    "16503600708",
    Map(
      "Cafe Latte" -> 4.75,
      "Flat White" -> 4.75,
      "Cappuccino" -> 3.85,
      "Single Espresso" -> 2.05,
      "Double Espresso" -> 3.75,
      "Americano" -> 3.75,
      "Cortado" -> 4.55,
      "Tea" -> 3.65,
      "Choc Mudcake" -> 6.40,
      "Choc Mousse" -> 8.20,
      "Affogato" -> 14.80,
      "Tiramisu" -> 11.40,
      "Blueberry Muffin" -> 4.05,
      "Chocolate Chip Muffin" -> 4.05,
      "Muffin Of The Day" -> 4.55
    )
  )
  "An Order" should {
    "be created with a header" which {
      "contains the name of the shop" in {
        val order = new Order(coffeeConnectionCafe)
        order.header("shopName") shouldEqual "The Coffee Connection"
      }
      "contains the address of the shop" in {
        val order = new Order(coffeeConnectionCafe)
        order.header("address") shouldEqual "123 Lakeside Way"
      }
      "contains the phone number of the shop" in {
        val order = new Order(coffeeConnectionCafe)
        order.header("phone") shouldEqual "16503600708"
      }
    }
    "be able to add items to it" which {
      "manages different menu items" in {
        val order = new Order(coffeeConnectionCafe)
        order.add("Cafe Latte")
        order.add("Cappuccino")
        order.view shouldEqual Map(
          "Cafe Latte" -> 1,
          "Cappuccino" -> 1
        )
      }
//      "manages multiples of a single menu item" in {
//
//      }
    }
  }
}