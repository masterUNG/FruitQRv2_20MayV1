package kiky.beam.lilly.th.ac.rmutk.fruitqr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ShowFarmerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_farmer);

        String idString = getIntent().getStringExtra("idUser");

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    //.add(R.id.contentShowMember, new DetailMemberFragment()).commit();
                    .add(R.id.contentShowMember, DetailMemberFragment.detailMemberInstance(idString)).commit();
        }
    }
}
