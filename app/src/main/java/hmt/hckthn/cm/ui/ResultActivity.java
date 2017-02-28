package hmt.hckthn.cm.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import hmt.hckthn.cm.hckthn.R;

public class ResultActivity extends Activity
{

    public static final String EXTRA_RESULT = "result";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        HData result;
        TextView tvInfo, tvInviteCode;
        HShapeDrawView hShapeDrawView;

        tvInfo = (TextView) findViewById(R.id.tv_result_message);
        hShapeDrawView = (HShapeDrawView) findViewById(R.id.vuu_hmt_result);
        tvInviteCode = (TextView) findViewById(R.id.tv_invite_code);

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
