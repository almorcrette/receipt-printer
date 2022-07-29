class Till(
            val cafeDetails: CafeDetails,
            val _order: Order = new Order
          ) {

//  private val _receiptPrinter: ReceiptPrinter = new ReceiptPrinter(cafeDetails, _order)

  def order: Order = _order

  def cafePrices: Map[String, Double] = {
    cafeDetails.prices
  }

  def order_=(item: String): Unit = {
    if (!(cafeDetails.prices contains item)) {
      throw new Exception("Not in menu")
    }
    _order.add(item)
  }

//  def checkout: Unit = {
//    _receiptPrinter.receipt
//  }
}