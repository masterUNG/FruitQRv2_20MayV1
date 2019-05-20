package kiky.beam.lilly.th.ac.rmutk.fruitqr;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShowMemberFragment extends Fragment {

    private String idUser;
    private String titleToolbar = "รายละเอียดสมาชิก";
    //เรียก Table 2 ชุด
    private ArrayList<String> userStringArrayList, typeStringArrayList;
    private Myconstant myconstant = new Myconstant(); //php

    public ShowMemberFragment() {
        // Required empty public constructor
    }

    public static ShowMemberFragment showMemberFragment(String idUser) {
        ShowMemberFragment showMemberFragment = new ShowMemberFragment();
        Bundle bundle = new Bundle();
        bundle.putString("idUser", idUser);
        showMemberFragment.setArguments(bundle);
        return showMemberFragment;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        idUser = getArguments().getString("idUser");
        Log.d("18AprilV2", "Receive idUser ==> " + idUser);

        //สร้าง Toolbar
        createToolbar();

    }

    private void createToolbar() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarMember);
        ((ProductActivity) getActivity()).setSupportActionBar(toolbar);
        ((ProductActivity) getActivity()).getSupportActionBar().setTitle(titleToolbar);
        ((ProductActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true); //สร้างปุ่ม
        ((ProductActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
        return inflater.inflate(R.layout.fragment_show_member, container, false);
    }

}
