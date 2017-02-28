package hmt.hckthn.cm.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.harlexprojects.json.ClassParser;
import com.harlexprojects.json.JSONParser;
import com.harlexprojects.json.tasks.GunIt;
import com.harlexprojects.json.tasks.OnGunItCallback;

import org.json.JSONObject;

import java.util.HashMap;

import hmt.hckthn.cm.hckthn.R;

public class Dispatcher extends AppCompatActivity
{
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hckthn_dispatcher);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        progressBar.setVisibility(View.VISIBLE);

        HashMap<String, String> params = new HashMap<>();
        params.put("email", "semihkabat@gmail.com");
        params.put("name", "Semih KABAT");
        params.put("gsm", "+905317202786");
        new GunIt("https://getir-bitaksi-hackathon.herokuapp.com/getElements", JSONParser.FETCH_METHOD.POST, params, new OnGunItCallback()
        {
            @Override
            public void onReceive(JSONObject data, Exception exp)
            {
                progressBar.setVisibility(View.GONE);
                BGData bgData;
                if(exp == null)
                {
                    Log.e("HMT", data.toString());
                    try
                    {
                        bgData = (BGData) new ClassParser().jsonToClass(data, BGData.class);
                    }
                    catch (Exception e)
                    {
                        Log.w("HMT", e);
                        bgData = new BGData();
                        bgData.setCode(-1);
                        bgData.setMessage(e.getMessage());
                    }
                }
                else
                {
                    Log.w("HMT", exp);
                    bgData = new BGData();
                    bgData.setCode(-1);
                    bgData.setMessage(exp.getMessage());
                }
                callResultActiivty(bgData);
            }
        }).execute();
    }

    private void callResultActiivty(BGData result)
    {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(ResultActivity.EXTRA_RESULT, result);
        //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
