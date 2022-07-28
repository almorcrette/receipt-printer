class Till(val cafe: CafeDetails) {

  private var _order: Map[String, Int] = Map()

  private val _receiptPrinter: ReceiptPrinter = new ReceiptPrinter(cafe, _order)

  def order: Map[String, Int] = _order

  def cafePrices: Map[String, Double] = {
    cafe.prices
  }

  def order_=(item: String): Unit = {
    if (!(cafe.prices contains item)) {
      throw new Exception("Not in menu")
    }
    val numberThisItemInBasket = order get item
    if (numberThisItemInBasket.isEmpty) {
      _order += (item -> 1)
    } else {
      _order += (item -> (order(item) + 1))
    }
  }

  def checkout(): Unit = {
    _receiptPrinter.receipt
  }
}