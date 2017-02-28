package hmt.hckthn.cm.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class HShapeDrawView extends View
{
    private List<HElement> elements;
    private Paint paint;

    public HShapeDrawView(Context context)
    {
        super(context);
        initialize();
    }

    public HShapeDrawView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        initialize();
    }

    public HShapeDrawView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    public void setElements(List<HElement> elements_)
    {
        if(elements != null) elements.clear();
        else elements = new ArrayList<>();
        elements.addAll(elements_);
        invalidate();
    }

    private void initialize()
    {
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        if(elements != null)
        {
            for(HElement element: elements)
            {
                paint.setColor(Color.parseColor("#"+element.getColor()));
                if(element.getType().equals("circle"))
                {
                    Log.e("HMT", "Circle: " + element.getXPosition() + "x" + element.getYPosition());
                    canvas.drawCircle(
                            element.getXPosition(),
                            element.getYPosition(),
                            element.getRadius(),
                            paint);
                }
                else if(element.getType().equals("rectangle"))
                {
                    Log.e("HMT", "RECT: " + element.getXPosition() + "x" + element.getYPosition());
                    canvas.drawRect(
                            element.getXPosition(),
                            element.getYPosition(),
                            element.getWidth() + element.getXPosition(),
                            element.getHeight() + element.getYPosition(),
                            paint);
                }
            }
        }
    }
}
