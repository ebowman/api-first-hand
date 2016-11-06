package de.zalando.play.controllers

import java.time.format.{ DateTimeFormatter, DateTimeParseException }
import java.time.{ LocalDate, ZoneId, ZonedDateTime }

/**
 * An utility class for parsing date and date-time inputs as required by RFC3339
 * Based on work done by Chad Okere
 * Needed to do a manual parsing because Joda Time only supports ISO8601 formats
 * which is not completely interchangeable with RFC3339
 *
 * As we need different types for Dates and DateTimes for implicit conversions to work,
 * deliberately using LocalDate here.
 *
 * @author slasch
 * @since 04.01.2016.
 */
object Rfc3339Util {

  private val fullDate = DateTimeFormatter.ofPattern("yyyy-MM-dd")
  private val shortDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ")
  private val shortDTWithTicks = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
  private val fullDTWithTicks = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSS'Z'")
  private val dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSZ")

  def parseDateTime(datestring: String): ZonedDateTime =
    if (datestring.endsWith("Z") || datestring.endsWith("z")) parseFull(datestring)
    else parseParts(datestring)

  def parseDate(datestring: String): LocalDate =
    LocalDate.parse(datestring, fullDate)

  def writeDate(date: LocalDate): String = fullDate.format(date)

  def writeDateTime(date: ZonedDateTime): String = dateTime.format(date)

  private def parseParts(datestring: String): ZonedDateTime = {
    //step one, split off the timezone.
    val sepChar = if (datestring.indexOf('+') > 0) '+' else '-'
    val firstpart = datestring.substring(0, datestring.lastIndexOf(sepChar.toInt))
    val secondpart = datestring.substring(datestring.lastIndexOf(sepChar.toInt))
    //step two, remove the colon from the timezone offset
    val thirdpart = secondpart.substring(0, secondpart.indexOf(':')) + secondpart.substring(secondpart.indexOf(':') + 1)
    val dstring = firstpart + thirdpart
    try {
      ZonedDateTime.parse(dstring, shortDateTime)
    } catch {
      case pe: DateTimeParseException =>
        ZonedDateTime.parse(dstring, dateTime)
    }
  }

  private def parseFull(datestring: String): ZonedDateTime = {
    val z = ZoneId.systemDefault()
    try {
      ZonedDateTime.parse(datestring, shortDTWithTicks.withZone(z))
    } catch {
      case p: DateTimeParseException => ZonedDateTime.parse(datestring, fullDTWithTicks.withZone(z))
    }
  }

}