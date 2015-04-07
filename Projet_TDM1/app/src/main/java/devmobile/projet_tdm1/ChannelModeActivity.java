package devmobile.projet_tdm1;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import devmobile.projet_tdm1.model.Chaine;
import devmobile.projet_tdm1.model.JSONController;

public class ChannelModeActivity extends FragmentActivity implements ActionBar.TabListener,
        NavBarFragment.OnNavBarItemSelectedListener {

	private ChannelPagerAdapter myPagerAdapter;
	private ViewPager mViewPager;
    DrawerLayout drawer_layout;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_channel_mode);

        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);

		// Set up the action bar.
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        ArrayList<Chaine> chaines = JSONController.loadChaines(getResources(), null);
        ArrayList<PageFragment> listPages = new ArrayList<PageFragment>();
        for (Chaine chaine : chaines) {
            listPages.add(PageFragment.newInstance(chaine.getProgrammes()));
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
                    		.setIcon(myPagerAdapter.getChaineIcon(i))
                            .setText(myPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
         
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.channel_mode, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onTabReselected(ActionBar.Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabSelected(ActionBar.Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabUnselected(ActionBar.Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub

	}

    @Override
    public void OnChaineItemSelected() {
        drawer_layout.closeDrawers();

        Intent detailIntent = new Intent(this, ChannelModeActivity.class);
        startActivity(detailIntent);
    }

    @Override
    public void OnThematiqueItemSelected() {
    }

    @Override
    public void OnTrancheHoraireItemSelected() {

    }

}
