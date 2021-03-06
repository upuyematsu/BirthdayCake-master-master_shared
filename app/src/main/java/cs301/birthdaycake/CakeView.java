package cs301.birthdaycake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class CakeView extends SurfaceView {

    /* These are the paints we'll use to draw the birthday cake below */
    Paint cakePaint = new Paint();
    Paint frostingPaint = new Paint();
    Paint candlePaint = new Paint();
    Paint outerFlamePaint = new Paint();
    Paint innerFlamePaint = new Paint();
    Paint wickPaint = new Paint();

    /* These constants define the dimensions of the cake.  While defining constants for things
        like this is good practice, we could be calculating these better by detecting
        and adapting to different tablets' screen sizes and resolutions.  I've deliberately
        stuck with hard-coded values here to ease the introduction for CS371 students.
     */
    public static final float cakeTop = 400.0f;
    public static final float cakeLeft = 100.0f;
    public static final float cakeWidth = 1200.0f;
    public static final float layerHeight = 200.0f;
    public static final float frostHeight = 50.0f;
    public static final float candleHeight = 300.0f;
    public static final float candleWidth = 80.0f;
    public static final float wickHeight = 30.0f;
    public static final float wickWidth = 6.0f;
    public static final float outerFlameRadius = 30.0f;
    public static final float innerFlameRadius = 15.0f;

    private CakeModel cakeModel;

    /**
     * ctor must be overridden here as per standard Java inheritance practice.  We need it
     * anyway to initialize the member variables
     */
    public CakeView(Context context, AttributeSet attrs) {
        super(context, attrs);

        //initializing var
        this.cakeModel = new CakeModel();

        //This is essential or your onDraw method won't get called
        setWillNotDraw(false);

        //Setup our palette
        cakePaint.setColor(0xFFC755B5);  //violet-red
        cakePaint.setStyle(Paint.Style.FILL);
        frostingPaint.setColor(0xFFFFFACD);  //pale yellow
        frostingPaint.setStyle(Paint.Style.FILL);
        candlePaint.setColor(0xFF32CD32);  //lime green
        candlePaint.setStyle(Paint.Style.FILL);
        outerFlamePaint.setColor(0xFFFFD700);  //gold yellow
        outerFlamePaint.setStyle(Paint.Style.FILL);
        innerFlamePaint.setColor(0xFFFFA500);  //orange
        innerFlamePaint.setStyle(Paint.Style.FILL);
        wickPaint.setColor(Color.BLACK);
        wickPaint.setStyle(Paint.Style.FILL);

        setBackgroundColor(Color.WHITE);  //better than black default

    }
    public void getCord(Canvas canvas, float x, float y){
        x = this.cakeModel.x;
        y = this.cakeModel.y;
        String xString = String.valueOf(x);
        String yString = String.valueOf(y);
        String combinedStrings = xString + ", " + yString;
        if(this.cakeModel.hasTouched == true) {
            canvas.drawText(combinedStrings, 1400, 600, innerFlamePaint);
        }

    }

    //getter for CakeModel
    public CakeModel getCakeModel(){
        return this.cakeModel;
    }



    /**
     * draws a candle at a specified position.  Important:  the left, bottom coordinates specify
     * the position of the bottom left corner of the candle
     */
    public void drawCandle(Canvas canvas, float left, float bottom) {
        //canvas.drawRect(cakeWidth/3, bottom-candleHeight, cakeWidth/3 + candleWidth, bottom, candlePaint);
       // canvas.drawRect((cakeWidth*2)/3, bottom-candleHeight, (cakeWidth*2)/3 + candleWidth, bottom, candlePaint);
        if (this.cakeModel.hasCandles == true){

            int count = 0;
            float widthNum = this.cakeModel.candleNum+1;
            int x = 1;
                while(count < this.cakeModel.candleNum){


                canvas.drawRect((cakeWidth*x)/widthNum+candleWidth, bottom - candleHeight, (cakeWidth*x)/widthNum+candleWidth*2, bottom, candlePaint);



                if (this.cakeModel.lit == true) {
                    //draw the outer flame
                    float flameCenterX = (cakeWidth*x)/widthNum+candleWidth+(candleWidth/2) - wickWidth/2;
                    float flameCenterY = bottom - wickHeight - candleHeight - outerFlameRadius / 3;

                    canvas.drawCircle(flameCenterX, flameCenterY, outerFlameRadius, outerFlamePaint);
                    //draw the inner flame
                    flameCenterY += outerFlameRadius / 3;
                    canvas.drawCircle(flameCenterX, flameCenterY, innerFlameRadius, innerFlamePaint);
                }


                //draw the wick
                float wickLeft = (cakeWidth*x)/widthNum+candleWidth+(candleWidth/2) - wickWidth;
                float wickTop = bottom - wickHeight - candleHeight;
                canvas.drawRect(wickLeft, wickTop, wickLeft + wickWidth, wickTop + wickHeight, wickPaint);
                    x++;
                count++;

            }

        }
    }

   /* public void drawBalloon(Canvas canvas, float centerX, float centerY){
        Paint balloonPaint = new Paint();
        balloonPaint.setColor(Color.BLUE);
        centerX = this.cakeModel.x;
        centerY = this.cakeModel.y;
        float balloonWidth = 20;
        float balloonHeight = 30;

            canvas.drawOval(centerX - balloonWidth, centerY - balloonHeight,
                    centerX + balloonWidth, centerY + balloonHeight, balloonPaint);


    }*/

    public void drawBalloon(Canvas canvas, float x, float y){
        x = this.cakeModel.x;
        y = this.cakeModel.y;
        float balloonWidth = 20;
        float balloonHeight = 30;
        Paint balloonPaint = new Paint();
        balloonPaint.setColor(Color.BLUE);
        if(this.cakeModel.hasTouched == true) {
            canvas.drawOval(x - balloonWidth, y - balloonHeight,
                    x + balloonWidth, y + balloonHeight, balloonPaint);
        }
    }




    /**
     * onDraw is like "paint" in a regular Java program.  While a Canvas is
     * conceptually similar to a Graphics in javax.swing, the implementation has
     * many subtle differences.  Show care and read the documentation.
     *
     * This method will draw a birthday cake
     */
    @Override
    public void onDraw(Canvas canvas)
    {
        //top and bottom are used to keep a running tally as we progress down the cake layers
        float top = cakeTop;
        float bottom = cakeTop + frostHeight;

        //Frosting on top
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, frostingPaint);
        top += frostHeight;
        bottom += layerHeight;

        //Then a cake layer
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, cakePaint);
        top += layerHeight;
        bottom += frostHeight;

        //Then a second frosting layer
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, frostingPaint);
        top += frostHeight;
        bottom += layerHeight;

        //Then a second cake layer
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, cakePaint);

        //Now a candle in the center
        drawCandle(canvas, cakeLeft + cakeWidth/2 +200 - candleWidth, cakeTop);




           drawBalloon(canvas, this.cakeModel.x, this.cakeModel.y);
        getCord(canvas, this.cakeModel.x, this.cakeModel.y);

    }//onDraw

}//class CakeView

