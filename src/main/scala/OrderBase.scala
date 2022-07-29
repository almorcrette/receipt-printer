trait OrderBase {
  def add(item: String): Map[String, Int]
  def items: Map[String, Int]
}