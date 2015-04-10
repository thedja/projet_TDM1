package devmobile.projet_tdm1;

import android.app.ProgressDialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import android.widget.ToggleButton;
import android.widget.VideoView;

import devmobile.projet_tdm1.model.ProgrammeTele;

import static android.view.Gravity.CENTER_VERTICAL;


/**
 * TODO: document your custom view class.
 */
public class ProgramDetailView extends LinearLayout {

    private static final String TAG = "MYTAG_ProgramDetailView";

    private VideoView videoView;
    private MediaController mediaController;
    private ProgressDialog progressDialog;
    private ImageView imgView_snapshot;
    private TextView txtView_title, txtView_thematique, txtView_description, txtView_horaire;
    private ToggleButton favoris;
    private FavorisListener favorisListener;

    public ProgramDetailView(Context context, ProgrammeTele programme) {
        super(context);
        bind(context);
        init(context, null, 0, programme);
    }

    public ProgramDetailView(Context context, AttributeSet attrs, ProgrammeTele programme) {
        super(context, attrs);
        bind(context);
        init(context, attrs, 0, programme);
    }

    public ProgramDetailView(Context context, AttributeSet attrs, int defStyle, ProgrammeTele programme) {
        super(context, attrs, defStyle);
        bind(context);
        init(context, attrs, defStyle, programme);
    }

    private void init(final Context context, AttributeSet attrs, int defStyle, final ProgrammeTele program) {

        txtView_title.setText(program.getTitre());
        txtView_thematique.setText(program.getThematique());
        txtView_description.setText(program.getDescriptif());
        imgView_snapshot.setImageResource(program.getIcon(context.getResources()));
        txtView_horaire.setText(program.getHoraire());

        favoris.setChecked(program.isFavoris());
        favoris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                program.setFavoris(favoris.isChecked());
                if (favorisListener != null)
                    favorisListener.favorisDetailChanged(favoris.isChecked());
                if (favoris.isChecked())
                    NotificationController.askForNotification(context, program);
            }
        });

        if (!program.getVideoId().isEmpty())
            makeVideo(context, program);
    }

    private void makeVideo(Context context, ProgrammeTele program) {
        // set the media controller buttons
        if (mediaController == null) {
            mediaController = new MediaController(context);
        }

        // create a progress bar while video is loading
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle(program.getTitre());
        progressDialog.setMessage("Chargement ....");
        progressDialog.setCancelable(true);
        progressDialog.show();

        try {
            videoView.setMediaController(mediaController);
            // TODO get the media Uri from program instance
            videoView.setVideoURI(Uri.parse("android.resource://" + context.getPackageName() + "/" + program.getVideo(context.getResources())));
        } catch (Exception e) {
            e.printStackTrace();
        }

        videoView.requestFocus();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                // close the progress bar
                Log.i(TAG, "video ready !");
                // wrap the video content
                videoView.setLayoutParams(
                        new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT,
                            Gravity.CENTER_HORIZONTAL| CENTER_VERTICAL));

                progressDialog.dismiss();
            }
        });

    }

    private void bind(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.program_layout, this, true);
        this.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 3f));

        imgView_snapshot = (ImageView) findViewById(R.id.imgView_snapshot);
        txtView_title = (TextView) findViewById(R.id.txtView_title);
        txtView_thematique = (TextView) findViewById(R.id.txtView_thematique);
        txtView_description = (TextView) findViewById(R.id.txtView_description);
        txtView_horaire = (TextView) findViewById(R.id.txtView_horaire);
        favoris = (ToggleButton) findViewById(R.id.favoris_detail);
        videoView = (VideoView) findViewById(R.id.videoView);

    }

    public void setFavoris(boolean isFavoris) {

        favoris.setChecked(isFavoris);
    }

    public void setFavorisListener(FavorisListener favorisListener) {
        this.favorisListener = favorisListener;
    }

    public VideoView getMyVideoView() {
        return videoView;
    }

    public interface FavorisListener {
        public void favorisDetailChanged(boolean isChecked);
    }
}
