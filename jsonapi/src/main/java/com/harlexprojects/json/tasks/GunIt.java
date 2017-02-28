package com.harlexprojects.json.tasks;

import android.os.AsyncTask;

import com.harlexprojects.json.JSONParser;

import org.json.JSONObject;

import java.util.HashMap;


public class GunIt extends AsyncTask<Void, Void, Void>
{
    private String url;
    private JSONParser.FETCH_METHOD fetch_method;
    private OnGunItCallback callback;
    private JSONObject result;
    private Exception error;
    private HashMap<String, String> params;

    public GunIt(String url, JSONParser.FETCH_METHOD fetch_method, HashMap<String, String> param, OnGunItCallback callback)
    {
        this.url = url;
        this.fetch_method = fetch_method;
        this.callback = callback;
        this.params = param;
    }

    @Override
    protected Void doInBackground(Void... objects)
    {
        JSONParser jsonParser = new JSONParser();
        result = jsonParser.getJSONFromUrl(url, fetch_method, params);
        if(result == null)
            error = new Exception("NO DATA");
        return null;
    }

    @Override
    protected void onPostExecute(Void o)
    {
        super.onPostExecute(o);

        if(callback != null)
        {
            callback.onReceive(result , error);
        }
    }

}
