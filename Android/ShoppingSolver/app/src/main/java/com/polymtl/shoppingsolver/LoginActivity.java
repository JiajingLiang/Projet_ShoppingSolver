package com.polymtl.shoppingsolver;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import com.polymtl.shoppingsolver.service.ShoppingSolverIntentService;

public class LoginActivity extends Activity {
	private EditText login;
	private EditText password;
    private CheckBox checkBoxSave;
    private boolean checked;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		login = (EditText)this.findViewById(R.id.editText_login);
		password = (EditText)this.findViewById(R.id.editText_password);
		
		Button connexionButton = (Button)this.findViewById(R.id.button_Connextion);
        checkBoxSave = (CheckBox) this.findViewById(R.id.checkbox_save);
        checkBoxSave.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                checked = checkBoxSave.isChecked();

            }
        });
		connexionButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {

                login.setText("jiajing.liang@polymtl.ca");
                password.setText("jjliang");
				String strLogin = login.getText().toString();
				String strPassword = password.getText().toString();
				if(strLogin.length()==0||strPassword.length()==0){
					Toast.makeText(getApplicationContext(), "Please enter login and password", Toast.LENGTH_SHORT).show();
				}else{

                    // create new Intent to invoke ShoppingSolverIntentService
                    Intent mServerIntent = new Intent(LoginActivity.this, ShoppingSolverIntentService.class);
                    mServerIntent.putExtra("command", "login");
                    mServerIntent.putExtra("checked", checked);
                    mServerIntent.putExtra("email", strLogin);
                    mServerIntent.putExtra("password", strPassword);
                    mServerIntent.putExtra("receiver", new LoginResultReceiver(new Handler()));

                    // start IntentService
                    LoginActivity.this.startService(mServerIntent);
                    Log.i("Login", " start service");

                    //TODO data exchange with server for login
                    /*Intent i = new Intent(LoginActivity.this,MainActivity.class);
                    i.putExtra("login", login.getText().toString());
                    i.putExtra("urlServlet",urlServlet);
                    startActivity(i);*/
				}
			}
		});
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

    private class LoginResultReceiver extends ResultReceiver {
        /**
         * Create a new ResultReceive to receive results.  Your
         * {@link #onReceiveResult} method will be called from the thread running
         * <var>handler</var> if given, or from an arbitrary thread if null.
         *
         * @param handler
         */
        public LoginResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            super.onReceiveResult(resultCode, resultData);

            switch (resultCode) {
                case 1:
                    Log.i("Login", " receive data from service");
                    Intent i = new Intent(LoginActivity.this,MainActivity.class);

                    startActivity(i);
                    break;
                case 0:
                    String err = resultData.getString("err");
                    switch (err) {
                        case "email_err":
                            Toast.makeText(getApplicationContext(), "Email is error!", Toast.LENGTH_SHORT).show();
                            login.setText("");
                            password.setText("");
                            break;
                        case "password_err":
                            Toast.makeText(getApplicationContext(), "Password is error!", Toast.LENGTH_SHORT).show();
                            password.setText("");
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
