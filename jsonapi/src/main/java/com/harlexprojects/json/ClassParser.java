package com.harlexprojects.json;

import com.google.gson.Gson;

import org.json.JSONObject;

/**
 * @author Ahmet OZCAN
 * @since 25.02.2016
 */
public class ClassParser
{
    public ClassParser()
    {
        // Empty Constructor..
    }

    public Object jsonToClass(JSONObject jsonObject, Class cls)
            throws IllegalAccessException, InstantiationException
    {
        Gson gson = new Gson();
        Object obj = null;
        if(jsonObject != null)
        {
            obj = gson.fromJson(jsonObject.toString(), cls);
        }

        return obj;
    }
}
