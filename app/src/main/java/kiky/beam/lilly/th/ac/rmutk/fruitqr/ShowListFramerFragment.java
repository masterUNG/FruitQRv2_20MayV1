package kiky.beam.lilly.th.ac.rmutk.fruitqr;


import android.content.Context;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShowListFramerFragment extends Fragment {

    private String typeUserString, idRecordString, search;
    private Myconstant myconstant = new Myconstant();
    private boolean searchABoolean = true;  // true ==> Search ชื่อผลไม้ false ชื่อสวน
    private int indexSearch = 1;
    private RadioGroup radioGroup;

    public ShowListFramerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //        Type Controller
        typeController();

        //การสร้าง Create Recycler
        createRecyclerView(0);

//        Search Controller
        searchController();



    }   // Main Method

    private void typeController() {
        radioGroup = getView().findViewById(R.id.ragSearch);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radFruit:
                        indexSearch = 1;
                        break;
                    case R.id.radGarden:
                        indexSearch = 2;
                        break;
                }
            }
        });
    }

    private void searchController() {
        Button button = getView().findViewById(R.id.btnSeach);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyAlertDialog myAlertDialog = new MyAlertDialog(getActivity());

                EditText editText = getView().findViewById(R.id.edtSearch);
                search = editText.getText().toString().trim();
                if (search.isEmpty()) {
                    myAlertDialog.normalDialog("มีช่องว่าง", "ค้นหาห้ามมีช่องว่าง");
                } else {

                    createRecyclerView(indexSearch);

                }

            }
        });
    }

    private void createRecyclerView(int indexSearch) {
        try {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences(myconstant.getNameFileSharePreference(), Context.MODE_PRIVATE);
            typeUserString = sharedPreferences.getString("TypeUser", "");
            idRecordString = sharedPreferences.getString("idLogin", "");

            int index = Integer.parseInt(typeUserString.trim()); //เอามาเทสก่อน

            String result = null;

            if(index == 1) { //สถานะเท่ากับ 1

                if (indexSearch == 0) {
                    GetAllDataThread getAllDataThread = new GetAllDataThread(getActivity());
                    getAllDataThread.execute(myconstant.getUrlGetAllDetailFramer()); //อ่านทุกช่อง
                    result = getAllDataThread.get();
                } else if (indexSearch == 1) {
//                    Search by ผลไม้
                    Log.d("15MayV1", "Search by ผลไม้");
                    Log.d("15MayV1", "Search ==> " + search);

                    GetDataWhereOneColumn getDataWhereOneColumn = new GetDataWhereOneColumn(getActivity());
                    getDataWhereOneColumn.execute("Name", search, myconstant.getUrlGetDetailFarmerWhereName());
                    result = getDataWhereOneColumn.get();

                    if (result.equals("null")) {
                        MyAlertDialog myAlertDialog = new MyAlertDialog(getActivity());
                        myAlertDialog.normalDialog("ไม่มีชื่อผลไม้นี้", "ไม่มีชื่อผลไม้นี้ในฐานข้อมูล");
                    }

                    Log.d("15MayV1", "result ==> " + result);

                } else {
//                    Search by สวน
                    Log.d("15MayV1", "Search by สวน");

                    GetDataWhereOneColumn getDataWhereOneColumn = new GetDataWhereOneColumn(getActivity());
                    getDataWhereOneColumn.execute("Name", search, myconstant.getUrlGetMasterWhereName());

                    String resultGarden = getDataWhereOneColumn.get();
                    Log.d("15MayV1", "resultGarden ==> " + resultGarden);
                    MyAlertDialog myAlertDialog = new MyAlertDialog(getActivity());

                    if (resultGarden.equals("null")) {
                        myAlertDialog.normalDialog("ไม่มีชื่อสวนนี้", "ไม่มีชื่อสวนนี้ในฐานข้อมูล");
                    } else {
                        JSONArray jsonArray = new JSONArray(resultGarden);
                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                        String idGarden = jsonObject.getString("id");
                        Log.d("15MayV1", "idGarden ==> " + idGarden);

                        GetDataWhereOneColumn getDataWhereOneColumn1 = new GetDataWhereOneColumn(getActivity());
                        getDataWhereOneColumn1.execute("idRecord", idGarden, myconstant.getUrlGetDetailFramerWhereIdRecord());
                        result = getDataWhereOneColumn1.get();

                    }


                }

            } else{ //สถานะมากกว่า1

                radioGroup.setVisibility(View.INVISIBLE);


                if (indexSearch == 0) {

                    GetDataWhereOneColumn getDataWhereOneColumn = new GetDataWhereOneColumn(getActivity());
                    getDataWhereOneColumn.execute("idRecord", idRecordString, myconstant.getUrlGetDetailFramerWhereIdRecord());
                    result = getDataWhereOneColumn.get();
                } else {
//                    Search ผลไม้
                    Log.d("15MayV1", "Search by ผลไม้");

                    Log.d("15MayV1", "idRecordString ==> " + idRecordString);
                    Log.d("15MayV1", "Search ==> " + search);

                    GetDataWhereTwoColunmThread getDataWhereTwoColunmThread = new GetDataWhereTwoColunmThread(getActivity());
                    getDataWhereTwoColunmThread.execute("idRecord", idRecordString.trim(), "Name", search.trim(), myconstant.getUrlGetDetailFramerWhereIdRecordAnNameLilly());
                    result = getDataWhereTwoColunmThread.get();

                    Log.d("15MayV1", "result ==> " + result);

                }

            }

            Log.d("7April", "result ==> " + result);

            JSONArray jsonArray = new JSONArray(result);
            ArrayList<String> nameStringArrayList = new ArrayList<>();
            ArrayList<String> amountStringArrayList = new ArrayList<>();
            ArrayList<String> dateStringArrayList = new ArrayList<>();

            ArrayList<String> nameOwnerStringArrayList = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i += 1) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                nameStringArrayList.add(jsonObject.getString("Name"));
                amountStringArrayList.add(jsonObject.getString("Amount")+ " " +jsonObject.getString("Unit"));
                dateStringArrayList.add(jsonObject.getString("Date"));

                nameOwnerStringArrayList.add(findNameOwer(jsonObject.getString("idRecord")));
            }

            Log.d("7April", "name ==>>> " + nameStringArrayList.toString());
            Log.d("7April", "amount ==>>> " + amountStringArrayList.toString());
            Log.d("7April", "date ==>>> " +dateStringArrayList.toString());

            RecyclerView recyclerView = getView().findViewById(R.id.recyclerFramer);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(linearLayoutManager);

            ShowListFramerAdapter showListFramerAdapter = new ShowListFramerAdapter(getActivity(), nameStringArrayList,
                    amountStringArrayList, dateStringArrayList, nameOwnerStringArrayList, new OnClickItem() {//กด ctrl+p
                @Override
                public void onClickitem(View view, int position) {

                }
            });
            recyclerView.setAdapter(showListFramerAdapter);


        }catch (Exception e){
            Log.d("7April", "eror ==> "+e.toString());
        }
    }

    private String findNameOwer(String idRecord) {//ดึงชื่อมาจากฐานข้อมูล
        try{
            GetDataWhereOneColumn getDataWhereOneColumn = new GetDataWhereOneColumn(getActivity());
            getDataWhereOneColumn.execute("id", idRecord, myconstant.getUrlGetUserWhereId());
            String result = getDataWhereOneColumn.get();

            JSONArray jsonArray = new JSONArray(result);
            JSONObject jsonObject = jsonArray.getJSONObject(0);

            return jsonObject.getString("Name");

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_list_framer, container, false);
    }

}