class Till(
            val cafeDetails: CafeDetails,
            val _order: Order = new Order,
            val _receiptPrinter: ReceiptPrinter = new ReceiptPrinter
          ) {

  def header: List[String] = List(cafeDetails.shopName, cafeDetails.address, cafeDetails.phone)

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

  def checkout: String = {
    _receiptPrinter.print(order, this)
  }
}