package com.harlexprojects.json;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Ahmet OZCAN
 * @since 25.02.2016
 */
public class JSONParser
{
    public enum FETCH_METHOD {POST, GET}
    public JSONParser()
    {
        // Empty Constructor.
    }

    @SuppressWarnings("deprecation")
    public JSONObject getJSONFromUrl(String url, FETCH_METHOD fetch_method, HashMap<String, String> params)
    {
        InputStream is = null;
        JSONObject jsonObject = null;
        String json = "";

        try
        {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpRequestBase httpRequestBase;
            if(fetch_method == FETCH_METHOD.POST)
            {
                HttpPost httpPost = new HttpPost(url);

                if(params != null && params.size() > 0)
                {
                    List<NameValuePair> nameValuePairs = new ArrayList<>(params.size());
                    for(String key: params.keySet())
                    {
                        nameValuePairs.add(new BasicNameValuePair(key, params.get(key)));
                    }
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                }

                httpRequestBase = httpPost;
            }
            else if(fetch_method == FETCH_METHOD.GET)
            {
                HttpGet httpGet = new HttpGet(url);
                httpRequestBase = httpGet;
            }
            else
            {
                httpRequestBase = null;
            }
            HttpResponse httpResponse = httpClient.execute(httpRequestBase);
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try
        {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line;
            while((line = bufferedReader.readLine()) != null)
            {
                String s = line +  "\n";
                sb.append(s);
            }
            is.close();
            json = sb.toString();
        }
        catch (Exception exp)
        {
            exp.printStackTrace();
        }

        try
        {
            jsonObject = new JSONObject(json);
        }
        catch (Exception exp)
        {
            exp.printStackTrace();
        }
        return jsonObject;
    }
}
