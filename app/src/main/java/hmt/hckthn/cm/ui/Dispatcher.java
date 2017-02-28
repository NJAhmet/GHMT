package hmt.hckthn.cm.ui;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.harlexprojects.json.ClassParser;
import com.harlexprojects.json.JSONParser;
import com.harlexprojects.json.tasks.GunIt;
import com.harlexprojects.json.tasks.OnGunItCallback;

import org.json.JSONObject;

import java.util.HashMap;

import hmt.hckthn.cm.hckthn.R;

public class Dispatcher extends Activity implements View.OnClickListener
{
    private RelativeLayout progressBar;
    private EditText etName, etEMail, etPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hckthn_dispatcher);

        progressBar = (RelativeLayout) findViewById(R.id.ly_progressbar);
        etEMail = (EditText) findViewById(R.id.et_email);
        etName = (EditText) findViewById(R.id.et_name);
        etPhone = (EditText) findViewById(R.id.et_gsm);

        progressBar.setVisibility(View.GONE);
        progressBar.setOnClickListener(this);
        findViewById(R.id.btn_submit).setOnClickListener(this);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
    }

    private void callResultActiivty(HData result)
    {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(ResultActivity.EXTRA_RESULT, result);
        startActivity(intent);
    }

    @Override
    public void onClick(View view)
    {
        if(view.getId() == R.id.btn_submit)
        {
            submit(etEMail.getText().toString().trim(),
                   etName.getText().toString().trim(),
                   etPhone.getText().toString().trim());
        }
    }

    private void submit(String email, String name, String phone)
    {
        progressBar.setVisibility(View.VISIBLE);

        HashMap<String, String> params = new HashMap<>();
        params.put("email", email);
        params.put("name", name);
        params.put("gsm", phone);
        new GunIt("https://getir-bitaksi-hackathon.herokuapp.com/getElements", JSONParser.FETCH_METHOD.POST, params, new OnGunItCallback()
        {
            @Override
            public void onReceive(JSONObject data, Exception exp)
            {
                progressBar.setVisibility(View.GONE);
                HData bgData;
                if(exp == null)
                {
                    Log.e("HMT", data.toString());
                    try
                    {
                        bgData = (HData) new ClassParser().jsonToClass(data, HData.class);
                    }
                    catch (Exception e)
                    {
                        Log.w("HMT", e);
                        bgData = new HData();
                        bgData.setCode(-1);
                        bgData.setMessage(e.getMessage());
                    }
                }
                else
                {
                    Log.w("HMT", exp);
                    bgData = new HData();
                    bgData.setCode(-1);
                    bgData.setMessage(exp.getMessage());
                }
                callResultActiivty(bgData);
            }
        }).execute();
    }
}
