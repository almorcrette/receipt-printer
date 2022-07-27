import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalamock.scalatest.MockFactory
import org.scalamock.proxy.ProxyMockFactory

class TillSpec extends AnyWordSpec with Matchers with MockFactory with ProxyMockFactory {
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
    "show the menu" which {
      "contains cafe menu items and prices" in {
        val till = new Till(
          coffeeConnectionCafe
        )
        till.cafePrices shouldEqual coffeeConnectionCafe.prices
      }
    }
    "allows user to update their order" which {
      "add a first item to their order if in menu" in {
        val till = new Till(
          coffeeConnectionCafe
        )
        till.order_=("Muffin Of The Day")
        till.order shouldEqual Map("Muffin Of The Day" -> 1)
      }
      "add two different items to their empty order if in menu" in {
        val till = new Till(
          coffeeConnectionCafe
        )
        till.order_=("Cappuccino")
        till.order_=("Muffin Of The Day")
        till.order shouldEqual Map(
          "Cappuccino" -> 1,
          "Muffin Of The Day" -> 1
        )
      }
      "add two of the same item to their empty order if in menu" in {
        val till = new Till(
          coffeeConnectionCafe
        )
        till.order_=("Cappuccino")
        till.order_=("Cappuccino")
        till.order shouldEqual Map(
          "Cappuccino" -> 2
        )
      }
      "add three of the same item to their empty order if in menu" in {
        val till = new Till(
          coffeeConnectionCafe
        )
        till.order_=("Cappuccino")
        till.order_=("Cappuccino")
        till.order_=("Cappuccino")
        till.order shouldEqual Map(
          "Cappuccino" -> 3
        )
      }
      "add multiple items, some multiple times, some once to their empty order all in menu" in {
        val till = new Till(
          coffeeConnectionCafe
        )
        till.order_=("Cappuccino")
        till.order_=("Muffin Of The Day")
        till.order_=("Cappuccino")
        till.order shouldEqual Map(
          "Cappuccino" -> 2,
          "Muffin Of The Day" -> 1
        )
      }
      "raises an error if their request item is not in the menu" in {
        val till = new Till(
          coffeeConnectionCafe
        )
        val thrown = the [Exception] thrownBy {
          till.order_=("Babyccino")
        }
        thrown.getMessage should equal ("Not in menu")
      }
    }
    "checkout an order" which {
      "finalise the and print the statement by calling on the receipt printer" in {
        //        val receiptPrinterMock = mock[ReceiptPrinter]
        val till = new Till(coffeeConnectionCafe)
        //        (receiptPrinterMock.receipt _).expects()
        till.order_=("Cappuccino")
        till.order_=("Muffin Of The Day")
        till.order_=("Cappuccino")
//        till.checkout should include ("The Coffee Connection")
      }
    }
  }
}