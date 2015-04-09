package devmobile.projet_tdm1;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;

public abstract class CommunActivity extends FragmentActivity implements ActionBar.TabListener,
        NavBarFragment.OnNavBarItemSelectedListener {

    protected ViewPager mViewPager;
    protected DrawerLayout drawer_layout;
    protected ActionBarDrawerToggle drawerToggle;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
        if(drawerToggle.onOptionsItemSelected(item)){
            drawer_layout.openDrawer(Gravity.START);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabReselected(ActionBar.Tab arg0, FragmentTransaction arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction arg1) {
        mViewPager.setCurrentItem(tab.getPosition());

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
        drawer_layout.closeDrawers();

        Intent detailIntent = new Intent(this, ThematiqueModeActivity.class);
        startActivity(detailIntent);
    }

    @Override
    public void OnTrancheHoraireItemSelected() {
        drawer_layout.closeDrawers();

        Intent detailIntent = new Intent(this, TrancheHoraireModeActivity.class);
        startActivity(detailIntent);
    }

    @Override
    public void OnFavorisItemSelected() {

        drawer_layout.closeDrawers();

        Intent detailIntent = new Intent(this, FavorisModeActivity.class);
        startActivity(detailIntent);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }
}
