class Till(
            var cafeDetails: CafeDetails,
            private val order: Order = new Order
          ) {

  def addItem(item: String): Unit = {
    if (!(cafeDetails.prices contains item)) {
      throw new Exception("Not in menu")
    }
    order.add(item)
  }

  def checkout(receiptPrinter: ReceiptPrinter): String = {
    receiptPrinter.print(order, cafeDetails)
  }
}