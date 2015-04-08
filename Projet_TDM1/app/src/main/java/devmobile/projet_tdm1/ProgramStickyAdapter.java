package devmobile.projet_tdm1;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;

import java.util.List;
import devmobile.projet_tdm1.model.ProgrammeTele;

/**
 * Created by Afifa RIH on 30/03/2015.
 */
public class ProgramStickyAdapter extends RecyclerArrayAdapter<ProgrammeTele, ProgramStickyAdapter.MyViewHolder>
        implements StickyRecyclerHeadersAdapter<RecyclerView.ViewHolder> {

    private LayoutInflater inflater;
    private ClickListener clickListener;
    private int mSelectedPosition = -1;
    Context context;

    public ProgramStickyAdapter(Context context, List<ProgrammeTele> data){

        this.context = context;
        inflater = LayoutInflater.from(context);
        addAll(data);
    }

    public void setClickListener(ClickListener clickListener){
        this.clickListener = clickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View  view = inflater.inflate(R.layout.program_item_layout, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        ProgrammeTele current = getItem(position);
        holder.bind(current);
        holder.updateSelection();
    }

    @Override
    public long getHeaderId(int position) {
        if (getItemCount() == 0) {
            return -1;

        } else {
            return getItem(position).getChaine().getIcon(context.getResources());
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup viewGroup) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.view_header, viewGroup, false);
        return new RecyclerView.ViewHolder(view) {
        };
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
        TextView textView = (TextView) holder.itemView;
        textView.setText(getItem(position).getChaine().getLabel());
    }

    public void selectItem(int position){
        mSelectedPosition = position;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView photo;
        TextView thematique;
        TextView horaire;
        TextView titre;
        TextView descriptif;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            photo = (ImageView) itemView.findViewById(R.id.program_snapshot);
            thematique = (TextView) itemView.findViewById(R.id.txtView_thematique);
            horaire = (TextView) itemView.findViewById(R.id.txtView_horaire);
            titre = (TextView) itemView.findViewById(R.id.txtView_title);
            descriptif = (TextView) itemView.findViewById(R.id.txtView_description);
        }

        public void bind(ProgrammeTele current){
            photo.setImageResource(current.getChaine().getIcon(context.getResources()));
            thematique.setText(current.getThematique());
            horaire.setText(current.getHoraire());
            titre.setText(current.getTitre());
            if(descriptif != null)
                descriptif.setText(current.getDescriptif());
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
            Log.i("ProgramStickyAdapter ", "setSelected "+ selected+" itemView "+itemView);
            //TODO : change color of text, ..
        }
    }

    public interface ClickListener{
        public void itemClicked(View view, int position);
    }
}
