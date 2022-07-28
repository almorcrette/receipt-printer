import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalamock.scalatest.MockFactory
import org.scalamock.proxy.ProxyMockFactory
import org.scalatestplus.mockito.MockitoSugar
import com.github.nscala_time.time.Imports._
//import org.mockito.mockito_core.MockitoSugar

class ReceiptPrinterSpec extends AnyWordSpec with Matchers with MockitoSugar with MockFactory with ProxyMockFactory {
  val mockedDateTimeHelper = mock [DateTimeHelper]
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
          Map("Cafe Latte" -> 1),
          mockedDateTimeHelper
        )
        printer.receipt should include ("The Coffee Connection")
      }
      "contains the address of the cafe" in {
        val printer = new ReceiptPrinter(
          coffeeConnectionCafe,
          Map("Cafe Latte" -> 1),
          mockedDateTimeHelper
        )
        printer.receipt should include ("123 Lakeside Way")
      }
      "contains the phone number of the cafe" in {
        val printer = new ReceiptPrinter(
          coffeeConnectionCafe,
          Map("Cafe Latte" -> 1),
          mockedDateTimeHelper
        )
        printer.receipt should include ("16503600708")
      }
      "contains the date and time of the receipt" in {
        val printer = new ReceiptPrinter(
          coffeeConnectionCafe,
          Map("Cafe Latte" -> 1),
          mockedDateTimeHelper
        )
        when(mockedDateTimeHelper.dateTimeNow) thenReturn ("some date and time")

        printer.receipt should include ("15:30 28/07/2022")
      }
      "contains the item in the order with one item, with the price" in {
        val printer = new ReceiptPrinter(
          coffeeConnectionCafe,
          Map("Cafe Latte" -> 1),
          mockedDateTimeHelper
        )
        printer.receipt should include (
          "1 x Cafe Latte         4.75"
        )
      }
      "contains the items in the order, with the price" in {
        val printer = new ReceiptPrinter(
          coffeeConnectionCafe,
          Map("Cafe Latte" -> 1, "Cappuccino" -> 2),
          mockedDateTimeHelper
        )
        printer.receipt should include (
          """1 x Cafe Latte         4.75
        |2 x Cappuccino         7.70""".stripMargin
        )
      }
    }
  }
}