package kiky.beam.lilly.th.ac.rmutk.fruitqr;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class TutorialFragment extends Fragment {


    public TutorialFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        buttonnClick();
    }

    private void buttonnClick() {
        Button button1 = getView().findViewById(R.id.iconhome);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentServiceFragment,new TutorialFragment()).commit();
            }
        });

        Button button2 = getView().findViewById(R.id.iconqrcode);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), QRActivity.class);
                intent.putExtra("Login", false);
                startActivity(intent);
            }
        });



        Button button3 = getView().findViewById(R.id.iconaddframer);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentServiceFragment,new AddFramerFragment()).commit();
            }
        });


        Button button4 = getView().findViewById(R.id.iconallfarmer);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentServiceFragment,new TotalFarmerListFragment()).commit();
            }
        });

        Button button5 = getView().findViewById(R.id.iconframer);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentServiceFragment,new ShowListFramerFragment()).commit();
            }
        });

        Button button6 = getView().findViewById(R.id.iconproduct);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentServiceFragment,new ShowListProductFragment()).commit();
            }
        });


        Button button7 = getView().findViewById(R.id.iconaddregister);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentServiceFragment,new RegisterFragment()).commit();
            }
        });

        Button button8 = getView().findViewById(R.id.iconregister);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentServiceFragment,new ShowListMemberFragment()).commit();
            }
        });

        Button button9 = getView().findViewById(R.id.iconmanual);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentServiceFragment,new ManualFragment()).commit();
            }
        });

        Button button10 = getView().findViewById(R.id.iconaboutme);
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentServiceFragment,new AboutMeFragment()).commit();
            }
        });

        Button button11 = getView().findViewById(R.id.iconexit);
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("แจ้งเตือน..!");
                builder.setMessage("คุณต้องการออกจากแอพพลิเคชันใช่หรือไม่");
                builder.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {//ปุ่มที่1
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }); //
                builder.setPositiveButton("ยืนยัน", new DialogInterface.OnClickListener() {//ปุ่มที่2
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getActivity().finish();
                    }
                });
                builder.show();
            }
        });
    }



        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tutorial, container, false);
    }

}
