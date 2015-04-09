package devmobile.projet_tdm1;

import android.app.ActionBar;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;

import devmobile.projet_tdm1.model.Chaine;
import devmobile.projet_tdm1.model.JSONController;
import devmobile.projet_tdm1.model.ProgrammeTele;


public class FavorisModeActivity extends CommunActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoris_mode);

        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);

        // Set up the action bar.
        final ActionBar actionBar = getActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        drawerToggle = new ActionBarDrawerToggle(this, drawer_layout, R.drawable.toggle, R.string.open, R.string.close);

        drawer_layout.setDrawerListener(drawerToggle);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        ArrayList<ProgrammeTele> programmesFavoris = ((MyApplication) getApplication()).getProgrammesFavoris();

        StickyPageFragment fragment = StickyPageFragment.newInstance(programmesFavoris);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.body, fragment);
        ft.commit();
    }

}
