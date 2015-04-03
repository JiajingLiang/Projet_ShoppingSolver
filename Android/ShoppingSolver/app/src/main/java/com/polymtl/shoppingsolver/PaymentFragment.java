package com.polymtl.shoppingsolver;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.JSONObject;

import com.zxing.activity.CaptureActivity;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PaymentFragment extends Fragment {
	private Context mcontext;
	private String urlServlet;
	private View rootView;
    private String userId;

	/**public PaymentFragment(Context context){
		this.mcontext = context;
	}*/

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        init();
        rootView = inflater.inflate(R.layout.fragment_payment, container, false);


        setUpButtons();
 
        return rootView;
    }

    private void init() {
        this.mcontext = getActivity().getApplicationContext();
        //this.mcontext = getActivity();

        if (this.mcontext == null) {
            Log.i("err:", "the fragment is not attached to an activity");
            return;
        }

        Bundle bundle = getArguments();
        userId = bundle.getString("userId");
        urlServlet = bundle.getString("urlServlet");
    }

    private void setUpButtons(){

        Button buttonPay = (Button) rootView.findViewById(R.id.buttonPay);
        buttonPay.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {
                TextView tvTotal = (TextView)rootView.findViewById(R.id.tvTotalVal);
                Double total = Double.parseDouble(tvTotal.getText().toString());
                TextView tvProducts = (TextView)rootView.findViewById(R.id.tvProductsVal);
                String products = tvProducts.getText().toString();
                JSONObject json = new JSONObject();
                json.put("userID", Integer.parseInt(userId));
                json.put("total", total);
                json.put("products", products);
                MakePaymentTask makePaymentTask = new MakePaymentTask();
                makePaymentTask.execute(json);
            }
        });

        buttonPay.setEnabled(false);

        Button buttonScan = (Button)rootView.findViewById(R.id.buttonScan);
        buttonScan.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent openCameraIntent = new Intent(getActivity(),CaptureActivity.class);
                getActivity().startActivityForResult(openCameraIntent, 0);
            }

        });
    }

    //TODO:
    private void updateShoppingList() {

    }

	private class MakePaymentTask extends AsyncTask<JSONObject, Integer, Void> {

		@Override
		protected Void doInBackground(JSONObject... params) {
			try {
				URL url = new URL(urlServlet);
				String data = params[0].toString();
				
				HttpURLConnection urlConn= (HttpURLConnection)url.openConnection();
				
				urlConn.setRequestMethod("POST");
				urlConn.setDoOutput(true);
				urlConn.setDoInput(true);
				
				urlConn.setFixedLengthStreamingMode(data.getBytes().length);
				
				urlConn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
				urlConn.setRequestProperty("Accept", "application/json");
				urlConn.setRequestProperty("X-Requested-With", "XMLHttpRequest");
				
				urlConn.connect();
				OutputStream os = new BufferedOutputStream(urlConn.getOutputStream());
				os.write(data.getBytes()); 
				os.flush();
				os.close();
				urlConn.disconnect();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result){
			Toast.makeText(PaymentFragment.this.mcontext, "You've juste made a payment!", Toast.LENGTH_SHORT).show();
			TextView tvTotal = (TextView)PaymentFragment.this.rootView.findViewById(R.id.tvTotalVal);
			tvTotal.setText("");
			TextView tvProducts = (TextView)PaymentFragment.this.rootView.findViewById(R.id.tvProductsVal);
			tvProducts.setText("");
			Button buttonPay = (Button) PaymentFragment.this.rootView.findViewById(R.id.buttonPay);
			buttonPay.setEnabled(false);
		}
	}
}

