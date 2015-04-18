package com.polymtl.shoppingsolver;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.polymtl.shoppingsolver.database.ClientDataSource;
import com.polymtl.shoppingsolver.model.Client;
import com.polymtl.shoppingsolver.service.ShoppingSolverIntentService;
import com.polymtl.shoppingsolver.util.ShoppingSolverApplication;

public class CountFragment extends Fragment {

	private View rootView;

    private EditText editEmail, editPassword, editName, editPhone, editBalance, editStreet, editCity,
    editCountry, editPostcode;

    private Button buttonSave;

    public CountFragment() {
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.count, container, false);

        editEmail = (EditText)rootView.findViewById(R.id.editText_email);
        editPassword = (EditText)rootView.findViewById(R.id.editText_password);
        editName = (EditText)rootView.findViewById(R.id.editText_name);
        editPhone = (EditText)rootView.findViewById(R.id.editText_phone);
        editBalance = (EditText)rootView.findViewById(R.id.editText_balance);
        editStreet = (EditText)rootView.findViewById(R.id.editText_street);
        editCity = (EditText)rootView.findViewById(R.id.editText_city);
        editCountry = (EditText)rootView.findViewById(R.id.editText_country);
        editPostcode = (EditText)rootView.findViewById(R.id.editText_postcode);

        buttonSave = (Button)rootView.findViewById(R.id.btn_saveClientInfo);
        buttonSave.setVisibility(View.INVISIBLE);

        ClientDataSource clientDataSource = new ClientDataSource(getActivity().getApplicationContext());
        clientDataSource.open();
        Client client = clientDataSource.getClient();
        clientDataSource.close();

        if (client != null) {

            editEmail.setText(client.getEmail());
            editEmail.setEnabled(false);
            editPassword.setText(client.getPassword());
            editPassword.setEnabled(false);
            editName.setText(client.getName());
            editName.setEnabled(false);
            editPhone.setText(client.getTelephone());
            editPhone.setEnabled(false);
            editBalance.setText(String.valueOf(client.getBalance()));
            editBalance.setEnabled(false);
            editStreet.setText(client.getStreet());
            editStreet.setEnabled(false);
            editCity.setText(client.getCity());
            editCity.setEnabled(false);
            editCountry.setText(client.getCountry());
            editCountry.setEnabled(false);
            editPostcode.setText(client.getPostcode());
            editPostcode.setEnabled(false);

            buttonSave.setEnabled(false);
        }

        return rootView;
    }


}
