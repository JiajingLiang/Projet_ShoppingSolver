package com.polymtl.shoppingsolver.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.polymtl.shoppingsolver.R;
import com.polymtl.shoppingsolver.model.BriefTransaction;
import com.polymtl.shoppingsolver.util.ShoppingSolverApplication;

import java.util.ArrayList;


/**
 * Created by Zoe on 15-04-06.
 */
public class TransactionFragment extends Fragment {
    private View rootView;
    private ListView receptionsListView;
    private TextView textTransaction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_receptions, container, false);

        switch (getArguments().getString("type")) {
            case "currentTransaction":
                textTransaction = (TextView) rootView.findViewById(R.id.tv_transaction);
                if (ShoppingSolverApplication.getInstance().getTheLastTransaction() != null) {
                    textTransaction.setText(ShoppingSolverApplication.getInstance().getTheLastTransaction().toString());
                }

                break;
            case "recentTransactions":
                receptionsListView = (ListView) rootView.findViewById(R.id.receptionList);

                ArrayList<String> values = new ArrayList<>();

                ArrayList<BriefTransaction> list = ShoppingSolverApplication.getInstance().getBriefTransactions();
                for (BriefTransaction item: list) {
                    values.add(item.toString());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                        android.R.layout.simple_list_item_1, android.R.id.text1, values);

                receptionsListView.setAdapter(adapter);
                break;
            default:
                break;
        }

        return rootView;
    }
}
