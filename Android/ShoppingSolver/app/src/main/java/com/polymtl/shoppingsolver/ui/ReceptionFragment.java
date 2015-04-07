package com.polymtl.shoppingsolver.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.polymtl.shoppingsolver.R;
import com.polymtl.shoppingsolver.database.DBHelper;
import com.polymtl.shoppingsolver.model.ShoppingRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zoe on 15-04-06.
 */
public class ReceptionFragment extends Fragment {
    private View rootView;
    private ListView receptionsListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_payment, container, false);

        receptionsListView = (ListView) rootView.findViewById(R.id.consumption_habit);

        List<String> values = new ArrayList<>();

        DBHelper dbHelper = new DBHelper(getActivity());
        /*List<ShoppingRecord> records = dbHelper.getNecessaryRecords();
        for (ShoppingRecord record : records) {
            values.add(record.toString());
        }*/
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, values);

        receptionsListView.setAdapter(adapter);

        receptionsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO: open one reception fragment
            }
        });

        return rootView;
    }
}
