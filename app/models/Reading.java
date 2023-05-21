package models;
import play.db.jpa.Model;
import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Entity
public class Reading extends Model {
    public int code;
    public float temperature;
    public float windSpeed;
    public int pressure;
    public float windDirection;
    public String dateTime;
    public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss", Locale.UK);

    public Reading(String dateTime, int code, float temperature, float windSpeed, float windDirection,int pressure){
        this.code = code;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.pressure = pressure;
        this.windDirection = windDirection;
    }
    public static String parseDateTime(LocalDateTime dateTime){

        if (dateTime != null) {
            return dateTimeFormatter.format(dateTime); //uses your formatter to format the date/time from Yaml file
        } else {
            return dateTimeFormatter.format(LocalDateTime.now()); //Get Current Date Time & Set formatted String
        }
    }
}
