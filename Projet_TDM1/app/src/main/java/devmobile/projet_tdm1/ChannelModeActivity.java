package devmobile.projet_tdm1;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

import devmobile.projet_tdm1.model.Chaine;
import devmobile.projet_tdm1.model.JSONController;
import devmobile.projet_tdm1.model.ProgrammeTele;

public class ChannelModeActivity extends CommunActivity{
    private static final String TAG = "PRIVATE_TAG_ChannelMode";

	private ChannelPagerAdapter myPagerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pager_mode);

        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);

        // Set up the action bar.
        final ActionBar actionBar = getActionBar();

        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        drawerToggle = new ActionBarDrawerToggle(this, drawer_layout, R.drawable.toggle, R.string.open, R.string.close);

        drawer_layout.setDrawerListener(drawerToggle);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        HashMap<Chaine, ArrayList<ProgrammeTele>> programmesParChaines = ((MyApplication) getApplication()).getProgrammesParChaines();
        ArrayList<Chaine> chaines = new ArrayList<>(programmesParChaines.keySet());

        ArrayList<SimplePageFragment> listPages = new ArrayList<SimplePageFragment>();

        for (Chaine chaine : chaines) {
            listPages.add(SimplePageFragment.newInstance(programmesParChaines.get(chaine)));
        }

        myPagerAdapter = new ChannelPagerAdapter(getSupportFragmentManager() , getResources(), chaines, listPages);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(myPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < myPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.

            actionBar.addTab(
                    actionBar.newTab()
                            //.setIcon(myPagerAdapter.getChaineIcon(i))
                            .setText(myPagerAdapter.getPageTitle(i))
                            .setTabListener(this));


        }
//        actionBar.addTab(actionBar.newTab().setText("new tab").setTabListener(this));
//        actionBar.addTab(actionBar.newTab().setText("new tab").setTabListener(this));
//        actionBar.addTab(actionBar.newTab().setText("new tab").setTabListener(this));
//        actionBar.addTab(actionBar.newTab().setText("new tab").setTabListener(this));

        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	}

    @Override
    public void OnChaineItemSelected() {
        drawer_layout.closeDrawers();
    }

}
