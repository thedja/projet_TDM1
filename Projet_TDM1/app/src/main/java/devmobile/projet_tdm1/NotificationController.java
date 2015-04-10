package devmobile.projet_tdm1;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.Log;

import java.util.Date;

import devmobile.projet_tdm1.model.ProgrammeTele;

/**
 * Created by Afifa RIH on 08/04/2015.
 */
public class NotificationController {

    public static void askForNotification(final Context context, final ProgrammeTele programme){

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        // set title
        alertDialogBuilder.setTitle(programme.getTitre());

        // set dialog message
        alertDialogBuilder
                .setMessage(context.getResources().getString(R.string.ask_for_notif))
                .setCancelable(false)
                .setPositiveButton(context.getResources().getString(R.string.dialogbox_yes), new DialogInterface.OnClickListener() {
                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close
                        // current activity
                        Intent intent = new Intent(context, ChannelModeActivity.class);

                        PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent, 0);
                        Date d = new Date();
                        d.setHours(programme.getHeureDebut());

                        // build notification
                        // the addAction re-use the same intent to keep the example short
                        NotificationManager NM = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
                        Notification notify = new Notification(programme.getIcon(context.getResources()),
                                context.getResources().getString(R.string.app_name),
                                d.getTime());

                        notify.setLatestEventInfo(context.getApplicationContext(),
                                programme.getTitre(),
                                programme.getThematique(),
                                pIntent);
                        NM.notify(0, notify);

                        dialog.cancel();
                    }
                })
                .setNegativeButton(context.getResources().getString(R.string.dialogbox_no), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }
}
