package au.com.ubd_ders.ubdtext;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FridayMessageActivity extends AppCompatActivity {

    private static String TAG = FridayMessageActivity.class.getSimpleName();

    private TextView tvHeaderFriday;
    private AutoCompleteTextView autoCompleteTextView;
    private EditText etAyah;
    private EditText etSurahBody;
    private EditText etTarixiShahs;
    private Button btnSendFriday;
    private String myMessage;

    private ArrayList<String> smsTarget = new ArrayList<String>();

    private String[] people = {"0466660096", "0466660096"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friday_message);

        tvHeaderFriday = (TextView) findViewById(R.id.header_friday);
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.surah_auto_complete);
        etAyah = (EditText) findViewById(R.id.et_Ayah);
        etSurahBody = (EditText) findViewById(R.id.et_surah_body);
        btnSendFriday = (Button) findViewById(R.id.btn_sent_friday);
        etTarixiShahs = (EditText) findViewById(R.id.et_tarixi_shahs);


        Typeface typeface = Typeface.createFromAsset(getAssets(), "uyku.ttf");
        tvHeaderFriday.setTypeface(typeface);

        tvHeaderFriday.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);

        String[] surahs = getResources().getStringArray(R.array.surah);
        ArrayAdapter<String> adapterSurah = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, surahs);
        autoCompleteTextView.setAdapter(adapterSurah);
    }

    public void onClickBottonSendFriday(View view) {
        final String smsText = "ئەسسەلەمۇئەلەيكۇم، قۇرئان كەرەرىمدىن سۈرە" ;
        final String body = etSurahBody.getText().toString();
        final String surah = autoCompleteTextView.getText().toString();
        final String ayah = etAyah.getText().toString();
        final String person = etTarixiShahs.getText().toString();

        new Thread(new Runnable() {
            @Override
            public void run() {
                sendMessage(smsText, body, surah, ayah, person);
            }
        }).start();


    }

    public void sendMessage(String smsText, String body, String surah, String ayah, String person){
        myMessage = smsText + " " + surah + " " + ayah + "-ئايەت: " + body + person;
        int counter = 1;
        for (int i = 0; i < people.length; i++) {
            try{
                Toast.makeText(this, "SMS is sent to " + people.length + " People!!!", Toast.LENGTH_SHORT).show();
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(people[i], null, myMessage,null, null);
                try {
                    if(counter % 3 == 0){ Thread.sleep(10000);}
                    else { Thread.sleep(2000); }
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
                Toast.makeText(this, "in module block", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this, "Messeage is NOT sent, something went wrong!!!", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
            counter++;
        }
    }

    public void addContact(View view) {
    }

    public void removeContact(View view) {
    }

    public void goToWhatsApp(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, myMessage );
        intent.setType("text/plain");
        intent.setPackage("com.whatsapp");
        startActivity(intent);
    }
}
