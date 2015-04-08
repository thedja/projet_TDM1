package devmobile.projet_tdm1;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import devmobile.projet_tdm1.model.ProgrammeTele;


public class ProgramDetailActivity extends Activity {

    ProgrammeTele program;
    public static String DATA_KEY = "data_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        program = (ProgrammeTele) getIntent().getExtras().getParcelable(DATA_KEY);
        if(program == null)
            throw new NullPointerException("program null !");
        setContentView(new ProgramDetailView(this, program));
    }


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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }else if (id == R.id.action_notify) {
            //TODO: create a notification
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
