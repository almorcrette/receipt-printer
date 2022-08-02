import com.github.nscala_time.time.Imports._

class ReceiptPrinter(
                      val dateTimeFactory: FactoryBase[DateTime] = DateTimeFactory
                    ) {

  def print(order: Order, cafeDetails: CafeDetails): String = {
    val header = List(cafeDetails.shopName, cafeDetails.address, cafeDetails.phone)
    val List(name, address, phone) = header
    val total = _totalPrice(order, cafeDetails)
    name + "\n" + address + "\n" + phone + "\n" + _receiptTime + "\n" + _orderList(order, cafeDetails) + "\n" + _totalPriceFormat(total) + "\n" + _vatFormat(_vatOfTotal(total))
  }

  def _orderList(order: Order, cafeDetails: CafeDetails): String = {
    val itemIterator = order.items.keysIterator
    val amountIterator = order.items.valuesIterator
    var list = ""
    val cafePrices = cafeDetails.prices
    while (itemIterator.hasNext) {
      val currentItem = itemIterator.next
      val currentItemAmount = amountIterator.next
      list = list + f"\n$currentItemAmount x $currentItem         ${"%1.2f".format(cafePrices(currentItem) * currentItemAmount)}"
    }
    list
  }

  def _receiptTime: String = {
    val dateTimeStamp = dateTimeFactory.create
    val formatter = DateTimeFormat.forPattern("H:m dd-MM-yyy")
    dateTimeStamp.toString(formatter)
  }

  def _totalPrice(order: Order, cafeDetails: CafeDetails): Double = {
    val itemIterator = order.items.keysIterator
    val amountIterator = order.items.valuesIterator
    var total = 0.0
    val cafePrices = cafeDetails.prices
    while (itemIterator.hasNext) {
      val currentItem = itemIterator.next
      val currentItemAmount: Int = amountIterator.next
      total += cafePrices(currentItem) * currentItemAmount
    }
    total
  }

  def _totalPriceFormat(total: Double): String = {
    f"Total:         $total"
  }

  def _vatOfTotal(total: Double): Double = {
    BigDecimal(total / 5).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble
  }

  def _vatFormat(vat: Double): String = {
    f"VAT:         $vat"
  }
}