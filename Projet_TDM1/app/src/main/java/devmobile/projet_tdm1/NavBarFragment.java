package devmobile.projet_tdm1;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import java.util.ArrayList;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 */
public class NavBarFragment extends Fragment implements NavBarAdapter.ClickListener{


    private static final String DATA_KEY = "data_key";
    private OnNavBarItemSelectedListener listener;
    private RecyclerView recyclerView;
    private ArrayList<ItemNavBarInfo> data = new ArrayList<ItemNavBarInfo>() ;
    private NavBarAdapter adapter;

    private ActionBarDrawerToggle drawerToggle;
    private DrawerLayout drawerLayout;
    private boolean userLearnedDrawer;
    private boolean fromSavedInstanceState;

    public void setUp(DrawerLayout drawerLayout, Toolbar toolbar){

        this.drawerLayout= drawerLayout;
        //drawerToggle = new ActionBarDrawerToggle(getActivity().dr)
    }

    public static NavBarFragment newInstance(){

        ArrayList<ItemNavBarInfo> data = new ArrayList<ItemNavBarInfo>();
        data.add(new ItemNavBarInfo(R.drawable.chaine, R.string.item_chaine));
        data.add(new ItemNavBarInfo(R.drawable.thematique, R.string.item_thematique));
        data.add(new ItemNavBarInfo(R.drawable.tranche_horaire, R.string.item_tranche_horaire));
        data.add(new ItemNavBarInfo(R.drawable.favoris, R.string.item_favoris));

        NavBarFragment fragment = new NavBarFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(DATA_KEY, data);
        fragment.setArguments(bundle);

        return fragment;
    }

    public NavBarFragment(){
        super();

        ArrayList<ItemNavBarInfo> data = new ArrayList<ItemNavBarInfo>();
        data.add(new ItemNavBarInfo(R.drawable.chaine, R.string.item_chaine));
        data.add(new ItemNavBarInfo(R.drawable.thematique, R.string.item_thematique));
        data.add(new ItemNavBarInfo(R.drawable.tranche_horaire, R.string.item_tranche_horaire));
        data.add(new ItemNavBarInfo(R.drawable.favoris, R.string.item_favoris));

        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(DATA_KEY, data);
        this.setArguments(bundle);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            data = getArguments().getParcelableArrayList(DATA_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nav_bar,
                container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.list_item);
        adapter = new NavBarAdapter(getActivity(), data);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        recyclerView.addItemDecoration(new DividerDecoration(getActivity()));
        if(recyclerView.getLayoutManager() == null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        return view;
    }

    @Override
    public void itemClicked(View view, int position) {

        if(listener != null) {
            switch (position) {
                case 0: listener.OnChaineItemSelected(); break;
                case 1: listener.OnThematiqueItemSelected(); break;
                case 2: listener.OnTrancheHoraireItemSelected(); break;
                case 3: listener.OnFavorisItemSelected(); break;
            }
        }
    }

    public interface OnNavBarItemSelectedListener {
        public void OnChaineItemSelected();
        public void OnThematiqueItemSelected();
        public void OnTrancheHoraireItemSelected();
        public void OnFavorisItemSelected();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnNavBarItemSelectedListener) {
            listener = (OnNavBarItemSelectedListener) activity;
        } else {
            throw new ClassCastException(activity.toString()
                    + " must implemenet MyListFragment.OnItemSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}