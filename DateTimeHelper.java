package cscm12.cafe94;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/** For ease of use with handling date time variables.
 * @author Paul Norman
 * @version 1.0
 */
public class DateTimeHelper {


    /** For ease of use when using frontend input forms.
     * @param year Input containing year.
     * @param month Input containing month.
     * @param day Input containing day.
     * @param hour Input containing hour.
     * @param minute Input containing minute.
     * @return Variable compatible with LocalDateTime object.
     */
    public static LocalDateTime convert(int year, int month, int day, int hour, int minute){
        return LocalDateTime.of(year, month, day, hour, minute, 0);
    }

    /** Converts LocalDateTime to a string of the date.
     * @param date LocalDateTime object to print.
     * @return String of date.
     */
    public String printDate(LocalDateTime date){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = date.format(format);
        return formattedDate;
    }

    /** Converts LocalDateTime to a string of the time.
     * @param time LocalDateTime object to print.
     * @return String of time.
     */
    public String printTime(LocalDateTime time){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");
        String formattedTime = time.format(format);
        return formattedTime;
    }
}
