package au.com.ubd_ders.ubdtext;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.TypedValue;
import android.view.WindowManager;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


public class FridayPickerActivity extends AppCompatActivity {

    //Widgets variables
    private TextView tvTitleMain;
    private TextView tvSurahTitle;
    private TextView tvAyahTitle;
    private TextView tvMainText;
    private NumberPicker surahNumberPicker;
    private NumberPicker ayahNumberPicker;

    //Data Variables
    private String apiLink = "http://api.alquran.cloud/ayah/2:255/ug.saleh";
    private String ayahFromJson = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friday_picker);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        if (!isNetworkAvailable()) {
            Toast.makeText(this,
                    "No Internet Connection and desired Text cannot be build!!!",
                         Toast.LENGTH_LONG).show();
        }

        //initializing widgedts
        initializeTv();
        initializeNumberPickers();

        surahNumberPicker.setMinValue(1);
        surahNumberPicker.setMaxValue(114);
        surahNumberPicker.setWrapSelectorWheel(true);
        ayahNumberPicker.setMinValue(1);
        ayahNumberPicker.setMaxValue(7);
        ayahNumberPicker.setWrapSelectorWheel(true);
        SurahMappingAyah.populateMapper();

        surahNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldVal, int newVal) {
                ayahNumberPicker.setMinValue(1);
                ayahNumberPicker.setMaxValue(SurahMappingAyah.getMapperValue(newVal));
                ayahNumberPicker.setWrapSelectorWheel(true);
            }
        });

        ayahNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldVal, int newVal) {
                apiLink = "http://api.alquran.cloud/ayah/" + surahNumberPicker.getValue()
                        + ":" + ayahNumberPicker.getValue() + "/ug.saleh";
                if(isNetworkAvailable()) {
                    getAyahFromJsonAPI();
                } else {
                    Toast.makeText(getApplicationContext(), "Please Check Your Internet Connection!!!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void getAyahFromJsonAPI() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("تۇپتۇرە ئاداش ھە...");
        //builder.setTitle("ئۇيغۇر بىرلىكى دەرسخانىسى");
        final AlertDialog dialog = builder.create();
        dialog.show();
        final Handler handler = new Handler();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                ayahFromJson = JsonProcessor.getVerse(JsonProcessor.getJson(apiLink));
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        tvMainText.setText(ayahFromJson);
                        dialog.dismiss();
                    }
                });
            }
        });
        thread.start();
    }


    private void initializeTv() {
        tvTitleMain = findViewById(R.id.tvFridayPickerTitle);
        tvAyahTitle = findViewById(R.id.tvAyahTitle);
        tvSurahTitle = findViewById(R.id.tvSurahTitle);
        tvMainText = findViewById(R.id.tvMainText);

        //get fonts from Assets folder
        Typeface uykuFont = Typeface.createFromAsset(getAssets(), "uyku.ttf");
        Typeface kesmeFont = Typeface.createFromAsset(getAssets(), "kesme.ttf");

        //set fonts and size for text views
        tvTitleMain.setTypeface(uykuFont);
        tvTitleMain.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);

        tvSurahTitle.setTypeface(kesmeFont);
        tvSurahTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 26);

        tvAyahTitle.setTypeface(kesmeFont);
        tvAyahTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 26);

        tvMainText.setMovementMethod(new ScrollingMovementMethod());
    }

    private void initializeNumberPickers(){
        surahNumberPicker = findViewById(R.id.numberPickerSurah);
        ayahNumberPicker = findViewById(R.id.numberPickerAyah);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
