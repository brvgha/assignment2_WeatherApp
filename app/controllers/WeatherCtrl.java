package controllers;

import play.Logger;
import play.mvc.Controller;
import models.Reading;
import models.Station;


public class WeatherCtrl extends Controller {
    public static void index(Long id)
    {
        Station station = Station.findById(id);
        Logger.info("Station name = " + id);
        render("station.html", station);
    }

    public static void addReading(Long id, String dateTime, int code, float temperature, float windSpeed, int pressure, int windDirection){
        Reading reading = new Reading(dateTime, code, temperature, windSpeed, pressure, windDirection);
        Station station = Station.findById(id);
        station.readings.add(reading);
        station.save();
        Logger.info("Adding Reading" + code);
        redirect("/stations/" + id);
    }

    public static void deleteReading(Long id, Long readingid){
        Station station = Station.findById(id);
        Reading reading = Reading.findById(readingid);
        Logger.info("Removing " + reading.code);
        station.readings.remove(reading);
        station.save();
        reading.delete();
        render("station.html", station);
    }
}