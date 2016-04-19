package com.zinitsolutions.test.testapplication.API;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by dmitrij on 4/19/16.
 */
class RetrievePosts {
    private List<JSONObject> mServerRespone;

    private class RetrievePostsTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();

                    return stringBuilder.toString();
                } finally {
                    urlConnection.disconnect();
                }
            } catch (Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }
    }

    public RetrievePosts() {
        String serverResponse = getPostsFromServer();
        JSONArray jsonArray;

        try {
            jsonArray = new JSONArray(serverResponse);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String getPostsFromServer() {
        RetrievePostsTask retrievePostsTask = new RetrievePostsTask();
        retrievePostsTask.execute(getApiUrl());

        String serverResponse = "";
        try {
            serverResponse = retrievePostsTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return serverResponse;
    }

    private String getApiUrl() {
        //TODO hardcode
        return "https://api.ukrbash.org/1/pictures.getRandom.json?client=802e2ef04fe3b3e4&limit=100";
    }
}