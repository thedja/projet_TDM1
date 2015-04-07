package devmobile.projet_tdm1;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import devmobile.projet_tdm1.model.Chaine;
import devmobile.projet_tdm1.model.JSONController;

import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

public class ChannelPagerAdapter extends FragmentPagerAdapter {
	private static final String TAG = "ChannelPagerAdapter";
	private ArrayList<Chaine> chaines;
	private Resources resources;
    private ArrayList<PageFragment> listPages = new ArrayList<PageFragment>();

	public ChannelPagerAdapter(FragmentManager fm, Resources resources,
                               ArrayList<Chaine> chaines, ArrayList<PageFragment> listPages) {
		super(fm);

		this.resources = resources;
        this.chaines = chaines;
        this.listPages = listPages;
	}

    @Override
    public Fragment getItem(int i) {
        return listPages.get(i);
    }

    public int getChaineIcon(int i){

        return chaines.get(i).getIcon(resources);
    }

	@Override
	public int getCount() {
		return chaines.size();
	}	
	
	@Override
	public CharSequence getPageTitle(int position) {
		return chaines.get(position).getLabel();		
	}

}