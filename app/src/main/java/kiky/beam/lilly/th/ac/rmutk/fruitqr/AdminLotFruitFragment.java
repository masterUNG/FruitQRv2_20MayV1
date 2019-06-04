package kiky.beam.lilly.th.ac.rmutk.fruitqr;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class AdminLotFruitFragment extends Fragment {

    private Myconstant myconstant = new Myconstant();
    private String idRecord, nameFruit, dateString, amountString, unitString, dateoutString, farmerlogString;
    private TextView dateTextView,date2TextView;

    public AdminLotFruitFragment() {
        // Required empty public constructor


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //        Set Date
        setDate();

    }// Main Method

    private void setDate() {


        //ทำให้เป็นวันที่ปัจจุบัน
        dateTextView = getView().findViewById(R.id.txtShowDatee);
        date2TextView = getView().findViewById(R.id.txtShowDatee2);

        final Calendar calendar = Calendar.getInstance();
        final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        dateTextView.setText(dateFormat.format(calendar.getTime()));
        date2TextView.setText(dateFormat.format(calendar.getTime()));

        //สร้างปุ่มปฎิทิน
        Button button = getView().findViewById(R.id.btnSetDatee);
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
        Button button2 = getView().findViewById(R.id.btnSetDatee2);
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin_lot_fruit, container, false);
    }

}
