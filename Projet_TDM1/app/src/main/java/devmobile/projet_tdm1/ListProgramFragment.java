package devmobile.projet_tdm1;


import android.app.Activity;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import devmobile.projet_tdm1.model.ProgrammeTele;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 */
public class ListProgramFragment extends Fragment implements ItemProgrammeAdapter.ClickListener{


    private OnProgrammeItemSelectedListener listener;
    private RecyclerView recyclerView;
    private static final String DATA_KEY = "data_key";
    private List<ProgrammeTele> data = new ArrayList<ProgrammeTele>() ;
    private ItemProgrammeAdapter adapter;

    public static ListProgramFragment newInstance(ArrayList<ProgrammeTele> data, OnProgrammeItemSelectedListener listener){

        ListProgramFragment fragment = new ListProgramFragment(listener);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(DATA_KEY, data);
        fragment.setArguments(bundle);

        return fragment;
    }

    public ListProgramFragment(){
        super();
    }

    public ListProgramFragment(OnProgrammeItemSelectedListener listener){
        super();
        this.listener = listener;
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
        View view = inflater.inflate(R.layout.fragment_list_program,
                container, false);

        if(savedInstanceState == null) {
            recyclerView = (RecyclerView) view.findViewById(R.id.list_program);
            adapter = new ItemProgrammeAdapter(getActivity(), data);
            adapter.setClickListener(this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        return view;
    }

    @Override
    public void itemClicked(View view, int position) {

        if(listener != null) {
            listener.OnProgrammeSelected(data.get(position));
        }
    }

    public interface OnProgrammeItemSelectedListener {
        public void OnProgrammeSelected(ProgrammeTele programme);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}