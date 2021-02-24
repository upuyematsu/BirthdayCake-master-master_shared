package cs301.birthdaycake;

import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;

public class CakeController implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, CompoundButton.OnCheckedChangeListener, View.OnTouchListener {

    private CakeView cakeView;
    private CakeModel cakeModel;


    public CakeController(CakeView cakeView){
        this.cakeView = cakeView;
        this.cakeModel = this.cakeView.getCakeModel();
    }

    public void onClick(View v){
        this.cakeModel.lit = false;
        cakeView.invalidate();
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked){
        this.cakeModel.hasCandles = isChecked;
        cakeView.invalidate();
    }

    public void onProgressChanged(SeekBar seekBar, int i, boolean b){
            this.cakeModel.candleNum = i;
            cakeView.invalidate();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        this.cakeModel.hasTouched = true;
        this.cakeModel.x = motionEvent.getX();
        this.cakeModel.y = motionEvent.getY();
        cakeView.invalidate();
        return true;
    }
}

