package devmobile.projet_tdm1.model;

import android.content.res.Resources;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import devmobile.projet_tdm1.R;

/**
 * Created by Afifa RIH on 06/04/2015.
 */
public class JSONController {

    public static ArrayList<Chaine> loadChaines(Resources resources, String TAG){

        ArrayList<Chaine> chaines = new ArrayList<Chaine>();
        try
        {
            InputStream is = resources.openRawResource(R.raw.chaines);
            byte [] buffer = new byte[is.available()];
            while (is.read(buffer) != -1);
            String jsontext = new String(buffer);
            JSONArray entries = new JSONArray(jsontext);

            Log.i(TAG, "Number of channels read : " + entries.length());
            for (int i=0;i<entries.length();i++)
            {
                JSONObject post = entries.getJSONObject(i);
                chaines.add(new Chaine(post.getString("label"), post.getString("iconId"), null));
            }
        }
        catch (IOException e){
            Log.e(TAG, "IOException -> Can't load JSON file !\n"+e.getMessage());
        }catch (JSONException e){
            Log.e(TAG, "JSONException -> Can't load JSON file !\n"+e.getMessage());
        }

        return chaines;
    }
}
