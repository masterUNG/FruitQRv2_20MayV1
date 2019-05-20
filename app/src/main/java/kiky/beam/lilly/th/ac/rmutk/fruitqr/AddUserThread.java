package kiky.beam.lilly.th.ac.rmutk.fruitqr;

import android.content.Context;
import android.os.AsyncTask;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class AddUserThread extends AsyncTask<String, Void, String> {

    private Context context;

    public AddUserThread(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {

        try {

            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = new FormEncodingBuilder()
                    .add("isAdd","true")
                    .add("Name",strings[0])
                    .add("FirstName",strings[1])
                    .add("LastName",strings[2])
                    .add("Address",strings[3])
                    .add("Phone",strings[4])
                    .add("User",strings[5])
                    .add("Password",strings[6])
                    .add("TypeUser",strings[7])
                    .add("AddContact",strings[8])
                    .build();
            Request.Builder builder = new Request.Builder();
            Request request = builder.url(strings[9]).post(requestBody).build();
            Response response = okHttpClient.newCall(request).execute();
            return  response.body().string();


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }



    }
}
