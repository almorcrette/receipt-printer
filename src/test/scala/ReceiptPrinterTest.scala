import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalamock.scalatest.MockFactory
import org.scalamock.proxy.ProxyMockFactory
import com.github.nscala_time.time.Imports._

class ReceiptPrinterTest extends AnyWordSpec with Matchers with MockFactory {
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

  "A ReceiptPrinter" should {
    "format a receipt" which {
      "contains the name of the cafe" in {
        val printer = new ReceiptPrinter(
          coffeeConnectionCafe,
          Map("Cafe Latte" -> 1)
        )
        printer.receipt should include ("The Coffee Connection")
      }
      "contains the address of the cafe" in {
        val printer = new ReceiptPrinter(
          coffeeConnectionCafe,
          Map("Cafe Latte" -> 1)

        )
        printer.receipt should include ("123 Lakeside Way")
      }
      "contains the phone number of the cafe" in {
        val printer = new ReceiptPrinter(
          coffeeConnectionCafe,
          Map("Cafe Latte" -> 1)
        )
        printer.receipt should include ("16503600708")
      }

      "contains the time and date receipt is printed" in {
        val mockDateTimeFactory = mock[FactoryBase[DateTime]]
        println(mockDateTimeFactory)
        val mockDateTime = new DateTime(2022, 7,28, 16, 30)
        (mockDateTimeFactory.create _).expects().returning(mockDateTime)

        val printer = new ReceiptPrinter(
          coffeeConnectionCafe,
          Map("Cafe Latte" -> 1),
          mockDateTimeFactory
        )
        printer.receipt should include ("16:30 28-07-2022")
      }
      "contains the item in the order with one item, with the price" in {
        val printer = new ReceiptPrinter(
          coffeeConnectionCafe,
          Map("Cafe Latte" -> 1)
        )
        printer.receipt should include (
          "1 x Cafe Latte         4.75"
        )
      }
      "contains the items in the order, with the price" in {
        val printer = new ReceiptPrinter(
          coffeeConnectionCafe,
          Map("Cafe Latte" -> 1, "Cappuccino" -> 2)
        )
        printer.receipt should include (
          """1 x Cafe Latte         4.75
        |2 x Cappuccino         7.70""".stripMargin
        )
      }
    }
  }
}