import com.github.nscala_time.time.Imports._

class ReceiptPrinter(
                      val dateTimeFactory: FactoryBase[DateTime] = DateTimeFactory
                    ) extends PrinterBase  {
  def print(order: OrderBase, till: Till): String = {
    val List(name, address, phone) = till.header
    val total = _totalPrice(order, till)
    name + "\n" + address + "\n" + phone + "\n" + _receiptTime + "\n" + _orderList(order, till) + "\n" + _totalPriceFormat(total) + "\n" + _vatFormat(_vatOfTotal(total))
  }

  def _orderList(order: OrderBase, till: Till): String = {
    val itemIterator = order.items.keysIterator
    val amountIterator = order.items.valuesIterator
    var list = ""
    while (itemIterator.hasNext) {
      var currentItem = itemIterator.next
      var currentItemAmount = amountIterator.next.toInt
      list = list + f"\n${currentItemAmount} x $currentItem         ${("%1.2f".format(till.cafePrices(currentItem) * currentItemAmount))}"
    }
    list
  }

  def _receiptTime: String = {
    val dateTimeStamp = dateTimeFactory.create
    val formatter = DateTimeFormat.forPattern("H:m dd-MM-yyy")
    dateTimeStamp.toString(formatter)
  }

  def _totalPrice(order: OrderBase, till: Till): Double = {
    val itemIterator = order.items.keysIterator
    val amountIterator = order.items.valuesIterator
    var total = 0.0
    while (itemIterator.hasNext) {
      var currentItem = itemIterator.next
      var currentItemAmount = amountIterator.next.toInt
      total += till.cafePrices(currentItem) * currentItemAmount
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