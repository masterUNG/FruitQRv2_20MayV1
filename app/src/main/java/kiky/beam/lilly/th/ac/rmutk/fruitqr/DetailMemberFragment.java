package kiky.beam.lilly.th.ac.rmutk.fruitqr;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailMemberFragment extends Fragment {

    private String idString;
    private String titleToolbar = "รายละเอียดสมาชิก";
    private Myconstant myconstant = new Myconstant();
    private ArrayList<String> stringArrayList = new ArrayList<>();
    private String[] typeUsers = {"", "ผู้ดูแลระบบ", "เจ้าของผลผลิต", "เจ้าของผลิตภัณฑ์", "เจ้าของผลผลิตและเจ้าของผลิตภัณฑ์"};
    private String typeUserSting;

    public DetailMemberFragment() {
        // Required empty public constructor
    }

    public static DetailMemberFragment detailMemberInstance(String idString) {
        DetailMemberFragment detailMemberFragment = new DetailMemberFragment();
        Bundle bundle = new Bundle();
        bundle.putString("idUser", idString);
        detailMemberFragment.setArguments(bundle);
        return detailMemberFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //สร้าง Toolbar
        createToolbar();

        idString = getArguments().getString("idUser");
        Log.d("27AprilV1", "idString ==> " + idString);

        try {

            GetDataWhereOneColumn getDataWhereOneColumn = new GetDataWhereOneColumn(getActivity());
            getDataWhereOneColumn.execute("id", idString, myconstant.getUrlGetUserWhereId());
            String result = getDataWhereOneColumn.get();
            Log.d("27AprilV1", result);

            JSONArray jsonArray = new JSONArray(result);
            JSONObject jsonObject = jsonArray.getJSONObject(0);

            String[] strings = myconstant.getColumnUser();

            for (int i = 0; i < strings.length; i += 1) {
                stringArrayList.add(jsonObject.getString(strings[i]));
                Log.d("27AprilV2", "stringArrayList[" + i + "] ==> " + stringArrayList.get(i));
            }





            //            ShowView

//            Nameshop
            TextView nameshopTextView = getView().findViewById(R.id.txtNameShop);
            nameshopTextView.setText(stringArrayList.get(1));

            TextView firstnameTextView = getView().findViewById(R.id.txtFirstName);
            firstnameTextView.setText(stringArrayList.get(2));

            TextView lastnameTextView = getView().findViewById(R.id.txtLastName);
            lastnameTextView.setText(stringArrayList.get(3));

            TextView addressTextView = getView().findViewById(R.id.txtAddress);
            addressTextView.setText(stringArrayList.get(4));


            TextView phonenameTextView = getView().findViewById(R.id.txtPhone);
            phonenameTextView.setText(stringArrayList.get(5));
////


            typeUserSting = typeUsers[Integer.parseInt(stringArrayList.get(8))];
            Log.d("27AprilV2", "typeUser ==> " + typeUserSting);


            TextView typeuserTextView = getView().findViewById(R.id.txtType);
            typeuserTextView.setText(typeUserSting);

//            TextView addcontactTextView = getView().findViewById(R.id.txtaddcontact);
//            addcontactTextView.setText(stringArrayList.get(9));

        } catch (Exception e) {
            e.printStackTrace();
        }


    }   // Main Method

    private void createToolbar() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarMember);
        ((ShowMemberActivity) getActivity()).setSupportActionBar(toolbar);
        ((ShowMemberActivity) getActivity()).getSupportActionBar().setTitle(titleToolbar);
        ((ShowMemberActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true); //สร้างปุ่ม
        ((ShowMemberActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
        return inflater.inflate(R.layout.fragment_detail_member, container, false);
    }

}