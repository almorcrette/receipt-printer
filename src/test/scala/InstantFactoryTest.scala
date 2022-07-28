import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec


class InstantFactoryTest extends AnyWordSpec with Matchers {
  "A DateFactory" should {
    "create a new date" which {
      "is an instant" in {
        DateFactory.create shouldBe a[Instant]

      }
    }
  }
}