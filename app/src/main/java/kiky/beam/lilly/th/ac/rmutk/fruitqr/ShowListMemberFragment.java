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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShowListMemberFragment extends Fragment {

    private Myconstant myconstant = new Myconstant();
    private String typeUserString, idRecordString, search;

    public ShowListMemberFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //      Create RecylerView
        createRecylerView();

    }//Main Method

    private void createRecylerView() {

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(myconstant.getNameFileSharePreference(), Context.MODE_PRIVATE);
        typeUserString = sharedPreferences.getString("TypeUser", "");
        idRecordString = sharedPreferences.getString("idLogin", "");

        RecyclerView recyclerView = getView().findViewById(R.id.recyclerShowListMember);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);


        try {

            GetAllDataThread getAllDataThread = new GetAllDataThread(getActivity());
            getAllDataThread.execute(myconstant.getUrlgetAllDataOrderByDesc());

            String jsonString = getAllDataThread.get();
            Log.d("23AprilV1", jsonString);


            ArrayList<String> nameStringArratList = new ArrayList<>();
            ArrayList<String> firstnameStringArratList = new ArrayList<>();
            ArrayList<String> secondnameStringArratList = new ArrayList<>();
            ArrayList<String> phoneStringArratList = new ArrayList<>();
            ArrayList<String> typeStringArratList = new ArrayList<>();
            final ArrayList<String> idmastertabelStringArratList = new ArrayList<>();


            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); i += 1) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                nameStringArratList.add(jsonObject.getString("Name"));
                firstnameStringArratList.add(jsonObject.getString("FirstName"));
                secondnameStringArratList.add(jsonObject.getString("SecondName"));
                phoneStringArratList.add(jsonObject.getString("Phone"));

                typeStringArratList.add(jsonObject.getString("TypeUser"));

                idmastertabelStringArratList.add(jsonObject.getString("id"));

            }

            ShowListMemberAdapter showListMemberAdapter = new ShowListMemberAdapter(getActivity(), nameStringArratList,
                    firstnameStringArratList, secondnameStringArratList, phoneStringArratList, typeStringArratList, new OnClickItem() {
                @Override
                public void onClickitem(View view, int position) {
                    Log.d("23AprilV1", "Position ==> " + position);

                    Intent intent = new Intent(getActivity(), ShowMemberActivity.class); //จะให้มันโยนค่าไปหน้าไหน
                    intent.putExtra("idUser", idmastertabelStringArratList.get(position));
                    startActivity(intent);
                }
            });
            recyclerView.setAdapter(showListMemberAdapter);


        } catch (Exception e) {
            Log.d("23AprilV1", "e ==> " + e.toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_list_member, container, false);
    }

}
