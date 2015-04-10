package devmobile.projet_tdm1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import devmobile.projet_tdm1.model.ProgrammeTele;


public class SimplePageFragment extends android.support.v4.app.Fragment implements
        ProgramSimpleAdapter.ClickListener,
        ProgramSimpleAdapter.FavorisListener,
        ProgramDetailView.FavorisListener {

    private static final String TAG = "MYTAG_SimplePageFrag";

    ArrayList<ProgrammeTele> data;
    public static String DATA_KEY = "data_key";
    ProgramSimpleAdapter adapter;
    ProgramDetailView detail;
    private int detail_index = -1;
    Intent detailIntent;

    public SimplePageFragment(ArrayList<ProgrammeTele> data) {

        this.data = data;
    }

    public static SimplePageFragment newInstance(ArrayList<ProgrammeTele> data) {

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
        adapter.setFavorisListener(this);
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
        detail_index = position;

    }

    public void showProgramDetail(ProgrammeTele programme) {
        if (getActivity().getResources().getInteger(R.integer.nbr_panel) == 2) {

            ViewGroup parent = (ViewGroup) getView();
            View view = parent.findViewById(R.id.detail_program);
            int index = parent.indexOfChild(view);
            parent.removeView(view);

            detail = new ProgramDetailView(getActivity(), programme);
            detail.setId(R.id.detail_program);
            detail.setFavorisListener(this);
            parent.addView(detail, index);

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            detailIntent = new Intent(getActivity(), ProgramDetailActivity.class);
            detailIntent.putExtra(ProgramDetailActivity.DATA_KEY, programme);
            getActivity().startActivityForResult(detailIntent, ProgramDetailActivity.DATA_REQUEST);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(detailIntent != null) {
            ProgrammeTele programmeTele = (ProgrammeTele) ProgramDetailActivity.getMyIntent().getExtras().get(ProgramDetailActivity.DATA_RESULT);
            data.get(detail_index).copy(programmeTele);
            adapter.selectItem(-1);
            detailIntent = null;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        super.onActivityResult(requestCode, resultCode, resultData);
        if(requestCode == ProgramDetailActivity.DATA_REQUEST){
            if(resultCode == ProgramDetailActivity.RESULT_OK) {
                Bundle bundle = resultData.getExtras();
                ProgrammeTele programmeTele = bundle.getParcelable(ProgramDetailActivity.DATA_KEY);
                data.get(detail_index).copy(programmeTele);
                adapter.notifyItemChanged(detail_index);
            }
        }
    }

    @Override
    public void favorisChanged(boolean isFavoris) {

        if (detail != null)
            detail.setFavoris(isFavoris);
    }

    @Override
    public void favorisDetailChanged(boolean isChecked) {
        adapter.notifyItemChanged(adapter.getmSelectedPosition());
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        if (savedInstanceState != null && detail!=null) {
            savedInstanceState.putInt("Position", detail.getMyVideoView().getCurrentPosition());
            detail.getMyVideoView().pause();
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null && detail!=null) {
            // Restore last state for checked position.
            int position = savedInstanceState.getInt("Position");
            detail.getMyVideoView().seekTo(position);
        }
    }
}
