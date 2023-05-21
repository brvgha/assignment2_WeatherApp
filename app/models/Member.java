package models;

import org.apache.commons.lang.WordUtils;
import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Member extends Model {
    public String firstname;
    public String lastname;
    public String email;
    public String password;
    @OneToMany(cascade = CascadeType.ALL)
    public List<Station> stations = new ArrayList<Station>();
    public Member(String firstname, String lastname,String email, String password){
        this.firstname = WordUtils.capitalize(firstname);
        this.lastname = WordUtils.capitalize(lastname);
        this.email = email;
        this.password = password;
    }

    public static Member getByEmail(String email){
        return find("email", email).first();
    }

    public boolean checkPassword(String password){
        return this.password.equals(password);
    }


    public List<Station> sortStations(){
        List<Station> sortedStations = new ArrayList<Station>();
        for (int i = 0; i<stations.size();i++){
            for (int j =1; j <stations.size()-1;i++)
            if (stations.get(i).getName().compareTo(stations.get(j).getName()) < 0){
                sortedStations.add(stations.get(i));
            }
            else if (stations.get(i).getName().compareTo(stations.get(j).getName()) > 0){
                sortedStations.add(stations.get(j));
            }
        }
        return sortedStations;
    }
}
