package com.example.csc413_volley_template.controller;

import android.net.Uri;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.csc413_volley_template.app.App;
import com.example.csc413_volley_template.model.Meetup;
import com.example.csc413_volley_template.request.JsonRequest;
import com.example.csc413_volley_template.volley.VolleySingleton;

import java.util.List;

/*
 * Created by abhijit on 12/2/16.
 */

/**
 * <p> Provides interface between {@link android.app.Activity} and {@link com.android.volley.toolbox.Volley} </p>
 */
public class JsonController {

    private final int TAG = 100;

    private OnResponseListener responseListener;

    /**
     *
     * @param responseListener  {@link OnResponseListener}
     */
    public JsonController(OnResponseListener responseListener) {
        this.responseListener = responseListener;
    }

    /**
     * Adds request to volley request queue
     * @param query query term for search
     */
    public void sendRequest(String query){

        // Request Method
        int method = Request.Method.GET;

        // Url with GET parameters, ?s means youre going to have a bunch of varbles in your path,
        // type &t= is movie

        //String url = "http://www.omdbapi.com/?s=" + Uri.encode(query) + "&t=movie";

        String url = "https://api.meetup.com/find/groups?&sign=true&photo-host=public&key=5b7b265615f4c1c6d4130145a7d164&text="
                + Uri.encode(query);
        // Create new request using JsonRequest
        JsonRequest request
            = new JsonRequest(
                method,
                url,
                //if you pass call this:
                new Response.Listener<List<Meetup>>() {
                    @Override
                    public void onResponse(List<Meetup> meetups) {
                        responseListener.onSuccess(meetups);
                    }
                },
                //if you fail call this
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        responseListener.onFailure(error.getMessage());
                    }
                }
        );

        // Add tag to request
        request.setTag(TAG);

        // Get RequestQueue from VolleySingleton... ask volley to add the request to the queue
        VolleySingleton.getInstance(App.getContext()).addToRequestQueue(request);
    }

    /**
     * <p>Cancels all request pending in request queue,</p>
     * <p> There is no way to control the request already processed</p>
     */
    public void cancelAllRequests() {
        VolleySingleton.getInstance(App.getContext()).cancelAllRequests(TAG);
    }

    /**
     *  Interface to communicate between {@link android.app.Activity} and {@link JsonRequest}
     *  <p>Object available in {@link JsonRequest} and implemented in {@link com.example.csc413_volley_template.MainActivity}</p>
     */
    public interface OnResponseListener {
        void onSuccess(List<Meetup> meetups);
        void onFailure(String errorMessage);
    }

}