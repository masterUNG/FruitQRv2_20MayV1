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
public class ShowProductFragment extends Fragment {

    private String idProduct;
    private String titleToolbar = "รายละเอียดผลิตภัณฑ์";
    //เรียก Table 3 ชุด
    private ArrayList<String> productStringArrayList, framerStringArrayList, userStringArrayList, userFramerStringArrayList;
    private Myconstant myconstant = new Myconstant(); //php


    public ShowProductFragment() {
        // Required empty public constructor
    }

    public static ShowProductFragment showProductInstance(String idProduct) {
        ShowProductFragment showProductFragment = new ShowProductFragment();
        Bundle bundle = new Bundle();
        bundle.putString("idProduct", idProduct);
        showProductFragment.setArguments(bundle);
        return showProductFragment;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        idProduct = getArguments().getString("idProduct");
        Log.d("27AprilV3", "Receive idProduct in Fragment ==> " + idProduct);

        //สร้าง Toolbar
        createToolbar();

        //โหลดเดต้า
        loadData();

    }//Main Method

    private void loadData() {
        productStringArrayList = new ArrayList<>();
        framerStringArrayList = new ArrayList<>();
        userStringArrayList = new ArrayList<>();
        userFramerStringArrayList  = new ArrayList<>();

        String[] columnDetailProduct = myconstant.getColumnDetailProduct(); //ดึงค่าจาก myconstane

        try {

            //สำหรับ Product
            GetDataWhereOneColumn getDataWhereOneColumn = new GetDataWhereOneColumn(getActivity());
            getDataWhereOneColumn.execute("id", idProduct, myconstant.getUrlGetProductWhereId());
            String jsonProduct = getDataWhereOneColumn.get();
            Log.d("27AprilV3", "jsonProduct ==>>> " + jsonProduct);

            JSONArray jsonArray = new JSONArray(jsonProduct);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            for (int i = 0; i < columnDetailProduct.length; i += 1) {
                productStringArrayList.add(jsonObject.getString(columnDetailProduct[i]));
                Log.d("18AprilV3", "productStringArrayList[" + i + "] ==> " + productStringArrayList.get(i));
            }

            Log.d("27ApriV4", "id Sent ==> " + productStringArrayList.get(4));


            //สำหรับ User
            GetDataWhereOneColumn getDataWhereOneColumn2 = new GetDataWhereOneColumn(getActivity());
            getDataWhereOneColumn2.execute("id", productStringArrayList.get(1),myconstant.getUrlGetUserWhereId());
            String jsonUser = getDataWhereOneColumn2.get();
            Log.d("27AprilV5", "jsonUser ==>>> " + jsonUser);

            JSONArray jsonArray2 = new JSONArray(jsonUser);
            JSONObject jsonObject2 = jsonArray2.getJSONObject(0);
            String[] columnUser = myconstant.getColumnUser();
            for (int i = 0; i < columnUser.length; i += 1) {
                userStringArrayList.add(jsonObject2.getString(columnUser[i]));
                Log.d("18AprilV5", "userStringArrayList[" + i + "] ==> " + userStringArrayList.get(i));
            }



//            Image Product
            ImageView imageView = getView().findViewById(R.id.imvImage);
            Picasso.get().load(productStringArrayList.get(7)).resize(800,600).into(imageView);

//          name product
            TextView nameeTextView = getView().findViewById(R.id.txtNamee);
            nameeTextView.setText(productStringArrayList.get(5));


//          Amount Product
            TextView productamountTextView = getView().findViewById(R.id.txtProductAmount);
            productamountTextView.setText(productStringArrayList.get(8));

//          Unit Product
            TextView productunitTextView = getView().findViewById(R.id.txtProductUnit);
            productunitTextView.setText(productStringArrayList.get(9));

//          Date Product
            TextView productdateTextView = getView().findViewById(R.id.txtProductdate);
            productdateTextView.setText(productStringArrayList.get(10));

//          List Product
            TextView productlistTextView = getView().findViewById(R.id.txtProductlist);
            productlistTextView.setText(productStringArrayList.get(6));

//          Frist Name Product
            TextView productnameTextView = getView().findViewById(R.id.txtProductName);
            productnameTextView.setText(productStringArrayList.get(2));

//          Address Product
//            TextView productaddressTextView = getView().findViewById(R.id.txtProductAdd);
//            productaddressTextView.setText(userStringArrayList.get(4));

//          Phone Product
//            TextView productphoneTextView = getView().findViewById(R.id.txtProductPhone);
//            productphoneTextView.setText(userStringArrayList.get(5));



//            ShowView
//            Name Fruit
//            TextView fruitTextView = getView().findViewById(R.id.txtFruit);
//            fruitTextView.setText(framerStringArrayList.get(2));
//
////          name Fruit
//            TextView fruittTextView = getView().findViewById(R.id.txtFruitt);
//            fruittTextView.setText(framerStringArrayList.get(2));

//          Amount Fruit
//            TextView fruitamountTextView = getView().findViewById(R.id.txtFruitAmount);
//            fruitamountTextView.setText(framerStringArrayList.get(3));

//          Unit Fruit
//            TextView fruitunitTextView = getView().findViewById(R.id.txtFruitUnit);
//            fruitunitTextView.setText(framerStringArrayList.get(4));

//          Date Fruit
//            TextView fruitdateTextView = getView().findViewById(R.id.txtFruitdate);
//            fruitdateTextView.setText(framerStringArrayList.get(5));

//          Name Fruit
//            TextView fruitnameTextView = getView().findViewById(R.id.txtProductName);
//            fruitnameTextView.setText(userStringArrayList.get(1));

//          Address Fruit
//            TextView fruitaddressTextView = getView().findViewById(R.id.txtProductAdd);
//            fruitaddressTextView.setText(userStringArrayList.get(4));

//          Phone Fruit
//            TextView fruitphoneTextView = getView().findViewById(R.id.txtProductPhone);
//            fruitphoneTextView.setText(userStringArrayList.get(5));


        } catch (Exception e) {
            Log.d("18AprilV3", "e ==>> " + e.toString());
        }

    }

    private void createToolbar() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarProduct);
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
        return inflater.inflate(R.layout.fragment_show_product, container, false);
    }

}