package devmobile.projet_tdm1;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by Afifa RIH on 30/03/2015.
 */
public class ItemNavBarAdapter extends RecyclerView.Adapter<ItemNavBarAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private List<ItemNavBarInfo> data = Collections.emptyList();
    private ClickListener clickListener;
    Context context;

    public ItemNavBarAdapter(Context context, List<ItemNavBarInfo> data){

        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    public void setClickListener(ClickListener clickListener){
        this.clickListener = clickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View  view = inflater.inflate(R.layout.layout_item_navbar, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int i) {

        ItemNavBarInfo current = data.get(i);
        holder.bind(current);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView icon;
        TextView text;
        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            icon = (ImageView) itemView.findViewById(R.id.icon);
            text = (TextView) itemView.findViewById(R.id.text);
        }

        public void bind(ItemNavBarInfo current){

            this.icon.setImageResource(current.icon);
            this.text.setText(context.getString(current.text));
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
