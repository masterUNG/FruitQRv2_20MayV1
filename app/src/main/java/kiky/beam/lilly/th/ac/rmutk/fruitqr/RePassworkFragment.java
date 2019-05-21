package kiky.beam.lilly.th.ac.rmutk.fruitqr;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class RePassworkFragment extends Fragment {


    public RePassworkFragment() {
        // Required empty public constructor
    }

    public static RePassworkFragment rePassworkInstance(String user) {
        RePassworkFragment rePassworkFragment = new RePassworkFragment();
        Bundle bundle = new Bundle();
        bundle.putString("User", user);
        rePassworkFragment.setArguments(bundle);
        return rePassworkFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


//        Save Controller
        saveController();


    }

    private void saveController() {
        Button button = getView().findViewById(R.id.btnSave);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText passwordEditText = getView().findViewById(R.id.edtPassword);
                EditText rePasswordEditText = getView().findViewById(R.id.edtRePassword);

                String password = passwordEditText.getText().toString().trim();
                String rePassword = rePasswordEditText.getText().toString().trim();

                MyAlertDialog myAlertDialog = new MyAlertDialog(getActivity());

                if (password.isEmpty() || rePassword.isEmpty()) {
                    myAlertDialog.normalDialog("Have Space", "ห้ามมีช่องว่าง");
                } else if (!(password.equals(rePassword))) {
                    myAlertDialog.normalDialog("Password Not Math", "Password ไม่ตรงกัน คะ");
                } else {

                    try {

                        String user = getArguments().getString("User");
                        Myconstant myconstant = new Myconstant();

                        GetDataWhereTwoColunmThread getDataWhereTwoColunmThread = new GetDataWhereTwoColunmThread(getActivity());
                        getDataWhereTwoColunmThread.execute("User", user, "Password", password, myconstant.getUrlEditPasswordWhereUser());
                        String response = getDataWhereTwoColunmThread.get();

                        if (Boolean.parseBoolean(response)) {
                            getActivity().getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.contentMainFragment, new MainFragment()).commit();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }


            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_re_passwork, container, false);
    }

}