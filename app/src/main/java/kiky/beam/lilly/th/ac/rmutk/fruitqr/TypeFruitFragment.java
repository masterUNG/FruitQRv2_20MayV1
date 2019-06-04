package kiky.beam.lilly.th.ac.rmutk.fruitqr;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class TypeFruitFragment extends Fragment {

    private Myconstant myconstant = new Myconstant();
    private String nameString, unitString;

    public TypeFruitFragment() {
        // Required empty public constructor
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


//        Save Controller
        saveController();

    }   // Main Method

    private void saveController() {
        Button button = getView().findViewById(R.id.btnSave);
        final MyAlertDialog myAlertDialog = new MyAlertDialog(getActivity());//เอาไว้โวยวายว่ายังไม่ได้ใส่ข้อมูล

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editnameText = getView().findViewById(R.id.txtFarmerLog); //เซฟโดยการกดคลิก
                nameString = editnameText.getText().toString().trim();
                EditText editunitText = getView().findViewById(R.id.txtUnit); //เซฟโดยการกดคลิก
                unitString = editunitText.getText().toString().trim();


                if (nameString.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("โปรดเลือกชื่อผลไม้");
                    builder.setMessage("กรุณาเลือกชื่อผลไม้อีกครั้ง");
                    builder.setPositiveButton("ยืนยัน", new DialogInterface.OnClickListener() {//ปุ่มที่2
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.show();

                } else if (unitString.isEmpty()) {//isEmpty มีการกรอกหรือป่าว
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("โปรดกรอกหน่วยนับของผลไม้");
                    builder.setMessage("กรุณากรอกหน่วยนับของผลไม้");
                    builder.setPositiveButton("ยืนยัน", new DialogInterface.OnClickListener() {//ปุ่มที่2
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.show();

                } else {
                    Log.d("7April", "idRecord ==>> "+ nameString);
                    Log.d("7April", "Name ==>> "+ unitString);


                    comfirmUpload(); //ป็อบอัพ


                }


            } // onClick คลิกที่ปุ่ม
        });


    }

    private void comfirmUpload() { //ป็อบอัพ
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("โปรดยืนยันข้อมูล");
        builder.setMessage("ชื่อผลผลิต = " + nameString + "\n" + "หน่วยนับของผลผลิต = "+ unitString);
        builder.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {//ปุ่มที่1
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }); //
        builder.setPositiveButton("ยืนยัน", new DialogInterface.OnClickListener() {//ปุ่มที่2
            @Override
            public void onClick(DialogInterface dialog, int which) {
                uploadToServer();
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void uploadToServer() {

        try {


            AddNameFruitThread addNameFruitThread = new AddNameFruitThread(getActivity());
            addNameFruitThread.execute(nameString, unitString, myconstant.getUrlAddNameFruit());
            if (Boolean.parseBoolean(addNameFruitThread.get())){
                //สำเร็จ
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentServiceFragment, new AddFramerFragment()).commit();

            }else{
//                //ไม่สำเร็จ
//                Toast.makeText(getActivity(), "บันทึกข้อมูลผลผลิตไม่สำเร็จ กรุณากรอกข้อมูลผลผลิตใหม่อีกครั้ง", Toast.LENGTH_SHORT).show();
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentServiceFragment, new AddFramerFragment()).commit();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_type_fruit, container, false);
    }

}
