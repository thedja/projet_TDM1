package devmobile.projet_tdm1;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class ThematiquePagerAdapter extends FragmentPagerAdapter {
	private static final String TAG = "PRIVATE_TAG_ThemePager";
	private ArrayList<String> thematiques;
    private ArrayList<StickyPageFragment> listPages = new ArrayList<StickyPageFragment>();

	public ThematiquePagerAdapter(FragmentManager fm,
                                  ArrayList<String> thematiques, ArrayList<StickyPageFragment> listPages) {
		super(fm);

        this.thematiques = thematiques;
        this.listPages = listPages;
	}

    @Override
    public Fragment getItem(int i) {
        return listPages.get(i);
    }

    // TODO : get thematique icon
//    public int getThemeIcon(int i){
//
//        return chaines.get(i).getIcon(resources);
//    }

	@Override
	public int getCount() {
		return thematiques.size();
	}	
	
	@Override
	public CharSequence getPageTitle(int position) {
		return thematiques.get(position);
	}

}