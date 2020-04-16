package com.sapronov.okhttpexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

    private EditText editLogin;
    private EditText editPassword;
    private final String BASIC_URL = "https://api.github.com/";
    private final String USERS = "users/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editLogin = findViewById(R.id.edit_login);
        editPassword = findViewById(R.id.edit_password);
    }

    public void sendAuth(View view) {
        OkHttpClient client = new OkHttpClient();
        String login = editLogin.getText().toString();
        String password = editPassword.getText().toString();
        Request request = new Request.Builder()
                .url(BASIC_URL + USERS + login)
                .addHeader("Authorization", Credentials.basic(login, password))
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String strResponse = response.body().string();
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, strResponse, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
