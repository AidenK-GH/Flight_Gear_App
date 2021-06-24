package com.flight_gear_app;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.NonNull;

public class Joystick extends SurfaceView implements SurfaceHolder.Callback, View.OnTouchListener {

    public float centerX;
    public float centerY;
    public float baseRadius;
    public float hatRadius;
    private final int ratio = 5;
    private JoystickListener joystickCallback;

    // contactors
    public Joystick(Context context){
        super(context);
        getHolder().addCallback(this);
        setOnTouchListener(this);
        if(context instanceof JoystickListener){
            joystickCallback = (JoystickListener) context;
        }
    }
    public Joystick(Context context, AttributeSet attributes){
        super(context, attributes);
        getHolder().addCallback(this);
        setOnTouchListener(this);
        if(context instanceof JoystickListener){
            joystickCallback = (JoystickListener) context;
        }
    }
    public Joystick(Context context, AttributeSet attributes, int style){
        super(context, attributes, style);
        getHolder().addCallback(this);
        setOnTouchListener(this);
        if(context instanceof JoystickListener){
            joystickCallback = (JoystickListener) context;
        }
    }

    private void setupDimensions(){
        centerX = getWidth() / 2;
        centerY = getHeight() / 2;
        baseRadius = Math.min(getWidth(), getHeight()) / 3;
        hatRadius = Math.min(getWidth(), getHeight()) / 5;
    }
    //this will drew the joystick
    private void drewJoystick(float newX, float newY){
        if(getHolder().getSurface().isValid()){
            Canvas myCanvas = this.getHolder().lockCanvas(); // stuff to drew
            Paint colors = new Paint();
            myCanvas.drawColor(Color.WHITE/*Color.TRANSPARENT*/, PorterDuff.Mode.CLEAR); //set BG
            colors.setARGB(255, 50, 50, 50); // joystick base color
            myCanvas.drawCircle(centerX, centerY, baseRadius, colors);
            colors.setARGB(255, 0, 0, 255); // joystick color
            myCanvas.drawCircle(newX, newY, hatRadius, colors);
            getHolder().unlockCanvasAndPost(myCanvas); // put the canvas on the surfaceView
        }
    }
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        setupDimensions();
        drewJoystick(centerX, centerY);
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {}
    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {}

    public interface JoystickListener{
        void onJoystickMoved(float elevator, float aileron, int id) throws InterruptedException;
    }

    public boolean onTouch(View v, MotionEvent e){
        if(v.equals(this)){
            if(e.getAction() != e.ACTION_UP){
                float displacement = (float) Math.sqrt(
                        (Math.pow(e.getX() - centerX, 2)) + Math.pow(e.getY() - centerY, 2));
                if(displacement < baseRadius){
                    // we are in the base of the joystick so there is
                    // no worry that the joystick goes out of the bounds of the base
                    drewJoystick(e.getX(), e.getY());
                    try {
                        float scaledX = (e.getX() - centerX) / baseRadius;
                        float scaledY = (e.getY() - centerY) / baseRadius;
                        joystickCallback.onJoystickMoved(scaledX, scaledY, getId());
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                } else {
                    float ratio = baseRadius / displacement;
                    float constrainedX = centerX + (e.getX() - centerX) * ratio;
                    float constrainedY = centerY + (e.getY() - centerY) * ratio;
                    drewJoystick(constrainedX, constrainedY);
                    try {
                        joystickCallback.onJoystickMoved(constrainedX, constrainedY, getId());
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                }
            } else {
                drewJoystick(centerX, centerY);
                try {
                    joystickCallback.onJoystickMoved(0, 0, getId());
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
        }
        return true;
    }
}
