package kiky.beam.lilly.th.ac.rmutk.fruitqr;


import android.content.Intent;
import android.os.Binder;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    private  String qrCode, nameString, imageString, amountString, unitString,
            dateString, idShopString, nameShopString, addressString, phoneString;
    private boolean loginABoolean;

    public DetailFragment() {
        // Required empty public constructor
    }

    public static DetailFragment detailFragment(String resuliQR, boolean b) {

        DetailFragment detailFragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("QRcode", resuliQR);
        bundle.putBoolean("Login", b);
        detailFragment.setArguments(bundle);
        return detailFragment;



    }

    //รับค่า QR เพื่อจะเอาไป where
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        loginABoolean = getArguments().getBoolean("Login");
        Log.d("3FebV2", "LoginBool at Detail ==> " + loginABoolean);

//        Create Toolbar
        createToolbar();

        showView();


    } //Main Method

    private void createToolbar() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarProduct);
        ((QRActivity)getActivity()).setSupportActionBar(toolbar);
        ((QRActivity) getActivity()).getSupportActionBar().setTitle("รายละเอียดผลิตภัณฑ์");
        ((QRActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((QRActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (loginABoolean) {
                    Intent intent = getActivity().getIntent();
                    getActivity().finish();
                    startActivity(intent);
                } else {
                    getActivity().finish();
                }

            }
        });


    }

    private void showView() {
        qrCode = getArguments().getString("QRcode");
        if (!qrCode.isEmpty()) {


//            Have QR codeValue
            try {

                Myconstant myconstant = new Myconstant();
                GetDataWhereOneColumn getDataWhereOneColumn = new GetDataWhereOneColumn(getActivity());
                getDataWhereOneColumn.execute("QRcode", qrCode, myconstant.getUrlGetDetailProductWhereQR());

                String json = getDataWhereOneColumn.get();
                Log.d("2FebV1", "json ==> " + json);

                JSONArray jsonArray = new JSONArray(json);
                JSONObject jsonObject = jsonArray.getJSONObject(0);


                String nameRecordString = jsonObject.getString("NameRecord");
                String namefruitString = jsonObject.getString("Name");
                String detailString = jsonObject.getString("Detail");
                String imageString = jsonObject.getString("Image");
                String amountString = jsonObject.getString("Amount");
                String unitString = jsonObject.getString("Unit");
                String dateString = jsonObject.getString("Date");


//                String imageString = jsonObject.getString("Image");
//                String namefruitString = jsonObject.getString("Name");
//                String amountString = jsonObject.getString("Amount");
//                String unitString = jsonObject.getString("Unit");
//                String dateString = jsonObject.getString("Date");
//                String detailString = jsonObject.getString("Detail");
//                String nameRecordString = jsonObject.getString("NameRecord");


                TextView nameTextView = getView().findViewById(R.id.txtNamee);
                nameTextView.setText(nameRecordString);

                TextView namefruitTextView = getView().findViewById(R.id.txtNamee);
                namefruitTextView.setText(namefruitString);

                TextView detailTextView = getView().findViewById(R.id.txtProductlist);
                detailTextView.setText(detailString);

                ImageView imageView = getView().findViewById(R.id.imvImage);
                Picasso.get().load(imageString).into(imageView);

//                TextView amountTextView = getView().findViewById(R.id.txtProductAmount);
//                amountTextView.setText(amountString);
//
//                TextView unitTextView = getView().findViewById(R.id.txtProductUnit);
//                unitTextView.setText(unitString);

                TextView dateTextView = getView().findViewById(R.id.txtProductdate);
                dateTextView.setText(dateString);
















            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_product, container, false);
    }

}