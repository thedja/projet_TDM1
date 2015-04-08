package devmobile.projet_tdm1;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
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

public abstract class CommunActivity extends FragmentActivity implements ActionBar.TabListener,
        NavBarFragment.OnNavBarItemSelectedListener {

    protected ViewPager mViewPager;
    protected DrawerLayout drawer_layout;

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
        }else if (id == R.id.action_notify) {

            String title = getResources().getString(R.string.notify_title);
            String subject = getResources().getString(R.string.notify_subject);
            String body = getResources().getString(R.string.notify_budy);

            NotificationManager NM =(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
            Notification notify=new Notification(android.R.drawable.
                stat_notify_more,title,System.currentTimeMillis());

            PendingIntent pending=PendingIntent.getActivity(
                        getApplicationContext(),0, new Intent(),0);
            notify.setLatestEventInfo(getApplicationContext(),subject,body,pending);
            NM.notify(0, notify);

            return true;
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
        //TODO : intent activity
    }

    @Override
    public void OnTrancheHoraireItemSelected() {

        drawer_layout.closeDrawers();
        //TODO : intent activity
    }

}
