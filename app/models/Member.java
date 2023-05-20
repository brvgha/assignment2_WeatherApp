package models;

import org.apache.commons.lang.WordUtils;
import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Comparator;
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
        this.firstname = getFirstname();
        this.lastname = getLastname();
        this.email = getEmail();
        this.password = getPassword();
    }

    public static Member getByEmail(String email){
        return find("email", email).first();
    }

    public boolean checkPassword(String password){
        return getPassword().equals(password);
    }


    public String getEmail(){
        return this.email;
    }
    public String getFirstname(){
        return WordUtils.capitalize(this.firstname);
    }
    public String getLastname(){
        return WordUtils.capitalize(this.lastname) ;
    }
    public String getPassword(){
        return this.password;
    }
    public String setFirstName(){
        return firstname;
    }
    public String setLastName(){
        return lastname;
    }
    public String setEmail(){
        return email;
    }
    public String setPassword(){
        return password;
    }

    /*public List<Station> sortStations(<List>Station station){
        station.sort(Comparator.comparing(Station.getName));
    }*/
}
