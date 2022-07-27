class CafeDetails (
                  val shopName: String,
                  val address: String,
                  val phone: String,
                  val prices: Map[String, Double]
                  )

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

class ReceiptPrinter(val cafe: CafeDetails, var order: Map[String, Int] = Map()) {

  /**
   * This method should return a multiline string
   * representing a ReceiptPrinter receipt that should show
   * - shop name, address, phone number
   * - the date and time the receipt was created
   * - each item in the order, with the price. eg:
   *     2 x Blueberry Muffin       8.10
   *     1 x Cappuccino             3.85
   * - the total price
   * - the VAT (20% of total price)
   */
  def receipt: String = {
    cafe.shopName
  }
}