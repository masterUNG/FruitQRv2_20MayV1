package kiky.beam.lilly.th.ac.rmutk.fruitqr;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;



/**
 * A simple {@link Fragment} subclass.
 */
public class ShowFarmerFragment extends Fragment {

    private String idFarmer;
    private String titleToolbar = "รายละเอียดผลผลิต";
    private ArrayList<String> framerStringArrayList, userStringArrayList;
    private Myconstant myconstant = new Myconstant(); //php

    public ShowFarmerFragment() {
        // Required empty public constructor
    }


    public static ShowFarmerFragment showFarmerInstance(String idFarmer) {
        ShowFarmerFragment showFarmerFragment = new ShowFarmerFragment();
        Bundle bundle = new Bundle();
        bundle.putString("idFarmer", idFarmer);
        showFarmerFragment.setArguments(bundle);
        return showFarmerFragment;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        idFarmer = getArguments().getString("idFarmer");
        Log.d("27AprilV3", "Receive idProduct in Fragment ==> " + idFarmer);

        //สร้าง Toolbar
        createToolbar();


        //โหลดเดต้า
        loadData();





    }//Main Method

    private void loadData() {
        framerStringArrayList = new ArrayList<>();
        userStringArrayList = new ArrayList<>();

        String[] columnDetailFramer = myconstant.getColumnDetailFramer(); //ดึงค่าจาก myconstane

        try {

            //สำหรับ Product
            GetDataWhereOneColumn getDataWhereOneColumn = new GetDataWhereOneColumn(getActivity());
            getDataWhereOneColumn.execute("id", idFarmer, myconstant.getUrlGetFarmerFruitWhereId());
            String jsonProduct = getDataWhereOneColumn.get();
            Log.d("27AprilV3", "jsonProduct ==>>> " + jsonProduct);

            JSONArray jsonArray = new JSONArray(jsonProduct);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            for (int i = 0; i < columnDetailFramer.length; i += 1) {
                framerStringArrayList.add(jsonObject.getString(columnDetailFramer[i]));
                Log.d("18AprilV3", "framerStringArrayList[" + i + "] ==> " + framerStringArrayList.get(i));

            }

            Log.d("27ApriV4", "id Sent ==> " + framerStringArrayList.get(1));

            //สำหรับ User
            GetDataWhereOneColumn getDataWhereOneColumn2 = new GetDataWhereOneColumn(getActivity());
            getDataWhereOneColumn2.execute("id", framerStringArrayList.get(1),myconstant.getUrlGetUserWhereId());
            String jsonUser = getDataWhereOneColumn2.get();
            Log.d("27AprilV5", "jsonUser ==>>> " + jsonUser);

            JSONArray jsonArray2 = new JSONArray(jsonUser);
            JSONObject jsonObject2 = jsonArray2.getJSONObject(0);
            String[] columnUser = myconstant.getColumnUser();
            for (int i = 0; i < columnUser.length; i += 1) {
                userStringArrayList.add(jsonObject2.getString(columnUser[i]));
                Log.d("18AprilV5", "userStringArrayList[" + i + "] ==> " + userStringArrayList.get(i));
            }

//          name
            TextView nameeTextView = getView().findViewById(R.id.txtNamee);
            nameeTextView.setText(framerStringArrayList.get(2));

            TextView amountTextView = getView().findViewById(R.id.txtAmount);
            amountTextView.setText(framerStringArrayList.get(3));

            TextView unitTextView = getView().findViewById(R.id.txtUnit);
            unitTextView.setText(framerStringArrayList.get(4));

            TextView dateTextView = getView().findViewById(R.id.txtDate);
            dateTextView.setText(framerStringArrayList.get(5));

            TextView dateoutTextView = getView().findViewById(R.id.txtDate2);
            dateoutTextView.setText(framerStringArrayList.get(6));

            TextView lotTextView = getView().findViewById(R.id.txtLot);
            lotTextView.setText(framerStringArrayList.get(7));

            //          Frist Name Product
//            TextView farmernameTextView = getView().findViewById(R.id.txtuser);
//            farmernameTextView.setText(userStringArrayList.get(1));

            TextView phoneTextView = getView().findViewById(R.id.txtphone);
            phoneTextView.setText(userStringArrayList.get(5));

            TextView addcontectTextView = getView().findViewById(R.id.txtaddcontect);
            addcontectTextView.setText(userStringArrayList.get(9));

        } catch (Exception e) {
            Log.d("18AprilV3", "e ==>> " + e.toString());
        }

    }

    private void createToolbar() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarFarmerr);
        ((FarmerActivity) getActivity()).setSupportActionBar(toolbar);
        ((FarmerActivity) getActivity()).getSupportActionBar().setTitle(titleToolbar);
        ((FarmerActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true); //สร้างปุ่ม
        ((FarmerActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
        return inflater.inflate(R.layout.fragment_show_farmer, container, false);
    }

}
