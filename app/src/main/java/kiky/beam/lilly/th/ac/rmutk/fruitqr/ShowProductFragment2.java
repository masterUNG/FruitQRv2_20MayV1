package kiky.beam.lilly.th.ac.rmutk.fruitqr;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShowProductFragment2 extends Fragment {

    private String idProduct;

    private String titleToolbar = "รายละเอียดผลิตภัณฑ์";
    //เรียก Table 3 ชุด
    private ArrayList<String> productStringArrayList, framerStringArrayList, userStringArrayList, userFramerStringArrayList;
    private Myconstant myconstant = new Myconstant(); //php

    private EditText txtQRcode;

    private ImageView imageeView;

    public ShowProductFragment2() {
        // Required empty public constructor
    }

    public static ShowProductFragment2 showProductInstance2(String idProduct) {
        ShowProductFragment2 showProductFragment2 = new ShowProductFragment2();
        Bundle bundle = new Bundle();
        bundle.putString("idProduct", idProduct);
        showProductFragment2.setArguments(bundle);
        return showProductFragment2;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        txtQRcode = getView().findViewById(R.id.txtQRcode);

        imageeView = getView().findViewById(R.id.imageeView);



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

            String urlImageQR = jsonObject.getString("ImageQR");

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

//                      Amount Product
            TextView amountpbTextView = getView().findViewById(R.id.txtProductAmount2);
            amountpbTextView.setText(productStringArrayList.get(12));

//          Unit Product
            TextView productunit2TextView = getView().findViewById(R.id.txtProductUnit2);
            productunit2TextView.setText(productStringArrayList.get(13));


//            Show Image QRcoe
            ImageView imageView1 = getView().findViewById(R.id.imvQRcode);
            Picasso.get().load(urlImageQR).resize(250, 250).into(imageView1);



        } catch (Exception e) {
            Log.d("18AprilV3", "e ==>> " + e.toString());
        }

    }

    private void createToolbar() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarProduct2);
        ((ProductActivity2) getActivity()).setSupportActionBar(toolbar);
        ((ProductActivity2) getActivity()).getSupportActionBar().setTitle(titleToolbar);
        ((ProductActivity2) getActivity()).getSupportActionBar().setHomeButtonEnabled(true); //สร้างปุ่ม
        ((ProductActivity2) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
        return inflater.inflate(R.layout.fragment_show_product_2, container, false);
    }

}
