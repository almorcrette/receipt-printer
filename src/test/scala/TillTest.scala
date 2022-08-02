import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalamock.scalatest.MockFactory

class TillTest extends AnyWordSpec with Matchers with MockFactory {
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

  "A Till" should {
    "allows user to update their order" which {
      "raises an error if their request item is not in the menu" in {
        val till = new Till(
          coffeeConnectionCafe
        )
        val thrown = the [Exception] thrownBy {
          till.addItem("Babyccino")
        }
        thrown.getMessage should equal ("Not in menu")
      }
      "add an item to their order if in menu" in {
        val mockOrder = mock[Order]
        (mockOrder.add _).expects("Muffin Of The Day")
        val till = new Till(
          coffeeConnectionCafe,
          mockOrder
        )
        till.addItem("Muffin Of The Day")
      }

    }
    "checkout an order" which {
      "finalise the and print the statement by calling on the receipt printer" in {
        val mockReceiptPrinter = mock[ReceiptPrinter]
        val mockOrder = mock[Order]
        val till = new Till(
          coffeeConnectionCafe,
          mockOrder)
        (mockReceiptPrinter.print _).expects(mockOrder, coffeeConnectionCafe).returning("a receipt")

        till.checkout(mockReceiptPrinter) should equal ("a receipt")
      }
    }
  }
}