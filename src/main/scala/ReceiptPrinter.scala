import com.github.nscala_time.time.Imports._

import java.time.Instant
// need to import the

class ReceiptPrinter(
                      val cafe: CafeDetails,
                      var order: Map[String, Int] = Map(),
                      val dateFactory: DateFactory = DateFactory
                    ) {
  /**
   * This method should return a multiline string
   * representing a ReceiptPrinter receipt that should show
   * - shop name, address, phone number
   * - the date and time the receipt was created
   * - each item in the order, with the price. eg:
   *     2 x Blueberry Muffin       8.10
   *     1 x Cappuccino             3.85
   * - the total price
   * - the VAT (20% of total price)
   */
  def receipt: String = {
//    println(    cafe.shopName + "\n" + cafe.address + "\n" + cafe.phone + "\n" + receiptTime + "\n" + orderList)
    cafe.shopName + "\n" + cafe.address + "\n" + cafe.phone + "\n" + formatInstant(instant) + "\n" + _orderList
  }

  def _orderList: String = {
    val itemIterator = order.keysIterator
    val amountIterator = order.valuesIterator
    var list = ""
    while (itemIterator.hasNext) {
      var currentItem = itemIterator.next
      var currentItemAmount = amountIterator.next
      list = list + f"\n${currentItemAmount} x $currentItem         ${("%1.2f".format(cafe.prices(currentItem) * currentItemAmount))}"
    }
    list
  }

  def formatInstant = (instant: Instant): String => {
    val formatter = DateTimeFormatter.ofPattern("H:m dd-MM-yyy").withZone(ZoneId.system...)
    formatter.format(instant)
  }
}