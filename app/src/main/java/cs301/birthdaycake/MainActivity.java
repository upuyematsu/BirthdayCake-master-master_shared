package cs301.birthdaycake;
import android.view.View;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private CakeView myCakeView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);

        myCakeView = findViewById(R.id.cakeview);
        final CakeController cakeController = new CakeController(myCakeView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(cakeController);

        Switch s = findViewById(R.id.switch2);
        s.setOnCheckedChangeListener(cakeController);

        SeekBar mySeekBar = findViewById(R.id.seekBar);
        mySeekBar.setOnSeekBarChangeListener(cakeController);


    }


        public void goodbye(View button){

        Log.i("button", "goodbye");
    }
}
