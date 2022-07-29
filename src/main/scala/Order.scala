class Order {

  private var _items: Map[String, Double] = Map()

  def add(item: String): Map[String, Double] = {
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