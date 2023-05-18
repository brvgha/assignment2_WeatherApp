package models;

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
        this.firstname = getFirstname();
        this.lastname = getLastname();
        this.email = getEmail();
        this.password = getPassword();
    }
    public String getFirstname(){
        return this.firstname;
    }

    public static Member getByEmail(String email){
        return find("email", email).first();
    }

    public boolean checkPassword(String password){
        return getPassword().equals(password);
    }
    public String getPassword(){
        return this.password = password;
    }
    public String setPassword(){
        return password;
    }
    public String getEmail(){
        return this.email = email;
    }
    public String setEmail(){
        return email;
    }
    public String setFirstName(){
        return firstname;
    }
    public String getLastname(){
        return this.lastname = lastname;
    }
    public String setLastName(){
        return lastname;
    }
}
