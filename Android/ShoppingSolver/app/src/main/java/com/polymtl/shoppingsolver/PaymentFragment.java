package com.polymtl.shoppingsolver;


import com.polymtl.shoppingsolver.model.ShoppingRecord;
import com.polymtl.shoppingsolver.service.ShoppingSolverIntentService;
import com.polymtl.shoppingsolver.ui.CustomerBaseAdapter;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class PaymentFragment extends Fragment {

    static final int PICK_PRODUCTID_REQUEST = 0;
    static final int PICK_SHOPID_REQUEST = 1;

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

                //set begin date
                //<date>2014-04-08 21:00:00.0 UTC</date>

                Date dNow = new Date(); //current time
                Date dBefore = new Date();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(dNow);
                calendar.add(Calendar.DAY_OF_MONTH, -1); // before
                dBefore = calendar.getTime();
                final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.s z");
                sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
                final String dateBegin = sdf.format(dNow);
                final String dateEnd = sdf.format(dBefore);
                ShoppingSolverApplication.getInstance().setDateBegin(dateBegin);
                ShoppingSolverApplication.getInstance().setDateEnd(dateEnd);

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
        ArrayList<ShoppingRecord> shoppingRecords = app.getShoppingRecords();
        // TODO: when list cleared, should show this text
        if(shoppingRecords.size() < 1) {
            tvShoppingList.setText("Nothing in the list, please scan your products...");
        } else {
            tvShoppingList.setEnabled(false);
        }

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
                comm.setComm(position);
            }
        });

    }

    // used for communication between this fragment with another fragment
    public interface StartCommunication{
        public void setComm(int position);
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
                    new AlertDialog.Builder(getActivity()).setMessage("Payment successfully! Do you want to get the reception?")
                            .setIcon(android.R.drawable.ic_dialog_info)
                            .setTitle("Payment Confirm")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    // create new Intent to invoke ShoppingSolverIntentService
                                    Intent mServerIntent = new Intent(getActivity(), ShoppingSolverIntentService.class);
                                    mServerIntent.putExtra("command", "getTransaction"); // get product price in shop
                                    mServerIntent.putExtra("receiver", new PayResultReceiver(new Handler()));
                                    // start IntentService
                                    getActivity().startService(mServerIntent);

                                }
                            }).setNegativeButton(android.R.string.no, null).show();
                    break;
            }


        }

    }








}

