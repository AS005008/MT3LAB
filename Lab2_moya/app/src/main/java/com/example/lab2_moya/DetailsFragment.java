package com.example.lab2_moya;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class DetailsFragment extends Fragment {
    public DetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundle = this.getArguments();
        View v = inflater.inflate(R.layout.fragment_details, container, false);
        TextView textView = v.findViewById(R.id.detailsEmail);
        textView.setText(bundle.getString("email"));
        textView = v.findViewById(R.id.detailsName);
        textView.setText(bundle.getString("name"));
        textView = v.findViewById(R.id.detailsBody);
        textView.setText(bundle.getString("body"));

        return v;
    }
}