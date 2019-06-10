package kiky.beam.lilly.th.ac.rmutk.fruitqr;


import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.encoder.QRCode;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import it.sauronsoftware.ftp4j.FTPAbortedException;
import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPDataTransferException;
import it.sauronsoftware.ftp4j.FTPDataTransferListener;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddProductFragment extends Fragment {
    private Myconstant myconstant = new Myconstant();
    private String idRecord, NameRecord, TypeRecord, amountTypeFruitString = "",
            idTypeFruid = "", Name, Detail, Image = "https://www.androidthai.in.th/rmutk/Picture/product.png", Amount, Unit, Date, QRcode, AmountPd, UnitPd;

    private ImageView imageView;
    private Uri uri;
    private boolean picABoolean = true; // ถ้าเค้าไม่มีการเลือกรูปภาพจะไม่เออเร่อ

    private EditText txtQRcode;
    private Button btnCreateQr;
    private ImageView imageeView;

    private File file;
    private Bitmap bitmap;
    private String imageQR;



    private String idDeleteDetailFarmerString;


    public AddProductFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        txtQRcode = getView().findViewById(R.id.txtQRcode);
        btnCreateQr = getView().findViewById(R.id.btnCreateQr);
        imageeView = getView().findViewById(R.id.imageeView);

            btnCreateQr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String text = txtQRcode.getText().toString().trim();


                    if(text != null){

                        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

                        try {

                            BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE, 500, 500);
                            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                            bitmap = barcodeEncoder.createBitmap(bitMatrix);
                            imageeView.setImageBitmap(bitmap);

                            String[] strings = text.split("/");

                            String nameImage = strings[0] + ".png";

                            imageQR = "https://www.androidthai.in.th/rmutk/QRimage/" + nameImage;

                            file = new File(getActivity().getCacheDir(), nameImage);




                        } catch (WriterException e) {
                            e.printStackTrace();
                        }
                    }



                }



            });


//      Create RecyclerView
        createRecyclerView();

//      Picture Controller();
        pritureController();

//      Date Controller
        dateController();

//      Unit Controller
        unitController();



//      Add Product
        addProduct();

//        Qr Controller
        qrController();

//        buttonqr();



    }// Main Method

    public class MyUploadPicture implements FTPDataTransferListener{
        @Override
        public void started() {
            Toast.makeText(getActivity(), "Start Upload", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void transferred(int i) {
            Toast.makeText(getActivity(), "Continue Upload", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void completed() {
            Toast.makeText(getActivity(), "Complete Upload", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void aborted() {

        }

        @Override
        public void failed() {

        }
    }







    private void qrController() { // Random
        TextView textView = getView().findViewById(R.id.txtQRcode);
//        Random random = new Random();
//        int i = random.nextInt(10000);
//        QRcode = "product" + Integer.toString(i);

        //เรียง id + date
        QRcode = "ID" + findIdDetailProduct() + Date;
        textView.setText(QRcode);
    }



    private String findIdDetailProduct() {

        String result = null;
        try {
            //ค้นหาid ให้คิวอาร์โค้ด
            GetAllDataThread getAllDataThread = new GetAllDataThread(getActivity());
            getAllDataThread.execute(myconstant.getUrlGetAllDeatailProduct());

            String jsonString = getAllDataThread.get();
            JSONArray jsonArray = new JSONArray(jsonString);
            JSONObject jsonObject = jsonArray.getJSONObject(0); //ตำแหน่งที่ 0
            result = jsonObject.getString("id");
            int i = Integer.parseInt(result); //เอาค่า id มาเป็นตัวเลข
            i = i + 1; //เพิ่มค่าทีละ1
            result = Integer.toString(i);

            return result;


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    private void unitController() {
        final Spinner spinner = getView().findViewById(R.id.spnUnit);
        final Spinner spinner1 = getView().findViewById(R.id.spnUnitPd);
        final String[] strings = myconstant.getUnits();

        Unit = strings[0];
        UnitPd = strings[0];

        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, strings);
        spinner.setAdapter(stringArrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Unit = strings[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Unit = strings[0];
            }
        });

        spinner1.setAdapter(stringArrayAdapter);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                UnitPd = strings[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { UnitPd = strings[0];

            }
        });
    }



    private void dateController() {
        Button button = getView().findViewById(R.id.btnSetDate);
        final TextView textView = getView().findViewById(R.id.txtDate);
        final Calendar calendar = Calendar.getInstance();
        final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date = dateFormat.format(calendar.getTime()); //เอาเวลาปัจจุบันมาใส่
        textView.setText(Date);

        //ปฎิทิน
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar calendar1 = Calendar.getInstance();
                        calendar1.set(year, month, dayOfMonth);
                        Date = dateFormat.format(calendar1.getTime());
                        textView.setText(Date);

                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
    }

    private void addProduct() { //กดที่ปุ่มAdd
        Button button = getView().findViewById(R.id.btnAddProduct);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences(myconstant.getNameFileSharePreference(), Context.MODE_PRIVATE);
                //ดึงค่ามาจาก MainFragment
                idRecord = sharedPreferences.getString("idLogin", "");
                NameRecord = sharedPreferences.getString("Name", "");
                TypeRecord = sharedPreferences.getString("TypeUser", "");

                EditText nameProductEditText = getView().findViewById(R.id.edtProduct);
                Name = nameProductEditText.getText().toString().trim();
                EditText detailEditText = getView().findViewById(R.id.edtDetailProduct);
                Detail = detailEditText.getText().toString().trim();
                EditText amountEditText = getView().findViewById(R.id.edtAmount);
                Amount = amountEditText.getText().toString().trim();

                EditText amountpdEditText = getView().findViewById(R.id.edtAmountPd);
                AmountPd = amountpdEditText.getText().toString().trim();


                MyAlertDialog myAlertDialog = new MyAlertDialog(getActivity());
                if (idTypeFruid.length() == 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("ยังไม่ได้เลือกผลผลิต");
                    builder.setMessage("กรุณาเลือกผลผลิต");
                    builder.setPositiveButton("ยืนยัน", new DialogInterface.OnClickListener() {//ปุ่มที่2
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.show();

                } else if (Name.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("ไม่มีชื่อผลิตภัณฑ์");
                    builder.setMessage("กรุณาพิมพ์ชื่อผลิตภัณฑ์");
                    builder.setPositiveButton("ยืนยัน", new DialogInterface.OnClickListener() {//ปุ่มที่2
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.show();

                } else if (Detail.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("ไม่มีรายละเอียดผลิตภัณฑ์");
                    builder.setMessage("กรุณาพิมพ์รายละเอียดผลิตภัณฑ์");
                    builder.setPositiveButton("ยืนยัน", new DialogInterface.OnClickListener() {//ปุ่มที่2
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.show();

                } else if (Amount.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("ไม่มีจำนวนผลผลิต");
                    builder.setMessage("กรุณาพิมพ์จำนวน");
                    builder.setPositiveButton("ยืนยัน", new DialogInterface.OnClickListener() {//ปุ่มที่2
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.show();

                } else if (AmountPd.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("ไม่มีจำนวนผลิตภัณฑ์");
                    builder.setMessage("กรุณาพิมพ์จำนวน");
                    builder.setPositiveButton("ยืนยัน", new DialogInterface.OnClickListener() {//ปุ่มที่2
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.show();

                } else if (picABoolean) {
                    Image = myconstant.getUrlProductPic();
                    upDataMySQL(); //ถ้าไม่เลือกรูปภาพ ให้ขึ้นภาพในเมธอดนี้แทน
                } else {

                    //หาตำแหน่ง path ของรูป เพื่ออัพเดต
                    String path = null;
                    String[] strings = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getActivity().getContentResolver().query(uri, strings, null, null, null);
                    if (cursor != null) {
                        cursor.moveToFirst();
                        int index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                        path = cursor.getString(index);

                    } else {
                        path = uri.getPath();
                    }

                    Log.d("11AprilV2", "path ==> " + path);

                    String nameImage = path.substring(path.lastIndexOf("/")); //หาชื่อรูปที่อยู่หลังเครื่องหมาย / Camera/20190411_135758.jpg

                    Image = "https://www.androidthai.in.th/rmutk/Picture" + nameImage;

//                    String QRcodee = path.substring(path.lastIndexOf("/")); //หาชื่อรูปที่อยู่หลังเครื่องหมาย / Camera/20190411_135758.jpg
//
//                    QRcode = "https://www.androidthai.in.th/rmutk/Picture" + QRcode;


                    // ได้ข้อมูลแล้วว แต่ยังไม่ได้โยนขึ้นฐานข้อมูล

                    File file = new File(path); //โหลดรูป pdf อะไรก็ได้
                    FTPClient ftpClient = new FTPClient(); //โหลดไลเบอรรี่
                    String tag = "18ApriV1";

                    StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy
                            .Builder().permitAll().build(); //ขออนุญาติเข้าถึง FTP ในอีกโปรโตคอลนึง
                    StrictMode.setThreadPolicy(threadPolicy);

                    try {
                        ftpClient.connect("ftp.androidthai.in.th", 21);
                        ftpClient.login("rmutk@androidthai.in.th", "Abc12345");
                        ftpClient.setType(FTPClient.TYPE_BINARY);//ย่อขนาดไลเบอรรี่
                        ftpClient.changeDirectory("Picture");
                        ftpClient.upload(file, new UploadListener());

                        Log.d("18AprilV2", "Image ==>> " + Image);

                        //เพิ่มข้อมูลขึ้นฐานข้อมูล
                        upDataMySQL();


                    } catch (Exception e) {
                        Log.d(tag, "e ==> " + e.toString());

                        try {
                            ftpClient.disconnect(true);
                        } catch (Exception e1) {
                            Log.d(tag, "e1 ==> " + e1.toString());
                        }

                    }


                } //if


//                Upload Image QRimage
                FTPClient ftpClient = new FTPClient();
                try {

                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
                    byte[] bytes = byteArrayOutputStream.toByteArray();

                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    fileOutputStream.write(bytes);
                    fileOutputStream.flush();
                    fileOutputStream.close();

                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy
                            .Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);


                    ftpClient.connect("ftp.androidthai.in.th", 21);
                    ftpClient.login("rmutk@androidthai.in.th", "Abc12345");
                    ftpClient.setType(FTPClient.TYPE_BINARY);
                    ftpClient.changeDirectory("QRimage");
                    ftpClient.upload(file, new MyUploadPicture());

                } catch (Exception e) {
                    e.printStackTrace();
                    try {
                        ftpClient.disconnect(true);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }



            }
        });


    }

    private void upDataMySQL() {

        try {

//            Edit Amount on TypeFruit
            int currentAmountInt = Integer.parseInt(amountTypeFruitString) - Integer.parseInt(Amount);
            String amountCurrentString = Integer.toString(currentAmountInt);

            EditDataOneColumnThread editDataOneColumnThread = new EditDataOneColumnThread(getActivity());
            editDataOneColumnThread.execute("id", idTypeFruid, "Amount", amountCurrentString,
                    myconstant.getUrlEditAmountWhereId());




            //อัพโหลด
            AddDetailProductThread addDetailProductThread = new AddDetailProductThread(getActivity());
            addDetailProductThread.execute(idRecord, NameRecord, TypeRecord, idTypeFruid, Name,
                    Detail, Image, Amount, Unit, Date, QRcode, AmountPd, UnitPd, imageQR, myconstant.getUrlAddDetailProduct());
            String result = addDetailProductThread.get();

            if (Boolean.parseBoolean(result)) {
                goToProductList();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goToProductList() {
        Log.d("18AprilV1", "goToProductList");
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contentServiceFragment, new ShowListProductFragment2())
                .commit();

    }

    public class UploadListener implements FTPDataTransferListener { //สร้างคลาสซ้อนคลาส

        @Override
        public void started() {
            Toast.makeText(getActivity(), "Start Uplaod", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void transferred(int i) {
            Toast.makeText(getActivity(), "Continue Uplaod", Toast.LENGTH_SHORT).show(); //กำลังอัพโหลด
        }

        @Override
        public void completed() {
            Toast.makeText(getActivity(), "Complete Uplaod", Toast.LENGTH_SHORT).show(); //อัพโหลดเสร็จแล้ว
//            AddProductFragment addProductFragment = new AddProductFragment();
//            addProductFragment.goToProductList();

        }

        @Override
        public void aborted() {
            Toast.makeText(getActivity(), "Aborted Upload", Toast.LENGTH_SHORT).show(); //มีอะไรมาขัดจังหวะ

        }

        @Override
        public void failed() {
            Toast.makeText(getActivity(), "False Uplaod", Toast.LENGTH_SHORT).show(); //อัพโหลดไม่สำเร็จ

        }
    }


    private void pritureController() {
        imageView = getView().findViewById(R.id.imvProduct);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK); //ขออนุญาติในการใช้โปรแกรมอื่น ในการดึงรูป
                intent.setType("image/*");//ทำการเลือกให้ว่าเลือกโปรแกรมไหนในการเลือกภาพ
                startActivityForResult(Intent.createChooser(intent, "กรุณาเลือกแอพ ดูรูปภาพ"), 1); //จำนวน1รูป


            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == getActivity().RESULT_OK) {//ถ้าเลือกรูปได้
            uri = data.getData();
            picABoolean = false;

            try {
                Bitmap bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(uri));
                Bitmap bitmap1 = Bitmap.createScaledBitmap(bitmap, 800, 600, false); //ย่อขนาดรูป
                imageView.setImageBitmap(bitmap1);

            } catch (Exception e) {
                e.printStackTrace();

            }
        }

    }

    private void createRecyclerView() {

        final ArrayList<String> nameFruitStringArrayList = new ArrayList<>();
        final ArrayList<String> amountStringArrayList = new ArrayList<>();
        ArrayList<String> unitStringArrayList = new ArrayList<>();
        final ArrayList<String> idStringArrayList = new ArrayList<>();



        try {

            RecyclerView recyclerView = getView().findViewById(R.id.recyclerFramer);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),
                    LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(linearLayoutManager);

            GetAllDataThread getAllDataThread = new GetAllDataThread(getActivity());
            getAllDataThread.execute(myconstant.getUrlGetTypeFruit());

            JSONArray jsonArray = new JSONArray(getAllDataThread.get());
            for (int i = 0; i < jsonArray.length(); i += 1) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                nameFruitStringArrayList.add(jsonObject.getString("NameFruit"));
                amountStringArrayList.add(jsonObject.getString("Amount"));
                unitStringArrayList.add(jsonObject.getString("Unit"));
                idStringArrayList.add(jsonObject.getString("id"));
            }

            TotalFruitAdapter totalFruitAdapter = new TotalFruitAdapter(getActivity(), nameFruitStringArrayList,
                    amountStringArrayList, unitStringArrayList, new OnClickItem() {
                @Override
                public void onClickitem(View view, int position) {
                    amountTypeFruitString = amountStringArrayList.get(position);
                    confirmFruit(nameFruitStringArrayList.get(position), idStringArrayList.get(position));
                }
            });

            recyclerView.setAdapter(totalFruitAdapter);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void confirmFruit(final String nameFruit, final String idFruit) {
        final TextView textView = getView().findViewById(R.id.textChooseFruit);


        //ป็อบอัพ
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("ยืนยันการเลือกผลผลิต");
        builder.setMessage("คุณต้องการเลือก " + nameFruit);
        builder.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                idTypeFruid = idFruit;
                textView.setText("ผลผลิตที่เลือก " + nameFruit);
                dialog.dismiss();
            }
        });
        builder.show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_product, container, false);
    }

}
