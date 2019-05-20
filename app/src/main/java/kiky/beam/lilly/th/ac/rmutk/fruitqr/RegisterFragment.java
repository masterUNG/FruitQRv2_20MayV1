package kiky.beam.lilly.th.ac.rmutk.fruitqr;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    private  String typeString;
    private  boolean aBoolean = true;



    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Uploand Controller
        Button button = getView().findViewById(R.id.btnUpload);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadValueToServer();
            }
        });


        // RadioGroup
        RadioGroup radioGroup = getView().findViewById(R.id.ragType);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                aBoolean = false;

                switch (checkedId){
//                    case R.id.radAdmin:
//                        typeString = "1";
//                        break;
                    case R.id.radFarmer:
                        typeString = "2";
                        break;
                    case R.id.radProduct:
                        typeString = "3";
                        break;

//                    case R.id.redFarmerandProduct:
//                        typeString = "4";
//                        break;
                }

            }
        });


    }//Main Method


    //คลิกรูป itemUpload
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);

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
        EditText addcontactEditText = getView().findViewById(R.id.editaddcontact);

        String nameShop = namsShopEditText.getText().toString().trim();

        String name = nameEditText.getText().toString().trim(); //แปลงค่าText ให้เป็น String , trim ลบค่าที่เว้นวรรคอัตโนวัติ
        String surname = surnameEditText.getText().toString().trim();
        String address = addressEditText.getText().toString().trim();
        String phone = phoneEditText.getText().toString().trim();
        String user = userEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String addcontact = addcontactEditText.getText().toString().trim();


        MyAlertDialog myAlertDialog = new MyAlertDialog(getActivity()); //การสร้างออปเจ็ค
//        check Spqce  การหาช่องว่าง
        if (nameShop.isEmpty()|| name.isEmpty()|| surname.isEmpty() || address.isEmpty() || phone.isEmpty() || user.isEmpty() || password.isEmpty()|| addcontact.isEmpty()) {//isEmpty ถ้าไม่มีการกรอกเป็น true

//            Have Space
            myAlertDialog.normalDialog("กรอกข้อมูลให้ครบ", "กรุณากรอกข้อมูลให้ครบถ้วน");
        } else if (aBoolean){
//            Non Choose
            myAlertDialog.normalDialog("เลือกสถานะผู้ใช้งาน", "กรุณาเลือกสถานะผู้ใช้งาน");
        }else{

//            Upload to Server
            try {

                Myconstant myconstant = new Myconstant();
                AddUserThread addUserThread = new AddUserThread(getActivity());
                addUserThread.execute(nameShop,name,surname,address,phone,user,password,addcontact,typeString,myconstant.getUrlAddUser());

                String result = addUserThread.get();
                Log.d("27AprilV1", "result ==> " + result);

                if (Boolean.parseBoolean(addUserThread.get())) {
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.contentServiceFragment, new ShowListMemberFragment())
                            .commit();   //พอทำงานเสร็จให้ไปหน้า ShowListMemberFragment
                }else{
                    myAlertDialog.normalDialog("ไม่สามารถบันทึกข้อมูลได้","กรุณาลองอีกครั้ง");
                }


            }catch (Exception e){
                e.printStackTrace();
            }


        }




    }

    //สร้างปุ่ม เมณู
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);


        inflater.inflate(R.menu.menu_register, menu);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

}
