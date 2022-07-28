import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import com.github.nscala_time.time.Imports._

class DateTimeFactoryTest extends AnyWordSpec with Matchers {
  "A DateTimeFactory" should {
    "create a new date" which {
      "is a DateTime stamp" in {
        DateTimeFactory.create shouldBe a[DateTime]
      }
    }
  }
}