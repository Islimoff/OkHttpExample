package com.sapronov.okhttpexample;

import androidx.fragment.app.Fragment;

public class ResultActivity extends BaseActivity {
    @Override
    public Fragment loadFragment() {
        return new ResultFragment().of(getIntent().getStringExtra("response"));
    }
}
