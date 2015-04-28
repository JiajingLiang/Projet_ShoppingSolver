package com.polymtl.shoppingsolver.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.polymtl.shoppingsolver.R;
import com.polymtl.shoppingsolver.database.HabitDataSource;
import com.polymtl.shoppingsolver.model.HabitRecord;
import com.polymtl.shoppingsolver.util.ShoppingSolverApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zoe on 15-04-06.
 */
public class HabitFragment extends Fragment {

    private HabitDataSource dataSource;
    private View rootView;
    private ListView habitListView;
    ArrayAdapter<String> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_habit, container, false);

        habitListView = (ListView) rootView.findViewById(R.id.consumption_habit);

        List<String> values = new ArrayList<>();

        dataSource = new HabitDataSource(getActivity());

        dataSource.open();



        List<HabitRecord> records = dataSource.getNecessaryRecords(ShoppingSolverApplication.getInstance().getCurrentClient().getClientId());
        for (HabitRecord record : records) {
            values.add(record.toString());
        }
        adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, values);

        habitListView.setAdapter(adapter);


        return rootView;
    }

    @Override
    public void onResume() {
        dataSource.open();
        super.onResume();

    }

    @Override
    public void onPause() {
        dataSource.close();
        super.onPause();
    }

}
