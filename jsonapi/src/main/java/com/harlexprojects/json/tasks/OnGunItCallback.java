package com.harlexprojects.json.tasks;

import org.json.JSONObject;

public interface OnGunItCallback
{
    void onReceive(JSONObject data, Exception exp);
}
