import com.github.nscala_time.time.Imports._

object DateTimeFactory extends FactoryBase[DateTime] {
  def create: DateTime = {
    DateTime.now()
  }
}