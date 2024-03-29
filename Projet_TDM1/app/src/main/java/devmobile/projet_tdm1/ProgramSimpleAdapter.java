package devmobile.projet_tdm1;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Collections;
import java.util.List;

import devmobile.projet_tdm1.model.ProgrammeTele;

/**
 * Created by Afifa RIH on 30/03/2015.
 */
public class ProgramSimpleAdapter extends RecyclerView.Adapter<ProgramSimpleAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private List<ProgrammeTele> data = Collections.emptyList();
    private ClickListener clickListener;
    private FavorisListener favorisListener;
    private int mSelectedPosition = -1;
    Context context;

    public ProgramSimpleAdapter(Context context, List<ProgrammeTele> data){

        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    public void setClickListener(ClickListener clickListener){
        this.clickListener = clickListener;
    }

    public void setFavorisListener(FavorisListener favorisListener){
        this.favorisListener = favorisListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {

        View  view = inflater.inflate(R.layout.program_item_layout, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int i) {

        ProgrammeTele current = data.get(i);
        holder.bind(current);
        holder.updateSelection();
    }



    @Override
    public int getItemCount() {
        return data.size();
    }

    public int getmSelectedPosition(){

        return mSelectedPosition;
    }

    public void selectItem(int position){
        mSelectedPosition = position;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        LinearLayout photo;
        TextView thematique;
        TextView horaire;
        TextView titre;
        TextView descriptif;
        ToggleButton favoris;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            photo = (LinearLayout) itemView.findViewById(R.id.program_snapshot);
            thematique = (TextView) itemView.findViewById(R.id.txtView_thematique);
            horaire = (TextView) itemView.findViewById(R.id.txtView_horaire);
            titre = (TextView) itemView.findViewById(R.id.txtView_title);
            descriptif = (TextView) itemView.findViewById(R.id.txtView_description);
            favoris = (ToggleButton) itemView.findViewById(R.id.favoris);
        }

        public void bind(final ProgrammeTele current){

            photo.setBackgroundResource(current.getIcon(context.getResources()));
            thematique.setText(current.getThematique());
            horaire.setText(current.getHoraire());
            titre.setText(current.getTitre());
            if(descriptif != null)
                descriptif.setText(current.getDescriptif());

            favoris.setChecked(current.isFavoris());
            favoris.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    current.setFavoris(favoris.isChecked());
                    notifyDataSetChanged();
                    if (favorisListener != null)
                        favorisListener.favorisChanged(favoris.isChecked());
                    if (favoris.isChecked())
                        NotificationController.askForNotification(context, current);
                }
            });
        }

        @Override
        public void onClick(View v) {
            if(clickListener != null){
                clickListener.itemClicked(v, getPosition());
            }
            mSelectedPosition = getPosition();
            notifyDataSetChanged();
        }

        public void updateSelection(){
            if(mSelectedPosition == getPosition())
                setSelected(true);
            else
                setSelected(false);
        }

        private void setSelected(boolean selected){
            itemView.setSelected(selected);
            ((CardView)itemView).setCardBackgroundColor(selected ? Color.rgb(221, 221, 221):
                                                            Color.WHITE);
        }
    }

    public interface ClickListener{
        public void itemClicked(View view, int position);
    }

    public interface FavorisListener{
        public void favorisChanged(boolean isChecked);
    }
}
