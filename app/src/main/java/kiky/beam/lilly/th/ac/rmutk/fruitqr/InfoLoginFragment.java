package kiky.beam.lilly.th.ac.rmutk.fruitqr;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AlertDialogLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class InfoLoginFragment extends Fragment {

    private Myconstant myconstant = new Myconstant();
    private String idRecord;
    private  String typeString;
    private  boolean aBoolean = true;

    public InfoLoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //        Show View
        showView();

//        Edit Controller
        editController();

//        uploadValueToServer();

//        onBackPressed();
    }

    private void editController() {
        Button button = getView().findViewById(R.id.btnInfoLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadValueToServer();
            }
        });
    }

    private void showView() {
        try {


            //ดึงค่า IdLogin
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences(myconstant.getNameFileSharePreference(), Context.MODE_PRIVATE);
            idRecord = sharedPreferences.getString("idLogin", "");


            GetDataWhereOneColumn getDataWhereOneColumn = new GetDataWhereOneColumn(getActivity());
            getDataWhereOneColumn.execute("id", idRecord, myconstant.getUrlGetUserWhereId());

            String result = getDataWhereOneColumn.get();
            JSONArray jsonArray = new JSONArray(result);
            JSONObject jsonObject = jsonArray.getJSONObject(0);

            TextView nameTextView = getView().findViewById(R.id.editName);
            nameTextView.setText(jsonObject.getString("Name"));

            TextView firstnameTextView = getView().findViewById(R.id.editFristname);
            firstnameTextView.setText(jsonObject.getString("FirstName"));

            TextView secondnameTextView = getView().findViewById(R.id.editSurname);
            secondnameTextView.setText(jsonObject.getString("SecondName"));

            TextView addressTextView = getView().findViewById(R.id.editAddress);
            addressTextView.setText(jsonObject.getString("Address"));

            TextView phoneTextView = getView().findViewById(R.id.editPhone);
            phoneTextView.setText(jsonObject.getString("Phone"));

            TextView userTextView = getView().findViewById(R.id.editUser);
            userTextView.setText(jsonObject.getString("User"));

            TextView passTextView = getView().findViewById(R.id.editPassword);
            passTextView.setText(jsonObject.getString("Password"));

            TextView typeTextView = getView().findViewById(R.id.editType);
            typeTextView.setText(jsonObject.getString("TypeUser"));




        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void uploadValueToServer() {

//        Initial View
        EditText namsShopEditText = getView().findViewById(R.id.editName);
        EditText nameEditText = getView().findViewById(R.id.editFirstname);
        EditText surnameEditText = getView().findViewById(R.id.editSurname);
        EditText addressEditText = getView().findViewById(R.id.editAddress);
        EditText phoneEditText = getView().findViewById(R.id.editPhone);
        EditText userEditText = getView().findViewById(R.id.editUser);
        EditText passwordEditText = getView().findViewById(R.id.editPassword);
//        EditText typeEditText = getView().findViewById(R.id.editType);


        String nameShop = namsShopEditText.getText().toString().toString();

        String name = nameEditText.getText().toString().trim(); //แปลงค่าText ให้เป็น String , trim ลบค่าที่เว้นวรรคอัตโนวัติ
        String surname = surnameEditText.getText().toString().trim();
        String address = addressEditText.getText().toString().trim();
        String phone = phoneEditText.getText().toString().trim();
        String user = userEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
//        String type = typeEditText.getText().toString().trim();

        MyAlertDialog myAlertDialog = new MyAlertDialog(getActivity()); //การสร้างออปเจ็ค
//        check Spqce  การหาช่องว่าง
        if (nameShop.isEmpty()|| name.isEmpty()|| surname.isEmpty() || address.isEmpty() || phone.isEmpty() || user.isEmpty() || password.isEmpty()) {//isEmpty ถ้าไม่มีการกรอกเป็น true

//            Have Space
            myAlertDialog.normalDialog("Have Space", "Please Fill Every Blank");
        } else if (aBoolean){
//            Non Choose
            myAlertDialog.normalDialog("Non Choose Type User", "Please Choose Type User");
        }else{

//            Upload to Server
            try {





                Myconstant myconstant = new Myconstant();
                AddUserThread addUserThread = new AddUserThread(getActivity());
                addUserThread.execute(nameShop,name,surname,address,phone,user,password,myconstant.getUrlEditUserWhereId());

                String result = addUserThread.get();
                Log.d("27AprilV1", "result ==> " + result);

                if (Boolean.parseBoolean(addUserThread.get())) {
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.contentServiceFragment, new TutorialFragment())
                            .commit();   //พอทำงานเสร็จให้ไปหน้า ShowListMemberFragment
                }else{
                    myAlertDialog.normalDialog("Cannot Upload","Please Try Again");
                }


            }catch (Exception e){
                e.printStackTrace();
            }


        }


    }



    //////////////// onBackPressed
//    public void onBackPressed() {
//        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
//        dialog.setTitle("Exit");
//        dialog.setIcon(R.mipmap.ic_launcher);
//        dialog.setCancelable(true);
//        dialog.setMessage("Do you want to exit?");
//        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int which) {
//                Intent intent = new Intent(Intent.ACTION_MAIN);
//                intent.addCategory(Intent.CATEGORY_HOME);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
//            }
//        });
//
//        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.cancel();
//            }
//        });
//
//        dialog.show();
//    }  /////////////// onBackPressed



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info_login, container, false);
    }



}
