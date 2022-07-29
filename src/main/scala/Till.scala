class Till(
            val cafeDetails: CafeDetails,
            val _order: OrderBase = new Order,
            val _receiptPrinter: PrinterBase = new ReceiptPrinter
          ) {

//  private val _receiptPrinter: ReceiptPrinter = new ReceiptPrinter(cafeDetails, _order)

  def header: List[String] = List(cafeDetails.shopName, cafeDetails.address, cafeDetails.phone)

  def order: OrderBase = _order

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