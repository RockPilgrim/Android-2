package my.lessons.lesson2;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

public class SimpleObject extends View {

    private final int DEFAULT_COLOR = Color.BLACK;
    private final int DEFAUL_RADIUS = 100;
    private int posY = 200;
    private int posX = 200;
    private Paint paint;
    private int radius;
    private Canvas canvas;
    private int color;


    public SimpleObject(Context context) {
        super(context);
        init();
    }

    public SimpleObject(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttr(context,attrs,0,0);
        init();
    }

    public SimpleObject(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(context,attrs,defStyleAttr,0);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SimpleObject(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initAttr(context,attrs,defStyleAttr,defStyleRes);
        init();
    }

    private void initAttr(Context context,AttributeSet attrs, int defStyleAttr, int defStyleRes) {

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SimpleObject,defStyleAttr,defStyleRes);
///////////??????????????????????///////////////////
        setRadius(typedArray.getInt(R.styleable.SimpleObject_radius,DEFAUL_RADIUS));
        setColor(typedArray.getColor(R.styleable.SimpleObject_color, DEFAULT_COLOR));
///////////??????????????????????///////////////////
        typedArray.recycle();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
    }

    private void setColor(int color) {
        this.color = color;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;
        canvas.drawCircle(posX, posY, radius, paint);
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
