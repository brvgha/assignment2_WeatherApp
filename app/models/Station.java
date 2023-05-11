package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Entity
public class Station extends Model {
    public String name;
    @OneToMany(cascade = CascadeType.ALL)
    public List<Reading> readings = new ArrayList<Reading>();
    public Station(String name){
        this.name = name;
    }

    private Reading mostRecentReading(){
        if (readings.size() > 0){
            return readings.get(readings.size() - 1);
        }
        else {
            return new Reading(0,0,0,0,0);
        }
    }

    private float celsiusToFahrenheit(){
        float fahr = mostRecentReading().temperature;
        return (fahr * 9/5) + 32;
    }
    private String predictWeather(){
        ArrayList<String> typesOfWeather = new ArrayList<String>();
        Random randomNo = new Random();
        typesOfWeather.add("Rain");
        typesOfWeather.add("Sunshine");
        typesOfWeather.add("Overcast");
        typesOfWeather.add("Windy");
        int randomInt = randomNo.nextInt(typesOfWeather.size());
        return typesOfWeather.get(randomInt);
    }
}
