package devmobile.projet_tdm1.model;

import android.content.res.Resources;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import devmobile.projet_tdm1.R;

/**
 * Created by Afifa RIH on 06/04/2015.
 */
public class JSONController {
    public static final String TAG = "MYTAG_JSONController";
    public static HashMap<Chaine, ArrayList<ProgrammeTele>> loadData(Resources resources){
        HashMap<Chaine, ArrayList<ProgrammeTele>> programmesParChaine = new HashMap<Chaine, ArrayList<ProgrammeTele>>();
        Chaine chaine;
        ArrayList<ProgrammeTele> programmes;

        try
        {
            InputStream is = resources.openRawResource(R.raw.chaines);
            byte [] buffer = new byte[is.available()];
            while (is.read(buffer) != -1);
            is.close();

            String jsontext = new String(buffer);
            JSONArray entries = new JSONArray(jsontext);

            for (int i=0, c=entries.length(); i<c ;i++)
            {
                JSONObject post = entries.getJSONObject(i);
                JSONArray programmesJSON = post.getJSONArray("programmes");

                chaine = new Chaine(post.getString("label"), post.getString("iconId"), post.getLong("chaineId"));
                programmes = readProgrammesFromChaine(programmesJSON, chaine);

                programmesParChaine.put(chaine, programmes);
            }
        }
        catch (IOException e){
            Log.e(TAG, "IOException -> Can't load JSON file !\n"+e.getMessage());
        }catch (JSONException e){
            Log.e(TAG, "JSONException -> Can't parse JSON file !\n"+e.getMessage());
        }catch(Exception e){
            Log.e(TAG, "Unkown exception"+e.getMessage());
        }

        return programmesParChaine;
    }

    private static ArrayList<ProgrammeTele> readProgrammesFromChaine(JSONArray programmesJSON, Chaine chaine){
        ArrayList<ProgrammeTele> programmes = new ArrayList<>();
        ProgrammeTele program;

        try {
            for (int i=0, c=programmesJSON.length(); i<c; i++)
            {
                JSONObject post = programmesJSON.getJSONObject(i);

                program = new ProgrammeTele(chaine, post.getString("thematique"), post.getInt("heureDebut"), post.getInt("heureFin"),
                        post.getString("descriptif"), post.getString("videoId"), post.getString("iconId"), post.getString("titre"), post.getBoolean("isFavoris"));
                programmes.add(program);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return programmes;
    }
}
