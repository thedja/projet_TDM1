package devmobile.projet_tdm1;

import java.util.ArrayList;

import devmobile.projet_tdm1.model.Chaine;

import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ChannelPagerAdapter extends FragmentPagerAdapter {
	private static final String TAG = "PRIVATE_TAG_ChannelPagerAdapter";
    ArrayList<SimplePageFragment> listPages;
    private ArrayList<Chaine> chaines;
    private Resources resources;

	public ChannelPagerAdapter(FragmentManager fm, Resources resources,
                               ArrayList<Chaine> chaines, ArrayList<SimplePageFragment> listPages) {
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