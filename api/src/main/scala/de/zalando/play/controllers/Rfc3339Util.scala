package de.zalando.play.controllers

import org.joda.time.format.DateTimeFormat
import org.joda.time.{ DateTime, LocalDate }

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

  private val fullDate = DateTimeFormat.forPattern("yyyy-MM-dd")
  private val shortDateTime = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ssZ")
  private val shortDTWithTicks = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
  private val fullDTWithTicks = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'")
  private val dateTime = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ")

  def parseDateTime(datestring: String): DateTime =
    if (datestring.endsWith("Z") || datestring.endsWith("z")) parseFull(datestring)
    else parseParts(datestring)

  def parseDate(datestring: String): LocalDate =
    fullDate.parseDateTime(datestring).toLocalDate

  def writeDate(date: LocalDate): String = fullDate.print(date)

  def writeDateTime(date: DateTime): String = dateTime.print(date)

  private def parseParts(datestring: String): DateTime = {
    //step one, split off the timezone.
    val sepChar = if (datestring.indexOf('+') > 0) '+' else '-'
    val firstpart = datestring.substring(0, datestring.lastIndexOf(sepChar.toInt))
    val secondpart = datestring.substring(datestring.lastIndexOf(sepChar.toInt))
    //step two, remove the colon from the timezone offset
    val thirdpart = secondpart.substring(0, secondpart.indexOf(':')) + secondpart.substring(secondpart.indexOf(':') + 1)
    val dstring = firstpart + thirdpart
    try {
      shortDateTime.parseDateTime(dstring)
    } catch {
      case pe: IllegalArgumentException => dateTime.parseDateTime(dstring)
    }
  }

  private def parseFull(datestring: String): DateTime = {
    try {
      shortDTWithTicks.parseDateTime(datestring)
    } catch {
      case p: IllegalArgumentException => fullDTWithTicks.parseDateTime(datestring)
    }
  }

}