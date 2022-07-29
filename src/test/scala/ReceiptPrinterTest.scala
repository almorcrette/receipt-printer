import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalamock.scalatest.MockFactory
import com.github.nscala_time.time.Imports._

class ReceiptPrinterTest extends AnyWordSpec with Matchers with MockFactory {
  "A ReceiptPrinter" should {
    "prints a formatted receipt" which {
      "contains the name of the cafe" in {
        val mockOrder = mock[OrderBase]
        val mockTill = mock[Till]
        (mockTill.header _).expects().returns(List("The Coffee Connection", "123 Lakeside Way", "16503600708"))
        (mockTill.cafePrices _).expects().anyNumberOfTimes().returns(Map(
          "Cafe Latte" -> 4.75,
          "Flat White" -> 4.75,
          "Cappuccino" -> 3.85
        ))
        (mockOrder.items _).expects().anyNumberOfTimes().returns(Map("Cappuccino" -> 1, "Cafe Latte" -> 2))

        val receiptPrinter = new ReceiptPrinter
        receiptPrinter.print(mockOrder, mockTill) should include ("The Coffee Connection")
      }
      "contains the address of the cafe" in {
        val mockOrder = mock[OrderBase]
        val mockTill = mock[Till]
        (mockTill.header _).expects().returns(List("The Coffee Connection", "123 Lakeside Way", "16503600708"))
        (mockTill.cafePrices _).expects().anyNumberOfTimes().returns(Map(
          "Cafe Latte" -> 4.75,
          "Flat White" -> 4.75,
          "Cappuccino" -> 3.85
        ))
        (mockOrder.items _).expects().anyNumberOfTimes().returns(Map("Cappuccino" -> 1, "Cafe Latte" -> 2))

        val receiptPrinter = new ReceiptPrinter
        receiptPrinter.print(mockOrder, mockTill) should include ("123 Lakeside Way")
      }
      "contains the phone number of the cafe" in {
        val mockOrder = mock[OrderBase]
        val mockTill = mock[Till]
        (mockTill.header _).expects().returns(List("The Coffee Connection", "123 Lakeside Way", "16503600708"))
        (mockTill.cafePrices _).expects().anyNumberOfTimes().returns(Map(
          "Cafe Latte" -> 4.75,
          "Flat White" -> 4.75,
          "Cappuccino" -> 3.85
        ))

        (mockOrder.items _).expects().anyNumberOfTimes().returns(Map("Cappuccino" -> 1, "Cafe Latte" -> 2))

        val receiptPrinter = new ReceiptPrinter
        receiptPrinter.print(mockOrder, mockTill) should include ("16503600708")
      }

      "contains the time and date receipt is printed" in {
        val mockDateTimeFactory = mock[FactoryBase[DateTime]]
        val mockDateTime = new DateTime(2022, 7,28, 16, 30)
        (mockDateTimeFactory.create _).expects().returning(mockDateTime)

        val mockOrder = mock[OrderBase]
        val mockTill = mock[Till]
        (mockTill.header _).expects().returns(List("The Coffee Connection", "123 Lakeside Way", "16503600708"))
        (mockTill.cafePrices _).expects().anyNumberOfTimes().returns(Map(
          "Cafe Latte" -> 4.75,
          "Flat White" -> 4.75,
          "Cappuccino" -> 3.85
        ))
        (mockOrder.items _).expects().anyNumberOfTimes().returns(Map("Cappuccino" -> 1, "Cafe Latte" -> 2))

        val receiptPrinter = new ReceiptPrinter(
          mockDateTimeFactory
        )
        receiptPrinter.print(mockOrder, mockTill) should include ("16:30 28-07-2022")
      }
      "contains the item in the order with one item, with the price" in {
        val mockOrder = mock[OrderBase]
        val mockTill = mock[Till]
        (mockTill.header _).expects().returns(List("The Coffee Connection", "123 Lakeside Way", "16503600708"))
        (mockTill.cafePrices _).expects().anyNumberOfTimes().returns(Map(
          "Cafe Latte" -> 4.75,
          "Flat White" -> 4.75,
          "Cappuccino" -> 3.85
        ))
        (mockOrder.items _).expects().anyNumberOfTimes().returns(Map("Cappuccino" -> 1, "Cafe Latte" -> 1))

        val receiptPrinter = new ReceiptPrinter(
        )

        receiptPrinter.print(mockOrder, mockTill) should include (
          "1 x Cafe Latte         4.75"
        )
      }
      "contains the items in the order, with the price" in {
        val mockOrder = mock[OrderBase]
        val mockTill = mock[Till]
        (mockTill.header _).expects().returns(List("The Coffee Connection", "123 Lakeside Way", "16503600708"))
        (mockTill.cafePrices _).expects().anyNumberOfTimes().returns(Map(
          "Cafe Latte" -> 4.75,
          "Flat White" -> 4.75,
          "Cappuccino" -> 3.85
        ))

        (mockOrder.items _).expects().anyNumberOfTimes().returns(Map("Cappuccino" -> 2, "Cafe Latte" -> 1))

        val receiptPrinter = new ReceiptPrinter(
        )
        receiptPrinter.print(mockOrder, mockTill) should include (
          """2 x Cappuccino         7.70
        |1 x Cafe Latte         4.75""".stripMargin
        )
      }
      "contains the total price of the order" in {
        val mockOrder = mock[OrderBase]
        val mockTill = mock[Till]

        (mockTill.header _).expects().returns(List("The Coffee Connection", "123 Lakeside Way", "16503600708"))
        (mockTill.cafePrices _).expects().anyNumberOfTimes().returns(Map(
          "Cafe Latte" -> 4.75,
          "Flat White" -> 4.75,
          "Cappuccino" -> 3.85
        ))

        (mockOrder.items _).expects().anyNumberOfTimes().returns(Map("Cappuccino" -> 2, "Cafe Latte" -> 1))

        val receiptPrinter = new ReceiptPrinter(
        )
        receiptPrinter.print(mockOrder, mockTill) should include (
          "Total:         12.45"
        )
      }
      "contains the VAT for the order" in {
        val mockOrder = mock[OrderBase]
        val mockTill = mock[Till]

        (mockTill.header _).expects().returns(List("The Coffee Connection", "123 Lakeside Way", "16503600708"))
        (mockTill.cafePrices _).expects().anyNumberOfTimes().returns(Map(
          "Cafe Latte" -> 4.75,
          "Flat White" -> 4.75,
          "Cappuccino" -> 3.85
        ))
        (mockOrder.items _).expects().anyNumberOfTimes().returns(Map("Cappuccino" -> 2, "Cafe Latte" -> 1))

        val receiptPrinter = new ReceiptPrinter(
        )
        receiptPrinter.print(mockOrder, mockTill) should include (
          "VAT:         2.49"
        )
      }
    }
  }
}