package com.sapronov.okhttpexample;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AuthFragment extends Fragment {

    private EditText editLogin;
    private EditText editPassword;
    private final String BASIC_URL = "https://api.github.com/";
    private final String USERS = "users/";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);
        editLogin = view.findViewById(R.id.edit_login);
        editPassword = view.findViewById(R.id.edit_password);
        Button sendButton=view.findViewById(R.id.button);
        sendButton.setOnClickListener(this::sendAuth);
        return view;
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
                   getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(getContext(), ResultActivity.class);
                            intent.putExtra("response",strResponse);
                            startActivity(intent);
                            Toast.makeText(getActivity(), strResponse, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}