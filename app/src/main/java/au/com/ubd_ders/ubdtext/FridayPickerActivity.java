package au.com.ubd_ders.ubdtext;

import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.NumberPicker;
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

        //initializing widgedts
        initializeTv();
        initializeNumberPickers();

        surahNumberPicker.setMinValue(1);
        surahNumberPicker.setMaxValue(114);
        surahNumberPicker.setWrapSelectorWheel(true);
        surahNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                SurahMappingAyah.populateMapper();
                ayahNumberPicker.setMinValue(1);
                ayahNumberPicker.setMaxValue(SurahMappingAyah.getMapperValue(newVal));
                ayahNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                        tvMainText.setText("Surah number: " +surahNumberPicker.getValue() +  ", Ayah number: " + ayahNumberPicker.getValue());
                    }
                });
            }
        });

    }

    private void getAyahFromJsonAPI() {
        final Handler handler = new Handler();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                ayahFromJson = JsonProcessor.getVerse(JsonProcessor.getJson(apiLink));
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        tvMainText.setText(ayahFromJson);
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
    }

    private void initializeNumberPickers(){
        surahNumberPicker = findViewById(R.id.numberPickerSurah);
        ayahNumberPicker = findViewById(R.id.numberPickerAyah);
    }
}
