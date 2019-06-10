package kiky.beam.lilly.th.ac.rmutk.fruitqr;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;


public class AddNameFruitThread extends AsyncTask<String, Void, String>  {

    private Context context;

    public AddNameFruitThread(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {

            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = new FormEncodingBuilder()
                    .add("isAdd","true")
                    .add("NameFruit",strings[0])
                    .add("UnitFruit",strings[1])
                    .build();
            Request.Builder builder = new Request.Builder();
            Request request = builder.url(strings[2]).post(requestBody).build();
            Response response = okHttpClient.newCall(request).execute();
            return  response.body().string();


        } catch (Exception e) {
            Log.d("10JuneV1", "e thread ==> " + e.toString());
            return null;
        }




    }
}
