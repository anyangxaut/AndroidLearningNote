package com.imooc.systemwidget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;
/**
 * 对现有控件进行拓展
 */
public class MyTextView extends TextView {

    private final static String TAG = "MyTextView";
    private Paint mPaint1, mPaint2;

    public MyTextView(Context context) {
        super(context);
        initView();
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MyTextView(Context context, AttributeSet attrs,
                      int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mPaint1 = new Paint();
        mPaint1.setColor(getResources().getColor(
                android.R.color.holo_blue_light));
        mPaint1.setStyle(Paint.Style.FILL);
        mPaint2 = new Paint();
        mPaint2.setColor(Color.YELLOW);
        mPaint2.setStyle(Paint.Style.FILL);
    }

    // 因为TextView类对onMeasure方法进行了重写，因此当MyTextView继承了TextView类后，不需重写onMeasure也可以实现Wrap_content效果
    @Override
    protected void onDraw(Canvas canvas) {
        Log.d(TAG,  "MeasuredWidth:" + getMeasuredWidth() + ",MeasuredHeight:" +
                getMeasuredHeight());
        // 绘制外层矩形
        canvas.drawRect(
                0,
                0,
                getMeasuredWidth(),
                getMeasuredHeight(),
                mPaint1);
        // 绘制内层矩形
        canvas.drawRect(
                10,
                10,
                getMeasuredWidth() - 10,
                getMeasuredHeight() - 10,
                mPaint2);
        // 锁定画布--在对画布进行旋转，平移，缩放操作情况下，我们需要采用save和restore对画布进行保存和恢复，save与restore成对出现
        canvas.save();
        // 绘制文字前平移10像素
        canvas.translate(10, 0);
        // 父类完成的方法，即绘制文本
        super.onDraw(canvas);
        // 恢复画布
        canvas.restore();
    }
}
