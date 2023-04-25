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
}
