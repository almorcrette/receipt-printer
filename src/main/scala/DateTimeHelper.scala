import com.github.nscala_time.time.Imports._

class DateTimeHelper() {
  def dateTimeNow: String = {
    val dateTimeStamp = DateTime.now()
    val formatter = DateTimeFormat.forPattern("H:m dd-MM-yyy")
    dateTimeStamp.toString(formatter)
  }
}