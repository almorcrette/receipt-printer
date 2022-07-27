class Till(val cafe: CafeDetails) {

  private var _order: Map[String, Int] = Map()

  private var _receiptPrinter: ReceiptPrinter = new ReceiptPrinter(cafe, _order)

  def order = _order

  def cafePrices: Map[String, Double] = {
    cafe.prices
  }

  def order_=(item: String): Unit = {
    if ((cafe.prices get item) == None) {
      throw new Exception("Not in menu")
    }
    val numberThisItemInBasket = order get item
    if (numberThisItemInBasket == None) {
      _order += (item -> 1)
    } else {
      _order += (item -> (order(item) + 1))
    }
  }

  def checkout: String = {
    _receiptPrinter.receipt
  }
}