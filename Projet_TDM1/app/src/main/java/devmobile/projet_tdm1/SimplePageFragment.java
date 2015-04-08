package devmobile.projet_tdm1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import devmobile.projet_tdm1.model.ProgrammeTele;


public class SimplePageFragment extends android.support.v4.app.Fragment implements
        ProgramSimpleAdapter.ClickListener{

    ArrayList<ProgrammeTele> data;
    private static String DATA_KEY = "data_key";
    ProgramSimpleAdapter adapter;

    public SimplePageFragment(ArrayList<ProgrammeTele> data) {

        this.data = data;
    }

    public static SimplePageFragment newInstance(ArrayList<ProgrammeTele> data){

        SimplePageFragment fragment = new SimplePageFragment(data);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(DATA_KEY, data);
        fragment.setArguments(bundle);
        return fragment;
    }

    public SimplePageFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page,
                container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list_program);
        adapter = new ProgramSimpleAdapter(getActivity(), data);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        if (recyclerView.getLayoutManager() == null)
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            data = getArguments().getParcelableArrayList(DATA_KEY);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void itemClicked(View view, int position) {

        showProgramDetail(data.get(position));

    }

    public void showProgramDetail(ProgrammeTele programme){
        if (getActivity().getResources().getInteger(R.integer.nbr_panel) == 2) {

            ViewGroup parent = (ViewGroup) getView();
            View view = parent.findViewById(R.id.detail_program);
            int index = parent.indexOfChild(view);
            parent.removeView(view);

            View detail = new ProgramDetailView(getActivity(), programme);
            detail.setId(R.id.detail_program);
            parent.addView(detail, index);

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(getActivity(), ProgramDetailActivity.class);
            detailIntent.putExtra(ProgramDetailActivity.DATA_KEY, programme);
            startActivity(detailIntent);
        }
    }

}
