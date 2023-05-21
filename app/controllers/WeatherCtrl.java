package controllers;

import play.Logger;
import play.mvc.Controller;
import models.Reading;
import models.Station;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class WeatherCtrl extends Controller {
    public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss", Locale.UK);
    public static void index(Long id)
    {
        Station station = Station.findById(id);
        Logger.info("Station name = " + id);
        render("station.html", station);
    }

    public static void addReading(Long id, int code, float temperature, float windSpeed, int windDirection, int pressure){
        Reading reading = new Reading(dateTimeFormatter.format(LocalDateTime.now()), code, temperature, windSpeed,windDirection,pressure);
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