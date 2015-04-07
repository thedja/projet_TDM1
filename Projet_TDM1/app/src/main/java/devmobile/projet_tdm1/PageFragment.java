package devmobile.projet_tdm1;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import devmobile.projet_tdm1.model.ProgrammeTele;


public class PageFragment extends android.support.v4.app.Fragment implements ListProgramFragment.OnProgrammeItemSelectedListener{

    Fragment current_detail = null;
    ArrayList<ProgrammeTele> data;
    private static final String DATA_KEY = "data_key";

    public PageFragment(ArrayList<ProgrammeTele> data) {
        this.data = data;
    }

    public PageFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page,
                container, false);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            data = getArguments().getParcelableArrayList(DATA_KEY);
        }
    }

    public static PageFragment newInstance(ArrayList<ProgrammeTele> data){
        PageFragment fragment = new PageFragment(data);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(DATA_KEY, data);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showListFragment(ListProgramFragment.newInstance(data, this));
    }

    @Override
    public void OnProgrammeSelected(ProgrammeTele programme){

        showProgramDetail(programme);
    }

    public void showProgramDetail(ProgrammeTele programme){
        if (getActivity().getResources().getInteger(R.integer.nbr_panel) == 2) {
            ProgrammeDetailFragment fragment = new ProgrammeDetailFragment(programme);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if(current_detail == null) {
                ft.add(R.id.body_page_fragment, fragment)
                        .commit();
            } else {

                ft.replace(current_detail.getId(), fragment)
                        .commit();
            }
            current_detail = fragment;
        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(getActivity(), ProgramDetailActivity.class);
            startActivity(detailIntent);
        }
    }

    public void showListFragment(ListProgramFragment fragment){

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.list_container, fragment, "detail");
        ft.commit();

        if(current_detail != null) {
            ft = getFragmentManager().beginTransaction();
            ft.remove(current_detail);
            ft.commit();
            current_detail = null;
        }
    }

}
