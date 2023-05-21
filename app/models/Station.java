package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
@Entity
public class Station extends Model {
    public String name;
    public float lat;
    public float lng;
    private static final DecimalFormat df = new DecimalFormat("0.00");
    public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss", Locale.UK);
    @OneToMany(cascade = CascadeType.ALL)
    public List<Reading> readings = new ArrayList<Reading>();
    ;
    public Station(String name, float lat, float lng){
        this.name = name;
        this.lat = Float.parseFloat(df.format(lat));
        this.lng = Float.parseFloat(df.format(lng));
    }

    private Reading getLatestReading(){
        if (readings.size() > 0){
            return readings.get(readings.size() - 1);
        }
        else {
            return new Reading(dateTimeFormatter.format(LocalDateTime.now()),0,0,0,0, 0);
        }
    }

    public String getName(){
        return this.name = name;
    }

    private float getMinTemp(){
        int i = 1;
        if (readings.size()>0) {
            float lowestTemp = readings.get(0).temperature;
            while (i != readings.size()) {
                if (readings.get(i).temperature < lowestTemp) {
                    lowestTemp = readings.get(i).temperature;
                }
                i += 1;
            }
            return lowestTemp;
        }
        else {
            return 0;
        }
    }
    private float getMaxTemp(){
        int i = 1;
        if (readings.size()>0) {
            float maxTemp = readings.get(0).temperature;
            while (i != readings.size()) {
                if (readings.get(i).temperature > maxTemp) {
                    maxTemp = readings.get(i).temperature;
                }
                i += 1;
            }
            return maxTemp;
        }
        else {
            return 0;
        }
    }
    private double getMinWindSpeed(){
        int i = 1;
        if (readings.size()>0) {
            double lowestSpeed = readings.get(0).windSpeed;
            while (i != readings.size()) {
                if (readings.get(i).windSpeed < lowestSpeed) {
                    lowestSpeed = readings.get(i).windSpeed;
                }
                i += 1;
            }
            return lowestSpeed;
        }
        else {
            return 0;
        }
    }
    private double getMaxWindSpeed(){
        int i = 1;
        if (readings.size()>0) {
            double maxSpeed = readings.get(0).windSpeed;
            while (i != readings.size()) {
                if (readings.get(i).windSpeed > maxSpeed) {
                    maxSpeed = readings.get(i).windSpeed;
                }
                i += 1;
            }
            return maxSpeed;
        }
        else {
            return 0;
        }
    }
    private float getMinPressure(){
        int i = 1;
        if (readings.size() > 0) {
            float lowestPressure = readings.get(0).pressure;
            while (i != readings.size()) {
                if (readings.get(i).pressure < lowestPressure) {
                    lowestPressure = readings.get(i).pressure;
                }
                i += 1;
            }
            return lowestPressure;
        }
        else {
            return 0;
        }
    }
    private float getMaxPressure(){
        int i = 1;
        if (readings.size()>0) {
            float maxPressure = readings.get(0).pressure;
            while (i != readings.size()) {
                if (readings.get(i).pressure > maxPressure) {
                    maxPressure = readings.get(i).pressure;
                }
                i += 1;
            }
            return maxPressure;
        }
        else {
            return 0;
        }
    }

    private String celsiusToFahrenheit(){
        return df.format((getLatestReading().temperature * 9/5) + 32);
    }
    private int kmhrToBeaufort(){
        if (getLatestReading().windSpeed <= 1){
            return 0;
        }
        if (getLatestReading().windSpeed > 1 || getLatestReading().windSpeed <= 5){
            return 1;
        }
        if (getLatestReading().windSpeed > 5 || getLatestReading().windSpeed <= 11) {
            return 2;
        }
        if (getLatestReading().windSpeed > 11 || getLatestReading().windSpeed <= 19) {
            return 3;
        }
        if (getLatestReading().windSpeed > 19 || getLatestReading().windSpeed <= 28) {
            return 4;
        }
        if (getLatestReading().windSpeed > 28 || getLatestReading().windSpeed <= 38) {
            return 5;
        }
        if (getLatestReading().windSpeed > 38 || getLatestReading().windSpeed <= 49) {
            return 6;
        }
        if (getLatestReading().windSpeed > 49 || getLatestReading().windSpeed <= 61) {
            return 7;
        }
        if (getLatestReading().windSpeed > 61 || getLatestReading().windSpeed <= 74) {
            return 8;
        }
        if (getLatestReading().windSpeed > 74 || getLatestReading().windSpeed <= 88) {
            return 9;
        }
        if (getLatestReading().windSpeed > 89 || getLatestReading().windSpeed <= 102) {
            return 10;
        }
        if (getLatestReading().windSpeed > 102 || getLatestReading().windSpeed <= 117) {
            return 11;
        }
        return 0;
    }
    private String beaufortLabel(){
        if (kmhrToBeaufort() == 0){
            return "Calm";
        }
        if (kmhrToBeaufort() == 1){
            return "Light Air";
        }
        if (kmhrToBeaufort() == 2){
            return "Light Breeze";
        }
        if (kmhrToBeaufort() == 3){
            return "Gentle Breeze";
        }
        if (kmhrToBeaufort() == 4){
            return "Moderate Breeze";
        }
        if (kmhrToBeaufort() == 5){
            return "Fresh Breeze";
        }
        if (kmhrToBeaufort() == 6){
            return "Strong Breeze";
        }
        if (kmhrToBeaufort() == 7){
            return "Near Gale";
        }
        if (kmhrToBeaufort() == 8){
            return "Gale";
        }
        if (kmhrToBeaufort() == 9){
            return "Severe Gale";
        }
        if (kmhrToBeaufort() == 10){
            return "Strong Storm";
        }
        if (kmhrToBeaufort() == 11){
            return "Violent Storm";
        }
        return "Unknown";
    }
    private String predictWeather(){
        ArrayList<String> typesOfWeather = new ArrayList<String>();
        typesOfWeather.add("Clear");
        typesOfWeather.add("Partial Clouds");
        typesOfWeather.add("Cloudy");
        typesOfWeather.add("Light Showers");
        typesOfWeather.add("Heavy Showers");
        typesOfWeather.add("Rain");
        typesOfWeather.add("Snow");
        typesOfWeather.add("Thunder");
        int code = getLatestReading().code;
        if (code == 100){
            return typesOfWeather.get(0);
        }
        if (code == 200){
            return typesOfWeather.get(1);
        }
        if (code == 300){
            return typesOfWeather.get(2);
        }
        if (code == 400){
            return typesOfWeather.get(3);
        }
        if (code == 500){
            return typesOfWeather.get(4);
        }
        if (code == 600){
            return typesOfWeather.get(5);
        }
        if (code == 700){
            return typesOfWeather.get(6);
        }
        if (code == 800){
            return typesOfWeather.get(7);
        }
        return "Unknown";
    }
    private String convertWindDirection(){
        float windDirection = getLatestReading().windDirection;
        if (windDirection >= 348.75 && windDirection <= 360){
            return "N";
        }
        if (windDirection >= 0 && windDirection <= 11.25){
            return "N";
        }
        if (windDirection > 11.25 && windDirection <= 33.75){
            return "NNE";
        }
        if (windDirection > 33.75 && windDirection <= 56.25){
            return "NE";
        }
        if (windDirection > 56.25 && windDirection <= 78.75){
            return "ENE";
        }
        if (windDirection >78.75 && windDirection <= 101.25){
            return "E";
        }
        if (windDirection >101.25 && windDirection <= 123.75){
            return "ESE";
        }
        if (windDirection >123.75 && windDirection <= 146.25){
            return "SE";
        }
        if (windDirection >146.25 && windDirection <= 168.75){
            return "SSE";
        }
        if (windDirection >168.75 && windDirection <= 191.25){
            return "S";
        }
        if (windDirection >191.25 && windDirection <= 213.25){
            return "SSW";
        }
        if (windDirection >213.25 && windDirection <= 236.25){
            return "SW";
        }
        if (windDirection >236.25 && windDirection <= 258.75){
            return "WSW";
        }
        if (windDirection >258.75 && windDirection <= 281.25){
            return "W";
        }
        if (windDirection >281.25 && windDirection <= 303.75){
            return "WNW";
        }
        if (windDirection >303.75 && windDirection <= 326.25){
            return "NW";
        }
        if (windDirection > 326.25 && windDirection < 348.75){
            return "NNW";
        }
        return "U";
    }
    private String calculateWindChill(){
        return df.format((13.12 + (0.6215*getLatestReading().temperature) - (11.37*Math.pow(getLatestReading().windSpeed,0.16)) + (0.3965*getLatestReading().temperature*Math.pow(getLatestReading().windSpeed,0.16))));
    }
    private String trendTemp(){
        if (readings.size()>=3) {
            float lastTemp = readings.get(readings.size() - 1).temperature;
            float secondLastTemp = readings.get(readings.size() - 2).temperature;
            float thirdLastTemp = readings.get(readings.size() - 3).temperature;
            if (thirdLastTemp > secondLastTemp && secondLastTemp > lastTemp) {
                return "DOWN";
            } else if (lastTemp > secondLastTemp && secondLastTemp > thirdLastTemp) {
                return "UP";
            } else {
                return "STEADY";
            }
        }
        else {
            return "STEADY";
        }
    }
    private String trendWind(){
        if (readings.size()>=3) {
            double lastWind = readings.get(readings.size() - 1).windSpeed;
            double secondLastWind = readings.get(readings.size() - 2).windSpeed;
            double thirdLastWind = readings.get(readings.size() - 3).windSpeed;
            if (thirdLastWind > secondLastWind && secondLastWind > lastWind) {
                return "DOWN";
            } else if (lastWind > secondLastWind && secondLastWind > thirdLastWind) {
                return "UP";
            } else {
                return "STEADY";
            }
        }
        else {
            return "STEADY";
        }
    }
    private String trendPressure(){
        if (readings.size()>=3) {
            float lastPressure = readings.get(readings.size() - 1).pressure;
            float secondLastPressure = readings.get(readings.size() - 2).pressure;
            float thirdLastPressure = readings.get(readings.size() - 3).pressure;
            if (thirdLastPressure > secondLastPressure && secondLastPressure > lastPressure) {
                return "DOWN";
            } else if (lastPressure > secondLastPressure && secondLastPressure > thirdLastPressure) {
                return "UP";
            } else {
                return "STEADY";
            }
        }
        else {
            return "STEADY";
        }
    }
}
