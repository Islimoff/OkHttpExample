package com.sapronov.okhttpexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Credentials;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result=findViewById(R.id.result);
//        OkHttpClient client= new OkHttpClient();
        String credentials = Credentials.basic("Islimoff", "Academyshag2017");
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new BasicAuthInterceptor("Islimoff", "Academyshag2017"))
                .build();
        RequestBody formBody = new FormBody.Builder()
                .add("username", "Islimoff")
                .add("password", "Academyshag2017")
                .build();

        Request request = new Request.Builder()
                .url("https://api.github.com" + "/users")
                .post(formBody)
                .build();

        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            Log.e("ответ", String.valueOf(response.code()));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                if (response.isSuccessful()){
//                    final String strResponse=response.body().string();
//                    MainActivity.this.runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            result.setText(strResponse);
//                        }
//                    });
//                }
//            }
//        });
    }
}
