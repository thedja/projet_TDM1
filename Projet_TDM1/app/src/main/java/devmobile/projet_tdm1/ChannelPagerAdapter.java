package devmobile.projet_tdm1;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import devmobile.projet_tdm1.ChannelModeActivity.PlaceholderFragment;
import devmobile.projet_tdm1.model.Chaine;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

public class ChannelPagerAdapter extends FragmentPagerAdapter {
	private static final String TAG = "ChannelPagerAdapter";
	private ArrayList<Chaine> chaines;
	private Resources resources; 

	public ChannelPagerAdapter(FragmentManager fm, Resources resources) {
		super(fm);

		this.resources = resources;
		chaines = new ArrayList<Chaine>();
		loadChaines();		
	}

	@Override
	public Fragment getItem(int position) {
		// TODO : return the desired fragment
        return PlaceholderFragment.newInstance(position + 1);
	}

	@Override
	public int getCount() {
		return chaines.size();
	}	
	
	@Override
	public CharSequence getPageTitle(int position) {
		return chaines.get(position).getLabel();		
	}

	private void loadChaines(){
		try
		{
			InputStream is = resources.openRawResource(R.raw.chaines);
			byte [] buffer = new byte[is.available()];
			while (is.read(buffer) != -1);
			String jsontext = new String(buffer);
			JSONArray entries = new JSONArray(jsontext);

			Log.i(TAG, "Number of channels read : "+entries.length());
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
		}catch (Exception e){
			Log.e(TAG, "Exception -> Can't load JSON file !\n"+e.getMessage());
		}
	}

	public int getIcon(int position) {
		//TODO : retun iconId
		return resources.getIdentifier(chaines.get(position).getIconId(), "drawable", "devmobile.projet_tdm1");
	}
}