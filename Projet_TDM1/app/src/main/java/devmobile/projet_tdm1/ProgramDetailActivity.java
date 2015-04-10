package devmobile.projet_tdm1;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import devmobile.projet_tdm1.model.ProgrammeTele;


public class ProgramDetailActivity extends Activity {

    public static final int DATA_REQUEST = 52;
    ProgrammeTele program;
    public static String DATA_KEY = "data_key";
    public static String DATA_RESULT = "data_result";
    private ProgramDetailView myView;
    private static Intent intent;

    public static Intent getMyIntent() {
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        intent = getIntent();
        program = (ProgrammeTele) intent.getExtras().getParcelable(DATA_KEY);
        if (program == null)
            throw new NullPointerException("program null !");

        myView = new ProgramDetailView(this, program);
        setContentView(myView);

        // Set up the action bar.
        final ActionBar actionBar = getActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void finish() {
        Log.i("ProgramDetailActivity", "intent program "+program.isFavoris());
        intent.putExtra(DATA_RESULT, program);
        super.finish();

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
        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            savedInstanceState.putInt("Position", myView.getMyVideoView().getCurrentPosition());
            myView.getMyVideoView().pause();
        }
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            int position = savedInstanceState.getInt("Position");
            myView.getMyVideoView().seekTo(position);
        }
    }
}
