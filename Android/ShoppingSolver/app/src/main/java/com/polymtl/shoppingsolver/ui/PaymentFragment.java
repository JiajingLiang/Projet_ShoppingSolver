package com.polymtl.shoppingsolver.ui;


import com.polymtl.shoppingsolver.R;
import com.polymtl.shoppingsolver.service.ShoppingSolverIntentService;
import com.polymtl.shoppingsolver.util.ShoppingSolverApplication;
import com.zxing.activity.CaptureActivity;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class PaymentFragment extends Fragment {

    public static final int PICK_PRODUCTID_REQUEST = 0;
    public static final int PICK_SHOPID_REQUEST = 1;

	private Context mContext;
	private View rootView;

    private ListView shoppingListView;
    private static CustomerBaseAdapter mAdapter;
    private TextView tvShoppingList;


    public static CustomerBaseAdapter getmAdapter() {
        return mAdapter;
    }

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        init();
        rootView = inflater.inflate(R.layout.fragment_payment, container, false);


        setUpButtons();

        setUpShoppingList(inflater);

        return rootView;
    }

    private void init() {
        this.mContext = getActivity().getApplicationContext();
        this.mContext = getActivity();

        if (this.mContext == null) {
            Log.i("err:", "the fragment is not attached to an activity");
            return;
        }

        Bundle bundle = getArguments();

        // ??
        if(bundle.containsKey("command")) {

            getActivity().runOnUiThread(new RefreshListView());
        }

    }

    private void setUpButtons(){

        ImageButton buttonPay = (ImageButton) rootView.findViewById(R.id.buttonPay);
        buttonPay.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {

                // create new Intent to invoke ShoppingSolverIntentService
                Intent mServerIntent = new Intent(getActivity(), ShoppingSolverIntentService.class);
                mServerIntent.putExtra("command", "payment"); // get product price in shop
                mServerIntent.putExtra("receiver", new PayResultReceiver(new Handler()));
                // start IntentService
                getActivity().startService(mServerIntent);


            }
        });

        //
        // buttonPay.setEnabled(false);

        ImageButton buttonScan = (ImageButton)rootView.findViewById(R.id.buttonScan);
        buttonScan.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {

                if (ShoppingSolverApplication.getInstance().getShop().getShopId() == 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    // Add the buttons
                    builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(getActivity(), CaptureActivity.class);
                            getActivity().startActivityForResult(intent, PICK_SHOPID_REQUEST);
                        }
                    });
                    builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            return;
                        }
                    });
                    // Set other dialog properties
                    builder.setTitle("Please get Shop ID");
                    builder.setMessage("Do you want to get Shop ID by Scan?");
                    // Create the AlertDialog
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
                    Intent openCameraIntent = new Intent(getActivity(), CaptureActivity.class);
                    // Start this activity and get a result back from this activity when it ends
                    getActivity().startActivityForResult(openCameraIntent, PICK_PRODUCTID_REQUEST);
                }
            }

        });
    }

    private void setUpShoppingList(LayoutInflater inflater) {

        tvShoppingList = (TextView) rootView.findViewById(R.id.tvShoppingList);

        ShoppingSolverApplication app = ShoppingSolverApplication.getInstance();
        //ArrayList<ShoppingRecord> shoppingRecords = app.getShoppingRecords();

        shoppingListView = (ListView) rootView.findViewById(R.id.shoppingList);
        mAdapter = new CustomerBaseAdapter(mContext);

        ShoppingSolverApplication.getInstance().setAdapter(mAdapter);
        shoppingListView.setAdapter(mAdapter);


        shoppingListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("click", "click");
                // open ItemDialogFragment
                Log.i("Open item:", "click");
                StartCommunication comm = (StartCommunication) getActivity();
                comm.openShoppingItem(position);
            }
        });

    }

    // used for communication between this fragment with another fragment
    public interface StartCommunication{
        public void openShoppingItem(int position);
        public void openTransaction();
    }


    //
    public class RefreshListView implements Runnable {

        @Override
        public void run() {

             mAdapter.notifyDataSetChanged();

        }
    }


    private class PayResultReceiver extends ResultReceiver {

        /**
         * Create a new ResultReceive to receive results.  Your
         * {@link #onReceiveResult} method will be called from the thread running
         * <var>handler</var> if given, or from an arbitrary thread if null.
         *
         * @param handler
         */
        public PayResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            super.onReceiveResult(resultCode, resultData);
            switch (resultCode) {
                case 1:
                    Toast.makeText(getActivity(),
                            "Payment successfully!",
                            Toast.LENGTH_SHORT).show();

                    ShoppingSolverApplication application = ShoppingSolverApplication.getInstance();
                    application.getTheLastTransaction()
                            .setShoppingList(application.getShoppingRecords());

                    Intent mServerIntent = new Intent(getActivity(), ShoppingSolverIntentService.class);
                    mServerIntent.putExtra("command", "sendConsumptionHabit"); // get product price in shop
                    mServerIntent.putExtra("receiver", new PayResultReceiver(new Handler()));
                    // start IntentService
                    getActivity().startService(mServerIntent);

                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.s z");
                    sdf.setTimeZone(TimeZone.getDefault());
                    String strDate = sdf.format(date);
                    application.getTheLastTransaction().setTime(strDate);
                    StartCommunication comm = (StartCommunication) getActivity();
                    comm.openTransaction();

                    break;
                case 0:
                    Toast.makeText(getActivity(),
                            "Payment failed!",
                            Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    Toast.makeText(getActivity(),
                            "Failed to connect to server!",
                            Toast.LENGTH_SHORT).show();
                    break;
                case 4:
                    break;
                default:
                    break;
            }


        }

    }








}

