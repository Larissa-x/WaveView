package com.larissa.waveview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class WaveView extends View {
    private Paint paint;
    private int maxWidth = 360;
    // 是否运行
    private boolean isStarting = false;
    private List<String> alphaList = new ArrayList<String>();
    private List<String> startWidthList = new ArrayList<String>();

    public WaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WaveView(Context context) {
        super(context);
        init();
    }

    private void init() {
        paint = new Paint();
// 设置博文的颜色
//        paint.setColor(0x0059ccf5);
        paint.setColor(Color.argb(153,19,154,255));
        alphaList.add("153");// 圆心的不透明度
        startWidthList.add("0");
    }

    /**
     * 设置波纹颜色
     * @param alpha
     * @param red
     * @param green
     * @param blue
     */
    public void setWaveColor(int alpha,int red, int green ,int blue){
        if (paint!=null){
            paint.setColor(Color.argb(alpha,red,green,blue));
        }
        invalidate();
    }


    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setBackgroundColor(Color.TRANSPARENT);// 颜色：完全透明
// 依次绘制 同心圆
        for (int i = 0; i < alphaList.size(); i++) {
            int alpha = Integer.parseInt(alphaList.get(i));
// 圆半径
            int startWidth = Integer.parseInt(startWidthList.get(i));
            paint.setAlpha(alpha);
// 这个半径决定你想要多大的扩散面积
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, startWidth + 50,
                    paint);
// 同心圆扩散
            if (isStarting && alpha > 0 && startWidth < maxWidth) {
                alphaList.set(i, (alpha - 1) + "");
                startWidthList.set(i, (startWidth + 2) + "");
            }
        }
        if (isStarting
                && Integer
                .parseInt(startWidthList.get(startWidthList.size() - 1)) == maxWidth / 3) {
            alphaList.add("153");
            startWidthList.add("0");
        }
// 同心圆数量达到10个，删除最外层圆
        if (isStarting && startWidthList.size() == 10) {
            startWidthList.remove(0);
            alphaList.remove(0);
        }
// 刷新界面
        invalidate();
    }

    // 执行动画
    public void start() {
        isStarting = true;
    }

    // 停止动画
    public void stop() {
        isStarting = false;
    }

    // 判断是都在不在执行
    public boolean isStarting() {
        return isStarting;
    }
}
