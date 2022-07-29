class Order(val cafe: CafeDetails) {
  private val _header: Map[String, String] = Map(
    "shopName" -> cafe.shopName,
    "address" -> cafe.address,
    "phone" -> cafe.phone,
  )

  private var _items: Map[String, Double] = Map()

  def header: Map[String, String] = _header

  def add(item: String): Map[String, Double] = {
    if (!(cafe.prices contains item)) {
      throw new Exception("Not in menu")
    }
    val numberThisItemInBasket = _items get item
    if (numberThisItemInBasket.isEmpty) {
      _items += (item -> 1)
    } else {
      _items += (item -> (_items(item) + 1))
    }
    _items
  }

  def view: Map[String, Double] = _items

}