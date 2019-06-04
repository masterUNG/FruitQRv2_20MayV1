package kiky.beam.lilly.th.ac.rmutk.fruitqr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class FarmerActivity extends AppCompatActivity {

    private String idFarmer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer);

//        Get Value Frome Intent
        idFarmer = getIntent().getStringExtra("idFarmer");
        Log.d("27AprilV3", "idFarmer Activity ==> " + idFarmer);

        if(savedInstanceState == null){ //==
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.contentFarmerFragment, ShowFarmerFragment.showFarmerInstance(idFarmer)) //ส่งค่าไอดี
                    .commit();

        }
    }
}
