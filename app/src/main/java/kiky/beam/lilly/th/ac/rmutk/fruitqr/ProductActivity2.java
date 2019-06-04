package kiky.beam.lilly.th.ac.rmutk.fruitqr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class ProductActivity2 extends AppCompatActivity {

    private String idProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product2);

//        Get Value Frome Intent
        idProduct = getIntent().getStringExtra("idProduct");
        Log.d("27AprilV3", "idProduct Activity ==> " + idProduct);

        if(savedInstanceState == null){ //==
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.contentProductFragment2, ShowProductFragment2.showProductInstance2(idProduct)) //ส่งค่าไอดี
                    .commit();

        }
    }
}