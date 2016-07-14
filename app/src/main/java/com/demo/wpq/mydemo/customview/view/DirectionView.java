package com.demo.wpq.mydemo.customview.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.demo.wpq.mydemo.R;

/**
 * Desc: 圆形方向盘：前后左右
 *
 * Created by wpq on 16/7/12.
 */
public class DirectionView extends View {

    /**
     * 方向，暂时为前后左右
     */
    public final class Direction{
        public static final int DEFAULT = -1;
        public static final int LEFT = 0;
        public static final int RIGHT = 1;
        public static final int UP = 2;
        public static final int DOWN = 3;
    }

    private final String LOG = "DirectionView";

    private Context context;
    private Bitmap bmpNormal, bmpLeft, bmpRight, bmpUp, bmpDown;
    private Bitmap mBitmap;
    private Matrix matrix;

    /** 直径 */
    private int diameter;
    private Paint paint;

    private int direction = Direction.DEFAULT;

    public DirectionView(Context context) {
        this(context, null);
    }

    public DirectionView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DirectionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.context = context;
        bmpNormal = BitmapFactory.decodeResource(context.getResources(), R.drawable.direction_control_normal);
        bmpLeft = BitmapFactory.decodeResource(context.getResources(), R.drawable.direction_control_left);
        bmpRight = BitmapFactory.decodeResource(context.getResources(), R.drawable.direction_control_right);
        bmpUp = BitmapFactory.decodeResource(context.getResources(), R.drawable.direction_control_up);
        bmpDown = BitmapFactory.decodeResource(context.getResources(), R.drawable.direction_control_down);

//        diameter = dp2px(context, 140);
//        matrix = new Matrix();
//        matrix.setScale(diameter / (float) bmpNormal.getWidth(), diameter / (float) bmpNormal.getHeight());
//        mBitmap = Bitmap.createBitmap(bmpNormal, 0, 0, bmpNormal.getWidth(), bmpNormal.getHeight(), matrix, false);
        diameter = bmpNormal.getWidth();
        mBitmap = Bitmap.createBitmap(bmpNormal, 0, 0, diameter, diameter);

        paint = new Paint();
        paint.setStrokeCap(Paint.Cap.ROUND); //设置笔刷类型为圆滑状
        paint.setAntiAlias(true); //打开抗锯齿
        paint.setDither(true); //打开抗抖动

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public DirectionView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(diameter, diameter);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mBitmap, 0, 0, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e(LOG, "ACTION_DOWN: " + event.getX() +","+ event.getY());
                updateDirection(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
//                Log.e(LOG, "ACTION_MOVE: " + event.getX() +","+ event.getY());

                break;
            case MotionEvent.ACTION_UP:
                Log.e(LOG, "ACTION_UP: " + event.getX() +","+ event.getY());
                resetBackground();
                break;
        }
        return super.onTouchEvent(event);
    }

    private void updateDirection(float x, float y){
        /**把整个方向盘圆放到坐标系中，因为Android坐标系是左上角为原点，x轴向右，y轴向下，
           因此以左上角为坐标原点，得到两条垂直交叉的直线，两条直线的函数为: y = x 和 y = -x + diameter
         */
        if(x + y < diameter && x < y){ //left
            direction = Direction.LEFT;
            mBitmap = bmpLeft;
        }else if(x + y > diameter && x > y){ //right
            direction = Direction.RIGHT;
            mBitmap = bmpRight;
        }else if(x + y < diameter && x > y){ //up
            direction = Direction.UP;
            mBitmap = bmpUp;
        }else if(x + y > diameter && x < y){ //down
            direction = Direction.DOWN;
            mBitmap = bmpDown;
        }

//        mBitmap = Bitmap.createBitmap(mBitmap, 0, 0, mBitmap.getWidth(), mBitmap.getHeight(), matrix, false);
        invalidate();
        this.onDirectionChangedListener.onDirectionChanged(direction);
    }

    /**
     * MotionEvent.ACTION_UP时重置
     */
    private void resetBackground(){
        direction = Direction.DEFAULT;
//        mBitmap = Bitmap.createBitmap(bmpNormal, 0, 0, bmpNormal.getWidth(), bmpNormal.getHeight(), matrix, false);
        mBitmap = bmpNormal;
        invalidate();
    }

    public interface OnDirectionChangedListener{
        /**
         * 点方向盘对应的方向时触发
         * @param currentDirection
         */
        void onDirectionChanged(int currentDirection);
    }

    public void setOnDirectionChangedListener(OnDirectionChangedListener onDirectionChangedListener){
        this.onDirectionChangedListener = onDirectionChangedListener;
    }

    private OnDirectionChangedListener onDirectionChangedListener;

    /**
     * dp to px
     *
     * @param context
     * @param dpValue
     * @return
     */
    private int dp2px(Context context, float dpValue) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * density + 0.5f);
    }
}
