package com.sapronov.okhttpexample;

import androidx.fragment.app.Fragment;

public class AuthActivity extends BaseActivity {


    @Override
    public Fragment loadFragment() {
        return new AuthFragment();
    }
}
