package kiky.beam.lilly.th.ac.rmutk.fruitqr;


import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.animation.Positioning;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddFramerFragment extends Fragment {

    private Myconstant myconstant = new Myconstant();
    private String idRecord, nameFruit, nameFarmer, dateString, amountString, unitString, dateoutString, farmerlogString;
    private TextView dateTextView,date2TextView;
    private boolean nameFruitABoolean = true;
    private boolean FarmerABoolean = true;
    private String[] strings;

    public AddFramerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


//        Create NameFruit
        createNameFruit();


//        Set Date
        setDate();

//        Show View
        showView();


//        Save Controller
        saveController();

        buttonfruit();

        //        Create NameFarmer
        createNameFarmer();

    }   // Main Method

    private void buttonfruit() {
        Button button = getView().findViewById(R.id.btnSpinner);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentServiceFragment, new TypeFruitFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }


    private void saveController() {
        Button button = getView().findViewById(R.id.btnSave);
        final MyAlertDialog myAlertDialog = new MyAlertDialog(getActivity());//เอาไว้โวยวายว่ายังไม่ได้ใส่ข้อมูล

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = getView().findViewById(R.id.edtAmount); //เซฟโดยการกดคลิก
                amountString = editText.getText().toString().trim();
                dateString = dateTextView.getText().toString();

                EditText editText1 = getView().findViewById(R.id.txtFarmerLog);
                dateoutString = date2TextView.getText().toString();
                farmerlogString = editText1.getText().toString().trim();

                if (nameFruitABoolean) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("โปรดเลือกชื่อผลผลิต");
                    builder.setMessage("กรุณาเลือกชื่อผลผลิตอีกครั้ง");
                    builder.setPositiveButton("ยืนยัน", new DialogInterface.OnClickListener() {//ปุ่มที่2
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.show();

//                    myAlertDialog.normalDialog("โปรดเลือกชื่อผลผลิต", "กรุณาเลือกชื่อผลผลิตอีกครั้ง");     //ยังไม่ได้เลือกชื่อผลไม้ผล //กรุณาใส่ชื่อผลไม้

                } else if (amountString.isEmpty()) {//isEmpty มีการกรอกหรือป่าว
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("โปรดกรอกราคาผลผลิต");
                    builder.setMessage("กรุณากรอกราคาผลผลิตอีกครั้ง");
                    builder.setPositiveButton("ยืนยัน", new DialogInterface.OnClickListener() {//ปุ่มที่2
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.show();

                } else if (farmerlogString.isEmpty()) {//isEmpty มีการกรอกหรือป่าว
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("โปรดกรอกรุ่น/รอบผลผลิต");
                    builder.setMessage("กรุณากรอกรุ่น/รอบผลผลิต");
                    builder.setPositiveButton("ยืนยัน", new DialogInterface.OnClickListener() {//ปุ่มที่2
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.show();

//                    myAlertDialog.normalDialog("โปรดกรอกราคาผลผลิต", "กรุณากรอกราคาผลผลิตอีกครั้ง");
                } else {
                    Log.d("7April", "idRecord ==>> "+ idRecord);
                    Log.d("7April", "Name ==>> "+ nameFruit);
                    Log.d("7April", "Amount ==>> "+ amountString);
                    Log.d("7April", "Unit ==>> "+ unitString);
                    Log.d("7April", "Date ==>> "+ dateString);

                    Log.d("7April", "Dateout ==>> "+ dateoutString);
                    Log.d("7April", "farmerlog ==>> "+ farmerlogString);

                    comfirmUpload(); //ป็อบอัพ
                }


            } // onClick คลิกที่ปุ่ม
        });
    }

    private void comfirmUpload() { //ป็อบอัพ
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("โปรดยืนยันข้อมูล");
        builder.setMessage("ชื่อผลผลิต = " + nameFruit + "\n" + "ราคา = " + amountString + " " + unitString + "\n" + "วันที่เก็บเกี่ยว = "+ dateString +
                "\n" + "รุ่น/รอบผลผลิต = "+ farmerlogString); //+ "วันที่ส่งผลผลิต = "+ dateoutString +  "\n"
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

            GetDataWhereOneColumn getDataWhereOneColumn = new GetDataWhereOneColumn(getActivity());
            getDataWhereOneColumn.execute("NameFruit", nameFruit.trim(), myconstant.getUrlGetTypeTruitWhereNameFruid());
            String response = getDataWhereOneColumn.get();
            Log.d("20MayV2", "response ==> " + response);

            JSONArray jsonArray = new JSONArray(response);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            String amountString2 = jsonObject.getString("Amount");

            int amountInt = Integer.parseInt(amountString2) + Integer.parseInt(amountString);
            String amountCurrent = Integer.toString(amountInt);

            EditDataOneColumnThread editDataOneColumnThread = new EditDataOneColumnThread(getActivity());
            editDataOneColumnThread.execute("NameFruit", nameFruit, "Amount", amountCurrent, myconstant.getUrlEditAmountWhereNameFruit());
            Log.d("20MayV3", "Result ==> " + getDataWhereOneColumn.get());



            AddDetailFramerThread addDetailFramerThread = new AddDetailFramerThread(getActivity());
            addDetailFramerThread.execute(idRecord, nameFruit, amountString, unitString,
                    dateString,dateoutString,farmerlogString, myconstant.getUrlAddDetailFramer());
            if (Boolean.parseBoolean(addDetailFramerThread.get())){
                //สำเร็จ
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentServiceFragment, new ShowListCenterFragment()).commit();

            }else{
                //ไม่สำเร็จ
                Toast.makeText(getActivity(), "บันทึกข้อมูลผลผลิตไม่สำเร็จ กรุณากรอกข้อมูลผลผลิตใหม่อีกครั้ง", Toast.LENGTH_SHORT).show();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void setDate() {
        Spinner spinner = getView().findViewById(R.id.spinnerUnit);
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, myconstant.getUnits());
        spinner.setAdapter(stringArrayAdapter);

        final String[] unitStrings = myconstant.getUnits();
        unitString = unitStrings[0]; //ถ้าไม่เลือกตั้งค่าให้ กิโลกรัมเป็นอันแรก

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                unitString = unitStrings[position]; //ถ้าเลือกกลับไปกลับมาให้ดูที่ position
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { //ถ้าไม่เลือกอะไรเลย
                unitString = unitStrings[0]; //ถ้าไม่เลือกตั้งค่าให้ กิโลกรัมเป็นอันแรก
            }
        });

        //ทำให้เป็นวันที่ปัจจุบัน
        dateTextView = getView().findViewById(R.id.txtShowDate);
        date2TextView = getView().findViewById(R.id.txtShowDate2);

        final Calendar calendar = Calendar.getInstance();
        final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        dateTextView.setText(dateFormat.format(calendar.getTime()));
        date2TextView.setText(dateFormat.format(calendar.getTime()));

        //สร้างปุ่มปฎิทิน
        Button button = getView().findViewById(R.id.btnSetDate);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        //ตั้งค่าในปฎิทิน ในการกดตกลง
                        Calendar calendar1 = Calendar.getInstance();
                        calendar1.set(year, month, dayOfMonth);
                        dateTextView.setText(dateFormat.format(calendar1.getTime()));


                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));//ตั้งการค่าตั้งต้น ทำการสั่งวันที่ปัจจุบันให้ตรงกับปุ่ม
                datePickerDialog.show();
            }//DAY_OF_MONTH 30วัน
        });

        //สร้างปุ่มปฎิทิน
        Button button2 = getView().findViewById(R.id.btnSetDate2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        //ตั้งค่าในปฎิทิน ในการกดตกลง
                        Calendar calendar1 = Calendar.getInstance();
                        calendar1.set(year, month, dayOfMonth);
                        date2TextView.setText(dateFormat.format(calendar1.getTime()));


                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));//ตั้งการค่าตั้งต้น ทำการสั่งวันที่ปัจจุบันให้ตรงกับปุ่ม
                datePickerDialog.show();
            }//DAY_OF_MONTH 30วัน
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

            TextView nameTextView = getView().findViewById(R.id.txtName);
            nameTextView.setText(jsonObject.getString("Name"));

            TextView addressTextView = getView().findViewById(R.id.txtAddress);
            addressTextView.setText(jsonObject.getString("Address"));

            TextView phoneTextView = getView().findViewById(R.id.txtPhone);
            phoneTextView.setText(jsonObject.getString("Phone"));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createNameFruit() {

        try {

            GetAllDataThread getAllDataThread = new GetAllDataThread(getActivity());
            Myconstant myconstant = new Myconstant();
            getAllDataThread.execute(myconstant.getUrlGetTypeFruit());

            String result = getAllDataThread.get();
//            Log.d("10JuneV1", result);
            JSONArray jsonArray = new JSONArray(result);
            strings = new String[jsonArray.length()];

            for (int i = 0; i < jsonArray.length(); i += 1) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                strings[i] = jsonObject.getString("NameFruit");
//                Log.d("10JuneV1", strings[i]);
            }



            createSpinner();


        } catch (Exception e) {
            Log.d("10JuneV1", "e ==> " + e.toString());
        }


//        final String[] strings = myconstant.getFavoriteFruits();


    }

    private void createSpinner() {
        Log.d("10JuneV1", "strings ==> " + strings.toString());
        Spinner spinner = getView().findViewById(R.id.spinnerFruit);
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, strings);
        spinner.setAdapter(stringArrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {//เลือก
                nameFruit = strings[position];
                checkStatusFruit(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {//ถ้าไม่เลือก
                nameFruit = strings[0];

            }
        });
    }

    private void createNameFarmer() {





        final String[] strings = myconstant.getFarmer();

        Spinner spinner = getView().findViewById(R.id.spinnername);
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, myconstant.getFarmer());
        spinner.setAdapter(stringArrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {//เลือก
                nameFarmer = strings[position];
                checkStatusfarmer(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {//ถ้าไม่เลือก
                nameFarmer = strings[0];

            }
        });

    }

    //เช็คค่า
    private void checkStatusFruit(int position) {
        if (position == 0) {
            nameFruitABoolean = true;
        } else {
            nameFruitABoolean = false;
        }
    }

    //เช็คค่า
    private void checkStatusfarmer(int position) {
        if (position == 0) {
            FarmerABoolean = true;
        } else {
            FarmerABoolean = false;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_framer, container, false);
    }

}