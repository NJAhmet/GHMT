package hmt.hckthn.cm.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import hmt.hckthn.cm.hckthn.R;

public class ResultActivity extends AppCompatActivity
{

    public static final String EXTRA_RESULT = "result";

    private BGData result;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        TextView tvInfo, tvInviteCode;
        Vuuu vuu;

        tvInfo = (TextView) findViewById(R.id.tv_hmt_result);
        vuu = (Vuuu) findViewById(R.id.vuu_hmt_result);
        tvInviteCode = (TextView) findViewById(R.id.tv_hmt_invite_code_result);

        try
        {
            result = (BGData) getIntent().getSerializableExtra(EXTRA_RESULT);
        }
        catch (Exception exp)
        {
            Log.w("HMT", exp);
        }

        String rr ;
        if(result != null && result.getCode() == 0)
        {
            Log.e("HMT", "__1__");
            vuu.setVisibility(View.VISIBLE);
            vuu.setElements(result.getElements());
            rr = result.getMessage();
            tvInviteCode.setText(result.getInviteCode());
        }
        else
        {
            Log.e("HMT", "__2__");
            vuu.setVisibility(View.INVISIBLE);
            rr = result.getMessage();
        }

        tvInfo.setText(rr);
    }
}
