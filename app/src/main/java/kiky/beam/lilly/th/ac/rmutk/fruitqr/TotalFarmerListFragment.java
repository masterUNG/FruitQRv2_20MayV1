package kiky.beam.lilly.th.ac.rmutk.fruitqr;


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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TotalFarmerListFragment extends Fragment {

    private String search;
    private int indexSearch = 1;

    public TotalFarmerListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Create RecyclerView
        createRecyclerView();

        //Search Controller
//        searchController();
    }

//    private void searchController() {
//        Button button = getView().findViewById(R.id.btnSeach1);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                MyAlertDialog myAlertDialog = new MyAlertDialog(getActivity());
//
//                EditText editText = getView().findViewById(R.id.edtSearch1);
//                search = editText.getText().toString().trim();
//                if (search.isEmpty()) {
//                    myAlertDialog.normalDialog("มีช่องว่าง", "ค้นหาห้ามมีช่องว่าง");
//                } else {
//
//                    createRecyclerView();
//
//                }
//
//            }
//        });
//
//    }

    private void createRecyclerView() {
        RecyclerView recyclerView = getView().findViewById(R.id.recyclerTotalFruit);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        Myconstant myconstant = new Myconstant();
        ArrayList<String> nameFruidStrings = new ArrayList<>();
        ArrayList<String> amountStrings = new ArrayList<>();
        ArrayList<String> unitStrings = new ArrayList<>();

        String result = null;

        try {

            GetAllDataThread getAllDataThread = new GetAllDataThread(getActivity());
            getAllDataThread.execute(myconstant.getUrlGetTypeFruit());
            String response = getAllDataThread.get();
            Log.d("20MayV1", "reaponse ==> " + response);


//            GetDataWhereOneColumn getDataWhereOneColumn = new GetDataWhereOneColumn(getActivity());
//            getDataWhereOneColumn.execute("NameFruit", search, myconstant.getUrlGetTypeTruitWhereNameFruid());
//            result = getDataWhereOneColumn.get();
//
//            if (result.equals("null")) {
//                MyAlertDialog myAlertDialog = new MyAlertDialog(getActivity());
//                myAlertDialog.normalDialog("ไม่มีชื่อผลไม้นี้", "ไม่มีชื่อผลไม้นี้ในฐานข้อมูล");
//
//            }
//
//            Log.d("15MayV1", "result ==> " + result);

            JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i < jsonArray.length(); i += 1) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                nameFruidStrings.add(jsonObject.getString("NameFruit"));
                amountStrings.add(jsonObject.getString("Amount"));
                unitStrings.add(jsonObject.getString("Unit"));

            }

            TotalFruitAdapter totalFruitAdapter = new TotalFruitAdapter(getActivity(), nameFruidStrings, amountStrings,
                    unitStrings, new OnClickItem() {
                @Override
                public void onClickitem(View view, int position) {
                    Log.d("20MayV1", "You Click position ==> " + position);
                }
            });

            recyclerView.setAdapter(totalFruitAdapter);


        } catch (Exception e) {
            Log.d("20MayV1", "e atr TotalFarmeTotla");
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_total_farmer_list, container, false);
    }

}
