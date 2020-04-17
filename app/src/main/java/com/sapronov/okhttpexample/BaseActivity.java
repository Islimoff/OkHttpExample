package com.sapronov.okhttpexample;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle saved){
        super.onCreate(saved);
        setContentView(R.layout.host_frg);
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.findFragmentById(R.id.content) == null) {
            fragmentManager.beginTransaction()
                    .add(R.id.content, loadFragment(), loadFragment().toString())
                    .commit();
        }
    }
    public abstract Fragment loadFragment();
}
