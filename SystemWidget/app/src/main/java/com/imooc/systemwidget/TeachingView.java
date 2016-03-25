package com.imooc.systemwidget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * View的测量与绘制
 */
public class TeachingView extends View {

    private final static String TAG = "TeachingView";

    public TeachingView(Context context) {
        super(context);
    }

    public TeachingView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TeachingView(Context context, AttributeSet attrs,
                        int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec,
                             int heightMeasureSpec) {
        setMeasuredDimension(
                measureWidth(widthMeasureSpec),
                measureHeight(heightMeasureSpec));
    }

    private int measureWidth(int measureSpec) {
        int result = 0;
        // 从measureSpec中提取出测量模式和大小
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        Log.d(TAG, "measureWidth--specMode:" + specMode + ",specSize:" + specSize);

        // 通过测量模式进行不同的处理：Exactly-直接使用原本MeasureSpec的width和height
        // At_most-比较我们自己设定值和MeasureSpec中较小值进行设定（如果不重写该方法，该模式下将填充整个父控件大小）
        // Unspecified--直接设置我们设定好的值
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = 200;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    private int measureHeight(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        Log.d(TAG, "measureHeight--specMode:" + specMode + ",specSize:" + specSize);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = 200;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 通过指定颜色填充整个View大小
        canvas.drawColor(Color.GRAY);
        int width = getWidth();
        int height = getHeight();
        Log.d(TAG, "onDraw--width : " + width + " height : " + height);
    }
}
