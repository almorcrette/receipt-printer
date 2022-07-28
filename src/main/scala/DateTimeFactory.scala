import com.github.nscala_time.time.Imports._

object DateTimeFactory extends FactoryTrait[DateTime] {
  def create: DateTime = {
    DateTime.now()
  }
}