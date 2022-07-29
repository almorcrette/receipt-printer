trait OrderBase {
  def add(item: String): Map[String, Double]
  def items: Map[String, Double]
}