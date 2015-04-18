package com.polymtl.shoppingsolver;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.polymtl.shoppingsolver.model.Client;
import com.polymtl.shoppingsolver.service.ShoppingSolverIntentService;
import com.polymtl.shoppingsolver.util.ShoppingSolverApplication;

/**
 * Created by Zoe on 15-04-16.
 */
public class NewCountActivity extends Activity {

    private EditText editEmail, editPassword, editName, editPhone, editBalance, editStreet, editCity,
            editCountry, editPostcode;

    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.count);

        editEmail = (EditText)findViewById(R.id.editText_email);
        editPassword = (EditText)findViewById(R.id.editText_password);
        editName = (EditText)findViewById(R.id.editText_name);
        editPhone = (EditText)findViewById(R.id.editText_phone);
        editBalance = (EditText)findViewById(R.id.editText_balance);
        editStreet = (EditText)findViewById(R.id.editText_street);
        editCity = (EditText)findViewById(R.id.editText_city);
        editCountry = (EditText)findViewById(R.id.editText_country);
        editPostcode = (EditText)findViewById(R.id.editText_postcode);

        buttonSave = (Button)findViewById(R.id.btn_saveClientInfo);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editEmail.getText().toString().isEmpty() || editPassword.getText().toString().isEmpty()
                        || editName.getText().toString().isEmpty() || editPhone.getText().toString().isEmpty()
                        || editStreet.getText().toString().isEmpty()
                        || editCity.getText().toString().isEmpty() || editCountry.getText().toString().isEmpty()
                        || editPostcode.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(),
                            "Please enter all your information!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    // Create new count
                    Intent mServerIntent = new Intent(getApplicationContext(),
                            ShoppingSolverIntentService.class);

                    Client client = new Client();
                    client.setEmail(editEmail.getText().toString());
                    client.setPassword(editPassword.getText().toString());
                    client.setName(editName.getText().toString());
                    client.setTelephone(editPhone.getText().toString());
                    //client.setBalance(Double.parseDouble(editBalance.getText().toString()));
                    client.setCity(editCity.getText().toString());
                    client.setStreet(editStreet.getText().toString());
                    client.setCountry(editCountry.getText().toString());
                    client.setPostcode(editPostcode.getText().toString());

                    ShoppingSolverApplication.getInstance().setNewCount(client);

                    mServerIntent.putExtra("command", "createCount");
                    mServerIntent.putExtra("receiver", new CreateCountResultReceiver(new Handler()));

                    // start IntentService
                    Log.i("Login", " start service");
                    getApplicationContext().startService(mServerIntent);
                }
            }
        });


    }


    private class CreateCountResultReceiver extends ResultReceiver {
        /**
         * Create a new ResultReceive to receive results.  Your
         * {@link #onReceiveResult} method will be called from the thread running
         * <var>handler</var> if given, or from an arbitrary thread if null.
         *
         * @param handler
         */
        public CreateCountResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            super.onReceiveResult(resultCode, resultData);

            switch (resultCode) {
                case 1:
                    Log.i("Login", " receive data from service");
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                    break;
                case 0:
                    String err = resultData.getString("err");
                    Toast.makeText(getApplicationContext(),
                            "Create count error!",
                            Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    }

}
