package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Member;
import models.Reading;
import models.Station;
import play.Logger;
import play.mvc.Controller;

public class Dashboard extends Controller
{
  public static void index() {
    Logger.info("Rendering Dashboard");
    Member member = Accounts.getLoggedMember();
    List<Station> stations = member.stations;
    render ("dashboard.html", stations);
  }
  public static void addStation(String name, float latitude, float longitude){
    Member member = Accounts.getLoggedMember();
    Station station = new Station(name, latitude, longitude);
    member.stations.add(station);
    station.save();
    redirect("/dashboard");
  }

  public static void deleteStation(String name){
    Member member = Accounts.getLoggedMember();
    Station station = Station.findById(name);
    member.stations.remove(name);
    member.save();
    station.delete();
    Logger.info("Deleting " + station.name);
    redirect("/dashboard");
  }
}
