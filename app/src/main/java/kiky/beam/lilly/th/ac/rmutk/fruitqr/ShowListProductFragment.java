package kiky.beam.lilly.th.ac.rmutk.fruitqr;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShowListProductFragment extends Fragment {
    private Myconstant myconstant = new Myconstant();

    private String idType, idLogin;



    public ShowListProductFragment() {
        // Required empty public constructor
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(myconstant.getNameFileSharePreference(), Context.MODE_PRIVATE);
        idType = sharedPreferences.getString("TypeUser", "1");
        idLogin = sharedPreferences.getString("idLogin", "1");

//      Create RecylerView
        createRecylerView();


    }//Main Method

    private void createRecylerView() {
        RecyclerView recyclerView = getView().findViewById(R.id.recyclerShowListProduct);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        try {

            String jsonString = null;

            if (Integer.parseInt(idType) == 1) {
                GetAllDataThread getAllDataThread = new GetAllDataThread(getActivity());
                getAllDataThread.execute(myconstant.getUrlGetAllDeatailProduct());

                jsonString = getAllDataThread.get();
                Log.d("AprilV1", jsonString);
            } else {
                GetDataWhereOneColumn getDataWhereOneColumn = new GetDataWhereOneColumn(getActivity());
                getDataWhereOneColumn.execute("idRecord", idLogin, myconstant.getUrlGetProductWhereIdRecord());
                jsonString = getDataWhereOneColumn.get();
            }

            ArrayList<String> nameStringArratList = new ArrayList<>();
            ArrayList<String> dateStringArratList = new ArrayList<>();
            ArrayList<String> amountStringArratList = new ArrayList<>();
            ArrayList<String> unitStringArratList = new ArrayList<>();
            ArrayList<String> iconStringArratList = new ArrayList<>();
            final ArrayList<String> idDetailProductStringArratList = new ArrayList<>();

            Log.d("27AprilV3", "Json ==> " + jsonString);

            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); i += 1) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                nameStringArratList.add(jsonObject.getString("Name"));
                dateStringArratList.add(jsonObject.getString("Date"));
                amountStringArratList.add(jsonObject.getString("Amount"));
                unitStringArratList.add(jsonObject.getString("Unit"));
                iconStringArratList.add(jsonObject.getString("Image"));
                idDetailProductStringArratList.add(jsonObject.getString("id"));

                Log.d("18AprilC1", iconStringArratList.get(i));

            }

            ShowListAdapter showListAdapter = new ShowListAdapter(getActivity(), nameStringArratList,
                    dateStringArratList, amountStringArratList, unitStringArratList, iconStringArratList, new OnClickItem() {
                @Override //กดที่รายการ จะเข้าไปดูรายละเอัยด
                public void onClickitem(View view, int position) {
                    Log.d("18AprilV1", "Position ==> " + position);

                    Intent intent = new Intent(getActivity(), ProductActivity.class);
                    intent.putExtra("idProduct", idDetailProductStringArratList.get(position));
                    startActivity(intent);


                }
            });
            recyclerView.setAdapter(showListAdapter);


        } catch (Exception e) {
            Log.d("27AprilV3", "e ==> " + e.toString());
        }

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_list_product, container, false);
    }

}