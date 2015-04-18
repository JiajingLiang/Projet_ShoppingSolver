package com.polymtl.shoppingsolver;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.polymtl.shoppingsolver.database.ClientDataSource;
import com.polymtl.shoppingsolver.model.Client;

/**
 * Created by Zoe on 15-04-17.
 */
public class TransactionFragment extends Fragment {

    private View rootView;

    public TransactionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.count, container, false);


        return rootView;
    }
}
