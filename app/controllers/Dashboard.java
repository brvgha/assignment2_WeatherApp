package controllers;

import java.util.List;
import models.Member;
import models.Station;
import play.Logger;
import play.mvc.Controller;

public class Dashboard extends Controller
{
  public static void index() {
    Logger.info("Rendering Dashboard");
    Member member = Accounts.getLoggedMember();
    List<Station> stations = member.stations;
    render ("dashboard.html", member, stations);
  }
  public static void addStation(String name, float lat, float lng){
    Member member = Accounts.getLoggedMember();
    Station station = new Station(name, lat, lng);
    member.stations.add(station);
    station.save();
    redirect("/dashboard");
  }

  public static void deleteStation(Long id){
    Member member = Accounts.getLoggedMember();
    Station station = Station.findById(id);
    member.stations.remove(station);
    member.save();
    station.delete();
    Logger.info("Deleting " + station.name);
    redirect("/dashboard");
  }
}
