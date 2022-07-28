//import com.github.nscala_time.time.Imports._
import java.time.Instant
import java.time.Clock

//class DateFactory() {
//  def dateTimeNow: String = {
//    val dateTimeStamp = DateTime.now()
//    val formatter = DateTimeFormat.forPattern("H:m dd-MM-yyy")
//    dateTimeStamp.toString(formatter)
//  }
//}

class InstantFactory {
  def create(): Instant = {
    Instant.now(Clock.systemUTC())
  }
}