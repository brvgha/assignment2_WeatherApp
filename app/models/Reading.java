package models;



import play.db.jpa.Model;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Reading extends Model {
    public int code;
    public float temperature;
    public float windSpeed;
    public int pressure;
    public float windDirection;

    public Reading(int code, float temperature, float windSpeed, float windDirection,int pressure){
        this.code = code;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.pressure = pressure;
        this.windDirection = windDirection;
    }
}
