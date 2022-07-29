class Order {

  private var _items: Map[String, Int] = Map()

  def add(item: String): Map[String, Int] = {
    val numberThisItemInBasket = _items get item
    if (numberThisItemInBasket.isEmpty) {
      _items += (item -> 1)
    } else {
      _items += (item -> (_items(item) + 1))
    }
    _items
  }

  def items: Map[String, Int] = _items

}