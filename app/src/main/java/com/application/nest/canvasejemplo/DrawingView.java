package com.application.nest.canvasejemplo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by Nest on 6/1/2017.
 */

public class DrawingView extends View {


    // setup initial color
    private int paintColor = Color.BLUE;
    private int stroke = 10;
    // defines paint and canvas
    private Paint drawPaint;
    private List<Point> circlePoints;
    private ArrayList <Path> paths;
    private Path path = new Path();
    private int colors;
    private Canvas canvas;
    private List <Stroke> _allStrokes; //all strokes that need to be drawn
    private SparseArray<Stroke> _activeStrokes; //use to retrieve the currently drawn strokes
    private Random _rdmColor = new Random();



    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        _allStrokes = new ArrayList<Stroke>();
        _activeStrokes = new SparseArray<Stroke>();
        setFocusable(true);
        setFocusableInTouchMode(true);
        //setBackgroundResource(R.drawable.cat);
        setupPaint(paintColor,5);
        circlePoints = new ArrayList<Point>();
        canvas=null;
    }

    public int getPaintColor() {
        return paintColor;
    }

    public void setPaintColor(int paintColor) {
        this.paintColor = paintColor;
    }

    public void setStroke(int stroke) {
        this.stroke = stroke;
    }

    public void setupPaint(int color , int stroke ) {
        colors=color;
        drawPaint = new Paint();
        drawPaint.setColor(color);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(stroke);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected void onDraw(Canvas canvas) {
       // super.onDraw(canvas);
        //this.canvas = canvas;
        //canvas.save();
        if (_allStrokes != null) {
            for (Stroke stroke: _allStrokes) {
                if (stroke != null) {
                    Path path = stroke.getPath();
                    Paint painter = stroke.getPaint();
                    if ((path != null) && (painter != null)) {
                        canvas.drawPath(path, painter);
                    }
                }
            }
        }
      /*  canvas.drawCircle(50,50,20, drawPaint); // (x,y, radio de circulo)
        drawPaint.setColor(Color.GREEN);
        canvas.drawCircle(50,150,20, drawPaint);
        drawPaint.setColor(Color.BLUE);*/

        //for(Point p : circlePoints){
        //canvas.drawCircle(p.x, p.y, 20 , drawPaint); circulos con espacios}

       // canvas.drawPath(path,drawPaint);
    }

    private ArrayList<StorePathPojo> store = new ArrayList<>();
   /* public void cambiaColor(){
            for (int a=0; a<store.size(); a++){
                Path path = store.get(a).getPath();
                int color = store.get(a).getColor();
                drawPaint=new Paint();
                drawPaint.setColor(color);
                drawPaint.setStrokeWidth(2);
                drawPaint.setAntiAlias(true);
                drawPaint.setStyle(Paint.Style.STROKE);
                drawPaint.setStrokeJoin(Paint.Join.ROUND);
                drawPaint.setStrokeCap(Paint.Cap.ROUND);
                if((path!=null)&&(drawPaint!=null)){
                    canvas.drawPath(path,drawPaint);
                }
            }


    }*/



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final int action = event.getActionMasked();
        final int pointerCount = event.getPointerCount();

        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                pointDown((int)event.getX(), (int)event.getY(), event.getPointerId(0));
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                for (int pc = 0; pc < pointerCount; pc++) {
                    pointMove((int) event.getX(pc), (int) event.getY(pc), event.getPointerId(pc));
                }
                break;
            }
            case MotionEvent.ACTION_POINTER_DOWN: {
                for (int pc = 0; pc < pointerCount; pc++) {
                    pointDown((int)event.getX(pc), (int)event.getY(pc), event.getPointerId(pc));
                }
                break;
            }
            case MotionEvent.ACTION_UP: {
                break;
            }
            case MotionEvent.ACTION_POINTER_UP: {
                break;
            }
        }
        invalidate();
        return true;
    }

    private void pointDown(int x, int y, int id) {
        //create a paint with random color
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(stroke);
        paint.setColor(paintColor);

        //create the Stroke
        Point pt = new Point(x, y);
        Stroke stroke = new Stroke(paint);
        stroke.addPoint(pt);
        _activeStrokes.put(id, stroke);
        _allStrokes.add(stroke);
    }

    private void pointMove(int x, int y, int id) {
        //retrieve the stroke and add new point to its path
        Stroke stroke = _activeStrokes.get(id);
        if (stroke != null) {
            Point pt = new Point(x, y);
            stroke.addPoint(pt);
        }
    }
}

























   /* @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();
        //circlePoints.add(new Point(Math.round(touchX), Math.round(touchY)));  // dibuja circulos con espacios

        switch (event.getAction()) {      //dibuja trazos continuos
            case MotionEvent.ACTION_DOWN:
                path.moveTo(touchX, touchY);
                invalidate();
                break;
                //return true;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(touchX, touchY);
                invalidate();
                break;
                /*StorePathPojo sts = new StorePathPojo(path,colors);
                store.add(sts);
                break;
            default:
                return false;

        }


        // indicate view should be redrawn
        //postInvalidate();
        return true;
    }*/
//}
