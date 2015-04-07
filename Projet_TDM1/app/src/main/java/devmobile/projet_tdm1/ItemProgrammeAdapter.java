package devmobile.projet_tdm1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import devmobile.projet_tdm1.model.ProgrammeTele;

/**
 * Created by Afifa RIH on 30/03/2015.
 */
public class ItemProgrammeAdapter extends RecyclerView.Adapter<ItemProgrammeAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private List<ProgrammeTele> data = Collections.emptyList();
    private ClickListener clickListener;
    Context context;

    public ItemProgrammeAdapter(Context context, List<ProgrammeTele> data){

        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
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
    public void onBindViewHolder(MyViewHolder holder, int i) {

        ProgrammeTele current = data.get(i);
        holder.bind(current);
    }

    @Override
    public int getItemCount() {
        return data.size();
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
            horaire.setText(current.getHeureDebut()+" - "+ current.getHeureFin());
            titre.setText(current.getTitre());
            descriptif.setText(current.getDescriptif());
        }

        @Override
        public void onClick(View v) {
            if(clickListener != null){
                clickListener.itemClicked(v, getPosition());
            }
        }
    }

    public interface ClickListener{
        public void itemClicked(View view, int position);
    }
}
