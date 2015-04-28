package com.polymtl.shoppingsolver.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.polymtl.shoppingsolver.R;
import com.polymtl.shoppingsolver.database.ClientDataSource;
import com.polymtl.shoppingsolver.model.Client;
import com.polymtl.shoppingsolver.service.ShoppingSolverIntentService;
import com.polymtl.shoppingsolver.util.ShoppingSolverApplication;

import java.io.IOException;


public class LoginActivity extends Activity {
	private EditText login;
	private EditText password;
    private Button btn_newUser;
    private TextView tvPowerConsume;

    GoogleCloudMessaging gcm;

    Context context;
    String regid;
    public static final String EXTRA_MESSAGE = "message";
    public static final String PROPERTY_REG_ID = "registration_id";
    private static final String PROPERTY_APP_VERSION = "appVersion";
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    /**
     * Substitute you own sender ID here. This is the project number you got
     * from the API Console, as described in "Getting Started."
     */
    String SENDER_ID = "341543397352";

    static final String TAG = "GCMTest";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
        ShoppingSolverApplication.getInstance().addActivity(this);

        context = getApplicationContext();

        //get start power level
        ShoppingSolverApplication.getInstance().setStartPower(getPowerLever());
        tvPowerConsume = (TextView) this.findViewById(R.id.tvPower);
        tvPowerConsume.setText("Start power lever: " + ShoppingSolverApplication.getInstance().getStartPower());

        login = (EditText)this.findViewById(R.id.editText_login);
        password = (EditText)this.findViewById(R.id.editText_password);

        btn_newUser = (Button) this.findViewById(R.id.button_create);
        btn_newUser.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), NewCountActivity.class);
                startActivity(i);
            }
        });

        ClientDataSource clientDataSource = new ClientDataSource(getApplicationContext());
        clientDataSource.open();
        Client client = clientDataSource.getClient();
        if (client != null) {
            login.setText(client.getEmail());
            //password.setText(client.getPassword());
        }
        clientDataSource.close();


		Button connexionButton = (Button)this.findViewById(R.id.button_Connextion);

		connexionButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {

				String strLogin = login.getText().toString();
				String strPassword = password.getText().toString();
				if(strLogin.length()==0||strPassword.length()==0){
					Toast.makeText(getApplicationContext(), "Please user name and password", Toast.LENGTH_SHORT).show();
				}else{

                    // create new Intent to invoke ShoppingSolverIntentService
                    Intent mServerIntent = new Intent(LoginActivity.this, ShoppingSolverIntentService.class);
                    mServerIntent.putExtra("command", "login");
                    mServerIntent.putExtra("email", strLogin);
                    mServerIntent.putExtra("password", strPassword);
                    mServerIntent.putExtra("receiver", new LoginResultReceiver(new Handler()));

                    // start IntentService
                    Log.i("Login", " start service");
                    LoginActivity.this.startService(mServerIntent);

				}
			}
		});


        // Check device for Play Services APK. If check succeeds, proceed with
        //  GCM registration.
        if (checkPlayServices()) {
            gcm = GoogleCloudMessaging.getInstance(this);
            regid = getRegistrationId(context);

            if (regid.isEmpty()) {
                registerInBackground();
            } else {
                ShoppingSolverApplication.getInstance().setRegId(regid);
                Log.i("RegId", "lenth" + regid.length() );
            }
        } else {
            Log.i(TAG, "No valid Google Play Services APK found.");
        }
    }


    public float getPowerLever() {

        Intent batteryIntent = context.getApplicationContext().registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        int level = batteryIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryIntent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        if(scale > 0 && level >= 0)
            return (level * 100.0f)/scale;

        else
            return 0.0f;

    }

    @Override
    protected void onResume() {
        super.onResume();
        // Check device for Play Services APK.
        checkPlayServices();

        float finishPowerLevel = getPowerLever();
        tvPowerConsume.setText("Start power lever: " + ShoppingSolverApplication.getInstance().getStartPower()
                + "\nFinish power lever: " + finishPowerLevel
                + "\nConsumed: " + (ShoppingSolverApplication.getInstance().getStartPower() - finishPowerLevel));
        Log.i("Login", "onResume");
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
                    Log.i("login", " receive data from service");
                    // create new Intent to invoke ShoppingSolverIntentService
                    Intent mServerIntent = new Intent(LoginActivity.this, ShoppingSolverIntentService.class);
                    mServerIntent.putExtra("command", "addDeviceKey");
                    // start IntentService
                    Log.i("addDeviceKey", " start service");
                    LoginActivity.this.startService(mServerIntent);

                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
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
                case 3:
                    Toast.makeText(getApplicationContext(), "Failed to connect to server!", Toast.LENGTH_SHORT).show();
                    break;

                default:
                    break;
            }
        }
    }

    /**
     * Check the device to make sure it has the Google Play Services APK. If
     * it doesn't, display a dialog that allows users to download the APK from
     * the Google Play Store or enable it in the device's system settings.
     */
    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Log.i(TAG, "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }

    /**
     * Stores the registration ID and the app versionCode in the application's
     * {@code SharedPreferences}.
     *
     * @param context application's context.
     * @param regId registration ID
     */
    private void storeRegistrationId(Context context, String regId) {
        final SharedPreferences prefs = getGcmPreferences(context);
        int appVersion = getAppVersion(context);
        Log.i(TAG, "Saving regId on app version " + appVersion);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PROPERTY_REG_ID, regId);
        editor.putInt(PROPERTY_APP_VERSION, appVersion);
        editor.commit();
    }

    /**
     * Gets the current registration ID for application on GCM service, if there is one.
     * <p>
     * If result is empty, the app needs to register.
     *
     * @return registration ID, or empty string if there is no existing
     *         registration ID.
     */
    private String getRegistrationId(Context context) {
        final SharedPreferences prefs = getGcmPreferences(context);
        String registrationId = prefs.getString(PROPERTY_REG_ID, "");
        if (registrationId.isEmpty()) {
            Log.i(TAG, "Registration not found.");
            return "";
        }
        // Check if app was updated; if so, it must clear the registration ID
        // since the existing regID is not guaranteed to work with the new
        // app version.
        int registeredVersion = prefs.getInt(PROPERTY_APP_VERSION, Integer.MIN_VALUE);
        int currentVersion = getAppVersion(context);
        if (registeredVersion != currentVersion) {
            Log.i(TAG, "App version changed.");
            return "";
        }
        return registrationId;
    }


    /**
     * Registers the application with GCM servers asynchronously.
     * <p>
     * Stores the registration ID and the app versionCode in the application's
     * shared preferences.
     */
    private void registerInBackground() {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String msg = "";
                try {
                    if (gcm == null) {
                        gcm = GoogleCloudMessaging.getInstance(context);
                    }
                    regid = gcm.register(SENDER_ID);
                    msg = "Device registered, registration ID=" + regid;

                    // You should send the registration ID to your server over HTTP, so it
                    // can use GCM/HTTP or CCS to send messages to your app.
                    ShoppingSolverApplication.getInstance().getNewCount().setDeviceKey(regid);
                    //sendRegistrationIdToBackend();

                    // For this demo: we don't need to send it because the device will send
                    // upstream messages to a server that echo back the message using the
                    // 'from' address in the message.

                    // Persist the regID - no need to register again.
                    ShoppingSolverApplication.getInstance().setRegId(regid);
                    storeRegistrationId(context, regid);
                } catch (IOException ex) {
                    msg = "Error :" + ex.getMessage();
                    // If there is an error, don't just keep trying to register.
                    // Require the user to click a button again, or perform
                    // exponential back-off.
                }
                return msg;
            }

            @Override
            protected void onPostExecute(String msg) {

                Log.i(TAG, msg);
            }
        }.execute(null, null, null);
    }


    /**
     * @return Application's version code from the {@code PackageManager}.
     */
    private static int getAppVersion(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            // should never happen
            throw new RuntimeException("Could not get package name: " + e);
        }
    }

    /**
     * @return Application's {@code SharedPreferences}.
     */
    private SharedPreferences getGcmPreferences(Context context) {
        // This sample app persists the registration ID in shared preferences, but
        // how you store the regID in your app is up to you.
        return getSharedPreferences(LoginActivity.class.getSimpleName(),
                Context.MODE_PRIVATE);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            ShoppingSolverApplication.getInstance().onTerminate();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
