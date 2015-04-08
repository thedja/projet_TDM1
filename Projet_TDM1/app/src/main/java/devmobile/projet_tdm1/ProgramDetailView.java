package devmobile.projet_tdm1;

import android.app.ProgressDialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import devmobile.projet_tdm1.model.ProgrammeTele;


/**
 * TODO: document your custom view class.
 */
public class ProgramDetailView extends LinearLayout {

    private VideoView videoView;
    private MediaController mediaController;
    private ProgressDialog progressDialog;
    private int position;
    private ImageView imgView_snapshot;
    private TextView txtView_title, txtView_thematique, txtView_description, txtView_horaire;

    public ProgramDetailView(Context context, ProgrammeTele programme) {
        super(context);
        bind(context);
        init(context,null, 0, programme);
    }

    public ProgramDetailView(Context context, AttributeSet attrs, ProgrammeTele programme) {
        super(context, attrs);
        bind(context);
        init(context,attrs, 0, programme);
    }

    public ProgramDetailView(Context context, AttributeSet attrs, int defStyle, ProgrammeTele programme) {
        super(context, attrs, defStyle);
        bind(context);
        init(context,attrs, defStyle, programme);
    }

    private void init(Context context,AttributeSet attrs, int defStyle, ProgrammeTele program) {

        txtView_title.setText(program.getTitre());
        txtView_thematique.setText(program.getThematique());
        txtView_description.setText(program.getDescriptif());
        imgView_snapshot.setImageResource(program.getIcon(context.getResources()));
        txtView_horaire.setText(program.getHoraire());

        // set the media controller buttons
        if(mediaController == null){
            mediaController = new MediaController(context);
        }

        // create a progress bar while video is loading
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle(program.getTitre());
        progressDialog.setMessage("Chargement ....");
        progressDialog.setCancelable(false);
        progressDialog.show();

        try{
            videoView.setMediaController(mediaController);
            // TODO get the media Uri from program instance
            videoView.setVideoURI(Uri.parse("android.resource://" + context.getPackageName() + "/" + program.getVideo(context.getResources())));
        }catch (Exception e){
            e.printStackTrace();
        }

        videoView.requestFocus();
        videoView.start();

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                // close the progress bar
                progressDialog.dismiss();
                // play the video from previous attempt
                videoView.seekTo(position);

                if (position == 0) {
                    videoView.start();
                } else {
                    videoView.pause();
                }
            }
        });

    }

    private void bind(Context context){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.program_layout, this, true);
        this.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 3f));

        imgView_snapshot = (ImageView) findViewById(R.id.imgView_snapshot);
        txtView_title = (TextView) findViewById(R.id.txtView_title);
        txtView_thematique = (TextView) findViewById(R.id.txtView_thematique);
        txtView_description = (TextView) findViewById(R.id.txtView_description);
        txtView_horaire = (TextView) findViewById(R.id.txtView_horaire);

        videoView = (VideoView) findViewById(R.id.videoView);

    }
}
