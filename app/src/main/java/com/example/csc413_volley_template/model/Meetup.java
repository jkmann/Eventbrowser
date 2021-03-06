package com.example.csc413_volley_template.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.List;

/*
 * Created by abhijit on 12/1/16.
 */


/**
 * Model class for movie
 */
public class Meetup {

    private String title;
    private String description;
    private String hostName;
    private String lat;
    private String lon;
    private String pictureUrl;
    private String city;
    private String members;

    /**
     *
     * @param jsonArray    {@link JSONObject} response, received in Volley success listener
     * @return  list of movies
     * @throws JSONException
     */
    public static List<Meetup> parseJson(JSONArray jsonArray) throws JSONException{
        List<Meetup> meetups = new ArrayList<>();
        // Check if the JSONObject has object with key "Search"


            for(int i = 0; i < jsonArray.length(); i++){
                // Create new Meetup object from each JSONObject in the JSONArray
                meetups.add(new Meetup(jsonArray.getJSONObject(i)));
            }


        return meetups;
    }

    /**
     * <p>Class constructor</p>
     * <p>Sample Meetup JSONObject</p>
     * <pre>
     * {
     *  "Title": "Batman Begins",
     *  "Year": "2005",
     *  "imdbID": "tt0372784",
     *  "Type": "movie",
     *  "Poster": "https://images-na.ssl-images-amazon.com/images/M/MV5BNTM3OTc0MzM2OV5BMl5BanBnXkFtZTYwNzUwMTI3._V1_SX300.jpg"
     * }
     * </pre>
     * @param jsonObject    {@link JSONObject} from each item in the search result
     * @throws JSONException     when parser fails to parse the given JSON
     */
    private Meetup(JSONObject jsonObject) throws JSONException {
        if(jsonObject.has("name")) this.setTitle(jsonObject.getString("name"));
        if(jsonObject.has("description")) this.setDescription(shorten(jsonObject.getString("description"),770));
        if(jsonObject.has("organizer")) this.setHostName(jsonObject.getJSONObject("organizer").getString("name"));
        if(jsonObject.has("latitude")) this.setLat(jsonObject.getString("latitude"));
        if(jsonObject.has("longitude")) this.setLon(jsonObject.getString("longitude"));
        if(jsonObject.has("group_photo")) {
            this.setPictureUrl(jsonObject.getJSONObject("group_photo").getString("photo_link"));
        }
        if(jsonObject.has("city")) this.setCity(jsonObject.getString("city") + ", " + jsonObject.getString("state") + ", " + jsonObject.getString("country"));
        if(jsonObject.has("members")) this.setMembers("Members: " + jsonObject.getString("members"));
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public static String shorten(String sentence, int limit){
        sentence = sentence.replaceAll("\\<.*?>","");
        sentence = sentence.substring(0, Math.min(sentence.length(),limit));
        if(sentence.length()>=limit){
            sentence = sentence + "...";
        }
        return sentence;
    }
}
