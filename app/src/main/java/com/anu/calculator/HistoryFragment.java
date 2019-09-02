package com.anu.calculator;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Objects;

public class HistoryFragment extends Fragment {

    private static final String TAG = "HISTORY_TAB";

    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.history_fragment, container, false);

        return rootView;
    }

    public void addHistory(String historyText){

        final TextView history_area = getActivity().findViewById(R.id.history_text);
        history_area.append("\n"+historyText);
    }
}