package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Reading;
import models.Station;
import play.Logger;
import play.mvc.Controller;

public class Dashboard extends Controller
{
  public static void index() {
    Logger.info("Rendering Dashboard");
    List<Station> stations = Station.findAll();
    render ("dashboard.html", stations);
  }
  public static void addStation(String name){
    Station station = new Station(name);
    Logger.info("Adding Station" + name);
    station.save();
    redirect("/dashboard");
  }

  public static void deleteStation(String name){
    Station station = Station.findById(name);
    station.delete(name);
    station.save();
    Logger.info("Deleting " + station.name);
    redirect("/dashboard");
  }
}
