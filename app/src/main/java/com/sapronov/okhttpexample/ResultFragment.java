package com.sapronov.okhttpexample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ResultFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.result_activity, container, false);
        TextView textView = view.findViewById(R.id.response);
        if (getArguments() != null) {
            textView.setText(getArguments().getString("response"));
        }
        return view;
    }

    public static ResultFragment of(String response) {
        ResultFragment resultFragment = new ResultFragment();
        Bundle bundle = new Bundle();
        bundle.putString("response", response);
        resultFragment.setArguments(bundle);
        return resultFragment;
    }
}
