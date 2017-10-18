package au.com.ubd_ders.ubdtext;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.TypeVariable;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private TextView tvHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvHeader= (TextView) findViewById(R.id.header);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "uyku.ttf");
        tvHeader.setTypeface(typeface);
        tvHeader.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
    }

    public void onFridaySMSClick(View view){
        Intent intent = new Intent(this, FridayPickerActivity.class);
        startActivity(intent);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            tvHeader.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            tvHeader.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
        }
    }
}
