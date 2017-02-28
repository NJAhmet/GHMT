package hmt.hckthn.cm.ui;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import hmt.hckthn.cm.hckthn.R;

public class ResultActivity extends Activity
{

    public static final String EXTRA_RESULT = "result";

    private HData result;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        TextView tvInfo, tvInviteCode;
        HShapeDrawView hShapeDrawView;

        tvInfo = (TextView) findViewById(R.id.tv_hmt_result);
        hShapeDrawView = (HShapeDrawView) findViewById(R.id.vuu_hmt_result);
        tvInviteCode = (TextView) findViewById(R.id.tv_hmt_invite_code_result);

        try
        {
            result = (HData) getIntent().getSerializableExtra(EXTRA_RESULT);
        }
        catch (Exception exp)
        {
            result = new HData();
            result.setCode(-1);
            result.setMessage("No Extra");
        }

        String rr ;
        if(result.getCode() != -1)
        {
            hShapeDrawView.setVisibility(View.VISIBLE);
            hShapeDrawView.setElements(result.getElements());
            rr = result.getMessage();
            tvInviteCode.setText(result.getInviteCode());
        }
        else
        {
            hShapeDrawView.setVisibility(View.INVISIBLE);
            rr = result.getMessage();
        }

        tvInfo.setText(rr);
    }
}
