package devmobile.projet_tdm1;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import java.util.ArrayList;

import devmobile.projet_tdm1.model.ProgrammeTele;


public class MainActivity extends Activity implements NavBarFragment.OnNavBarItemSelectedListener{

    DrawerLayout drawer_layout;
    ArrayList<ProgrammeTele> data = new ArrayList<ProgrammeTele>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
