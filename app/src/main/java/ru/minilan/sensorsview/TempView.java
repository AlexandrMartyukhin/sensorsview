package ru.minilan.sensorsview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TempView extends LinearLayout {

    private TextView textViewTempName;
    private TextView textViewTempValue;
    private int tempValue;


    public TempView(Context context) {
        super(context);
        init();
    }

    public TempView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TempView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        textViewTempName = new TextView(getContext());
        textViewTempValue = new TextView(getContext());
        textViewTempName.setText(getResources().getString(R.string.temperature));
        textViewTempValue.setText(String.valueOf(tempValue));
        textViewTempName.setTextSize(25);
        textViewTempValue.setTextSize(25);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT);
        params.weight = 1;

        this.setOrientation(HORIZONTAL);
        this.addView(textViewTempName, params);
        this.addView(textViewTempValue, params);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

//        int width = 0;
//        int height = 0;
//
//        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
//        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
//
//        if (widthMode == MeasureSpec.AT_MOST) {
//            width = Math.min(400, widthSize);
//        } else if (widthMode == MeasureSpec.EXACTLY) {
//            width = Math.min(400, widthSize);
//        } else if (widthMode == MeasureSpec.UNSPECIFIED) {
//            width = widthSize;
//        }
//
//        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
//
//        if (heightMode == MeasureSpec.AT_MOST) {
//            height = Math.min(80, heightSize);
//        } else if (heightMode == MeasureSpec.EXACTLY) {
//            height = Math.min(80, heightSize);
//        } else if (heightMode == MeasureSpec.UNSPECIFIED) {
//            height = heightSize;
//        }
//
//        setMeasuredDimension(width,height);
    }

    public void setTemp(int tempValue) {
        this.tempValue = tempValue;
        textViewTempValue.setText(String.valueOf(tempValue));
    }
}
