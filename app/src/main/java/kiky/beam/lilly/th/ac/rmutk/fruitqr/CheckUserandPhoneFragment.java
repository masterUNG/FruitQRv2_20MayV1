package kiky.beam.lilly.th.ac.rmutk.fruitqr;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class CheckUserandPhoneFragment extends Fragment {


    public CheckUserandPhoneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


//        Check Controller
        checkController();

    }

    private void checkController() {
        Button button = getView().findViewById(R.id.btnCheck);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText nameEditText = getView().findViewById(R.id.edtName);
                EditText userEditText = getView().findViewById(R.id.edtUser);
                EditText phoneEditText = getView().findViewById(R.id.edtPhone);

                String name = nameEditText.getText().toString();
                String user = userEditText.getText().toString();
                String phone = phoneEditText.getText().toString();

                MyAlertDialog myAlertDialog = new MyAlertDialog(getActivity());
                Myconstant myconstant = new Myconstant();

                try {

                    GetDataWhereOneColumn getDataWhereOneColumn = new GetDataWhereOneColumn(getActivity());
                    getDataWhereOneColumn.execute("User", user, myconstant.getUrlGetUserWhereUser());
                    String response = getDataWhereOneColumn.get();
                    Log.d("21MayV1", "response ==> " + response);


                    if (response.equals("null")) {
                        myAlertDialog.normalDialog("ชื่อผู้ใช้งานไม่ถูกต้อง", "กรุณากรอกชื่อผู้ใช้งานใหม่อีกครั้ง");
                    } else {
                        JSONArray jsonArray = new JSONArray(response);
                        JSONObject jsonObject = jsonArray.getJSONObject(0);

                        if (!(name.equals(jsonObject.getString("Name")))) {
                            myAlertDialog.normalDialog("ชื่อสวน/ร้านไม่ถูกต้อง", "กรุณากรอกชื่อสวน/ร้านใหม่อีกครั้ง");
                        } else if (!(phone.equals(jsonObject.getString("Phone")))) {
                            myAlertDialog.normalDialog("เบอร์โทรศัพท์ไม่ถูกต้อง", "กรุณากรอกเบอร์โทรศัพท์ใหม่อีกครั้ง");
                        } else {
                            getActivity().getSupportFragmentManager()
                                    .beginTransaction().replace(R.id.contentMainFragment, RePassworkFragment.rePassworkInstance(user))
                                    .commit();
                        }

                    }


                } catch (Exception e) {
                    Log.d("21MayV1", "e ==> " + e.toString());
                }


            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_check_userand_phone, container, false);
    }

}