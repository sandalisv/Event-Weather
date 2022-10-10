/*
 * Copyright (c) 10/10/22, 2:39 AM Created by Sandali Vithanage.
 */

package com.events.event.management.services;

import com.events.event.management.dao.EventDAO;
import com.events.event.management.entity.Event;
import com.events.event.management.exception.CustomApiException;
import com.events.event.management.exception.CustomDateException;
import com.events.event.management.exception.CustomDbException;
import com.events.event.management.model.GeoCode;
import com.events.event.management.model.Weather;
import com.events.event.management.model.WeatherList;
import com.events.event.management.model.WeatherRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Event service.
 */
@Service
@Transactional
public class EventServiceImpl implements EventService {
    @Autowired
    private EventDAO dao;

    /**
     * The API Key for openweathwemap. Please replace with your key.
     */
    private static final String APP_ID = "921ccf6a3e57a4011ff63e3d20c6ab59";

    /**
     * The type Event service.
     */
    @Override
    public List<Event> getEvents() throws CustomDbException {
        return dao.getEvents();
    }

    @Override
    public Event getEvent(int id) throws CustomDbException {
        return dao.getEvent(id);
    }

    @Override
    public Event createEvent(Event event) throws CustomApiException, CustomDbException, CustomDateException {
        try {
            if (getGeo(event.getCity(), event.getCountry()) != null) {
                List<GeoCode> geoList = getGeo(event.getCity(), event.getCountry());
                LocalDate eventDate = event.getDate();
                LocalTime startTime = event.getStart_time();
                LocalTime endTime = event.getEnd_time();
                if (geoList != null) {
                    String weather = getWeather(geoList.get(0).lat, geoList.get(0).lon, eventDate, startTime, endTime);
                    event.setWeather(weather);
                    return dao.createEvent(event);
                } else return null;
            } else return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public boolean deleteEvent(int id) throws CustomDbException {
        try {
            boolean isDeleted = dao.deleteEvent(id);
            return isDeleted;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * This function will return the GeoCode of the location using city and country as parameters and calling external API.
     */
    private List<GeoCode> getGeo(String city, String country) throws CustomApiException {
        final String locationUri = "http://api.openweathermap.org/geo/1.0/direct?q=" + city + "," + country + "&limit=1&appid="+APP_ID;
        RestTemplate restTemplate = new RestTemplate();
        ParameterizedTypeReference<List<GeoCode>> responseType = new ParameterizedTypeReference<List<GeoCode>>() {
        };
        try {
            ResponseEntity<List<GeoCode>> resp = restTemplate.exchange(locationUri, HttpMethod.GET, null, responseType);
            return resp.getBody();

        } catch (Exception e) {
            throw new CustomApiException(e.getMessage(), e.getCause());
        }


    }

    /**
     * This function will return a String with weather data during the event as a comma separated values and calling external API.
     */
    private String getWeather(double lat, double lon, LocalDate date, LocalTime startTime, LocalTime endTime) throws CustomApiException, CustomDateException {
        final String weatherUri = "http://api.openweathermap.org/data/2.5/forecast?lat=" + lat + "&lon=" + lon + "&appid="+APP_ID;
        RestTemplate restTemplate = new RestTemplate();
        WeatherRes weatherRes = new WeatherRes();
        String weatherString = null;
        ParameterizedTypeReference<WeatherRes> responseType = new ParameterizedTypeReference<WeatherRes>() {
        };
        try {
            ResponseEntity<WeatherRes> resp = restTemplate.exchange(weatherUri, HttpMethod.GET, null, responseType);
            weatherRes = resp.getBody();
        } catch (Exception e) {
            throw new CustomApiException(e.getMessage(), e.getCause());
        }

        try {
            if (isValidDateRange(date, LocalDate.now().plusDays(5))) {

                String formattedDate = formatDate(date, "yyyy-MM-dd");
                List<WeatherList> weatherLists = new ArrayList<>();
                List<Weather> weathers = new ArrayList<>();
                if (weatherRes != null) {
                    weatherLists = weatherRes.getWeatherList().stream()
                            .filter(a -> a.dt_txt.contains(formattedDate)).collect(Collectors.toList());
                }
                weathers = getWeatherList(date, startTime, endTime, weatherLists, weathers);
                weatherString = getWeatherString(weathers);
            } else throw new CustomDateException(date + " is invalid");
        } catch (CustomDateException e) {
            e.printStackTrace();
        }

        return weatherString;
    }

    /**
     * This function will construct a String with weather data during the event as a comma separated values.
     */
    private static String getWeatherString(List<Weather> weathers) {
        String weather = null;
        for (Weather we : weathers) {
            if (weather == null) {
                weather = we.main;
            } else {
                weather = weather + "," + we.main;
            }
        }
        return weather;
    }

    /**
     * This function will return a List of Weather .
     */
    private List<Weather> getWeatherList(LocalDate date, LocalTime startTime, LocalTime endTime, List<WeatherList> weatherLists, List<Weather> weathers) {
        for (WeatherList w : weatherLists) {
            LocalDateTime date1 = parseDate(w.dt_txt, "yyyy-MM-dd HH:mm:ss");
            Instant wDate = date1.toInstant(ZoneOffset.UTC);
            Instant sTime = startTime.atDate(date).atZone(ZoneOffset.UTC).toInstant();
            Instant eTime = endTime.atDate(date).atZone(ZoneOffset.UTC).toInstant();
            Duration res = Duration.between(sTime, eTime);
            if (sTime.isBefore(wDate) && eTime.isAfter(wDate) && res.toMinutes() > 180) {
                weathers.add(w.getWeather().stream().collect(Collectors.toList()).get(0));
            } else if (sTime.isBefore(wDate) && res.toMinutes() <= 180) {
                weathers = w.getWeather().stream().collect(Collectors.toList());
                break;
            }
        }
        return weathers;
    }

    /**
     * This function will format date according to the pattern.
     */
    private String formatDate(LocalDate date, String pattern) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return date.format(dateTimeFormatter);
    }

    /**
     * This function will parse date string according to the pattern.
     */
    private LocalDateTime parseDate(String dateText, String pattern) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(dateText, dateTimeFormatter);
    }

    /**
     * This function will check the validity of entered date.
     * if the entered date is within 5 days from current date, then it is considered as a valid date.
     */
    private boolean isValidDateRange(LocalDate eventDate, LocalDate validDate) {
        return ((eventDate.isAfter(LocalDate.now()) || eventDate.isEqual(LocalDate.now())) && eventDate.isBefore(validDate));
    }
}

