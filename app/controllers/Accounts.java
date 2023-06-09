package controllers;

import com.thoughtworks.xstream.mapper.Mapper;
import models.Member;
import play.Logger;
import play.mvc.Controller;

import java.util.Objects;

public class Accounts extends Controller {
    public static void signup(){
        render("signup.html");
    }
    public static void login(){
        render("login.html");
    }
    public static void editAccount(){
        render("editaccount.html");
    }
    public static void register(String firstname, String lastname, String email, String password){
        Logger.info("Registering new user " + email);
        Member member = new Member(firstname, lastname, email, password);
        member.save();
        redirect("/login");
    }
    public static void authenticateUser(String email, String password){
        Logger.info("Authenticating user " + email + " with " + password);
        Member member = Member.getByEmail(email);
        if ((member != null) && (member.checkPassword(password)==true)){
            Logger.info("Authentication successful");
            session.put("logged_in_Memberid",member.id);
            redirect("/dashboard");
        }
        else {
            Logger.info("Authentication failed");
            redirect("/login");
        }
    }
    public static void changeUserDetails(String firstname, String lastname, String password){
        Member member = getLoggedMember();
        if (!Objects.equals(firstname, "")) {
            member.firstname = firstname;
        }
        if (!Objects.equals(lastname, "")){
            member.lastname = lastname;
        }
        if (!Objects.equals(password, "")){
            member.password = password;
        }
        member.save();
        redirect("/login");
    }
    public static Member getLoggedMember(){
        Member member = null;
        if (session.contains("logged_in_Memberid")){
            String memberid = session.get("logged_in_Memberid");
            member = Member.findById(Long.parseLong(memberid));
        }
        else {
            login();
        }
        return member;
    }
    public static void logout(){
        session.clear();
        redirect("/");
    }
}
