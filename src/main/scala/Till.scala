class Till(
            val cafeDetails: CafeDetails,
            private var order: List[Item] = List()
          ) {

  def addItem(name: String, number: Int): Unit = {
    if (!(cafeDetails.prices contains name)) {
      throw new Exception("Not in menu")
    } else if (!(order.exists(item => item.itemName == name))) {
      order = new Item(name, number) :: order
    } else {
      order = new Item(name, order.filter(item => item.itemName == name)(0).quantity + number) :: order.filter(item => item.itemName != name)
    }
  }

  def checkout(receiptPrinter: ReceiptPrinter): String = {
    receiptPrinter.print(order, cafeDetails)
  }
}