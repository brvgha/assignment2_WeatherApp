package models;


import play.db.jpa.Model;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
public class Reading extends Model {
    public int code;
    public float temperature;
    public float windSpeed;
    public int pressure;
    public int windDirection;
    public String weather;

    public Reading(int code, float temperature, float windSpeed, int pressure, int windDirection){
        this.code = code;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.pressure = pressure;
        this.windDirection = windDirection;
        /*this.weather = predictWeather(temperature, windSpeed, pressure, windDirection);*/
    }

    /*public static String predictWeather(float temperature, float windSpeed, int pressure, int windDirection){
        ArrayList<String> typesOfWeather = new ArrayList<String>();
        Random randomNo = new Random();
        int randomInt = randomNo.nextInt(typesOfWeather.size());
        typesOfWeather.add("Rain");
        typesOfWeather.add("Sunshine");
        typesOfWeather.add("Overcast");
        typesOfWeather.add("Windy");
        return typesOfWeather.get(randomInt);
    }*/
}
