import com.github.nscala_time.time.Imports._

class ReceiptPrinter(
                      val dateTimeFactory: FactoryBase[DateTime] = DateTimeFactory
                    ) extends PrinterBase  {
  def print(order: OrderBase, till: Till): String = {
    val List(name, address, phone) = till.header
    name + "\n" + address + "\n" + phone + "\n" + _receiptTime + "\n" + _orderList(order, till)
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
}