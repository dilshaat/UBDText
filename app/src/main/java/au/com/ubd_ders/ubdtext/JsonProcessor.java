package au.com.ubd_ders.ubdtext;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by boa on 10/16/17.
 */

public class JsonProcessor {

    public static String getJson(String api) {

        try {
            URL url = new URL(api);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;

            while((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getVerse(String json) {

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject dataObject = new JSONObject(jsonObject.getString("data"));
            return dataObject.getString("text");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getNumberOfVerses(int surahNumber){
        String api = "http://api.alquran.cloud/surah/" + surahNumber + "/en.asad";
        String json = getJson(api);
        try {
            JSONObject jsonObject = new JSONObject(api);
            JSONObject dataObject = new JSONObject(jsonObject.getString("data"));
            return dataObject.getInt("numberOfAyahs");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 1;
    }

}
