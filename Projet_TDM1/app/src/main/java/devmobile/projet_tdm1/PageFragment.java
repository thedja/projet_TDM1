package devmobile.projet_tdm1;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import devmobile.projet_tdm1.model.ProgrammeTele;


public class PageFragment extends android.support.v4.app.Fragment implements
        ItemProgrammeAdapter.ClickListener{

    ArrayList<ProgrammeTele> data;
    private static String DATA_KEY = "data_key";

    public PageFragment(ArrayList<ProgrammeTele> data) {

        this.data = data;
    }

    public static PageFragment newInstance(ArrayList<ProgrammeTele> data){

        PageFragment fragment = new PageFragment(data);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(DATA_KEY, data);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public PageFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page,
                container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list_program);
        ItemProgrammeAdapter adapter = new ItemProgrammeAdapter(getActivity(), data);
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

            TextView t = new TextView(getActivity());
            t.setText("new view"+Math.random());
            t.setId(R.id.detail_program);
            t.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 3f));

            parent.addView(t, index);

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(getActivity(), ProgramDetailActivity.class);
            startActivity(detailIntent);
        }
    }

}
