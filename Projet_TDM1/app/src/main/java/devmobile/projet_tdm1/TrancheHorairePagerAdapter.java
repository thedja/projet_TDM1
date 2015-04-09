package devmobile.projet_tdm1;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.ArrayList;


public class TrancheHorairePagerAdapter extends FragmentPagerAdapter {
	private static final String TAG = "PRIVATE_TAG_TranchPager";
	private ArrayList<String> tranchesHoraires;
    private ArrayList<StickyPageFragment> listPages = new ArrayList<StickyPageFragment>();

	public TrancheHorairePagerAdapter(FragmentManager fm,
                                      ArrayList<String> tranchesHoraires, ArrayList<StickyPageFragment> listPages) {
		super(fm);

        this.tranchesHoraires = tranchesHoraires;
        this.listPages = listPages;
	}

    @Override
    public Fragment getItem(int i) {
        return listPages.get(i);
    }

    // TODO : get icon for tranche horaire
//    public int getChaineIcon(int i){
//
//        return chaines.get(i).getIcon(resources);
//    }

	@Override
	public int getCount() {
		return tranchesHoraires.size();
	}	
	
	@Override
	public CharSequence getPageTitle(int position) {
		return tranchesHoraires.get(position);
	}

}