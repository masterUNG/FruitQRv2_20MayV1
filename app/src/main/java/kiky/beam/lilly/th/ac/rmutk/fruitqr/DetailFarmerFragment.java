package kiky.beam.lilly.th.ac.rmutk.fruitqr;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFarmerFragment extends Fragment {

    private String idString;
    private String titleToolbar = "รายละเอียดผลผลิต";
    private Myconstant myconstant = new Myconstant();
    private ArrayList<String> stringArrayList = new ArrayList<>();

    public DetailFarmerFragment() {
        // Required empty public constructor
    }

    public static DetailFarmerFragment DetailFarmerInstance(String idString) {
        DetailFarmerFragment detailFarmerFragment = new DetailFarmerFragment();
        Bundle bundle = new Bundle();
        bundle.putString("idUser", idString);
        detailFarmerFragment.setArguments(bundle);
        return detailFarmerFragment;



    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //สร้าง Toolbar
        createToolbar();
    }

    private void createToolbar() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarFarmer);
        ((ShowFarmerActivity) getActivity()).setSupportActionBar(toolbar);
        ((ShowFarmerActivity) getActivity()).getSupportActionBar().setTitle(titleToolbar);
        ((ShowFarmerActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true); //สร้างปุ่ม
        ((ShowFarmerActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_farmer, container, false);
    }

}
