import com.github.nscala_time.time.Imports._

class ReceiptPrinter(
                      val dateTimeFactory: FactoryBase[DateTime] = DateTimeFactory
                    ) {

  def print(order: List[Item], cafeDetails: CafeDetails): String = {
    val header = List(cafeDetails.shopName, cafeDetails.address, cafeDetails.phone)
    val List(name, address, phone) = header
    val total = _totalPrice(order, cafeDetails)
    name + "\n" + address + "\n" + phone + "\n" + _receiptTime + "\n" + orderList(order, cafeDetails) + "\n" + _totalPriceFormat(total) + "\n" + _vatFormat(_vatOfTotal(total))
  }

  private def orderList(order: List[Item], cafeDetails: CafeDetails): String = {
    order.map((item: Item) => {
      cafeDetails.prices.get(item.itemName) match {
        case Some(price) => f"${item.quantity} x ${item.itemName}         ${"%1.2f".format(price * item.quantity)}"
//        case None => throw new Exception("Not in menu")
      }
    }).mkString("\n")
  }

  def _receiptTime: String = {
    val dateTimeStamp = dateTimeFactory.create
    val formatter = DateTimeFormat.forPattern("H:m dd-MM-yyy")
    dateTimeStamp.toString(formatter)
  }

  def _totalPrice(order: List[Item], cafeDetails: CafeDetails): Double = {
    order.map((item: Item) => {
      cafeDetails.prices.get(item.itemName) match {
        case Some(price) => price * item.quantity
      }
    }).foldLeft(0.0)((runningTotal: Double, itemSubTotal: Double) => runningTotal + itemSubTotal)
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