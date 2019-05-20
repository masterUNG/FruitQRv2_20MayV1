package kiky.beam.lilly.th.ac.rmutk.fruitqr;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class TutorialProductFragment extends Fragment {


    public TutorialProductFragment() {
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
                        .replace(R.id.contentServiceFragment,new TutorialFramerFragment()).commit();
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

        Button button5 = getView().findViewById(R.id.iconproduct);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentServiceFragment,new ShowListProductFragment()).commit();
            }
        });

        Button button6 = getView().findViewById(R.id.iconaddproduct);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentServiceFragment,new AddProductFragment()).commit();
            }
        });

        Button button8 = getView().findViewById(R.id.iconregister);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentServiceFragment,new InfoLoginFragment()).commit();

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
                getActivity().finish();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tutorial_product, container, false);
    }

}
