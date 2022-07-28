import com.github.nscala_time.time.Imports._

class ReceiptPrinter(
                      val cafe: CafeDetails,
                      var order: Map[String, Int] = Map(),
                      val dateTimeFactory: FactoryBase[DateTime] = DateTimeFactory
                    ) extends PrinterBase  {
  def receipt: String = {
    cafe.shopName + "\n" + cafe.address + "\n" + cafe.phone + "\n" + _receiptTime + "\n" + _orderList
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

  def _receiptTime: String = {
    val dateTimeStamp = dateTimeFactory.create
    val formatter = DateTimeFormat.forPattern("H:m dd-MM-yyy")
    dateTimeStamp.toString(formatter)
  }
}