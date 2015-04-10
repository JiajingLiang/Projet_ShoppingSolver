package com.polymtl.shoppingsolver;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.JSONObject;

import com.polymtl.shoppingsolver.model.Category;
import com.polymtl.shoppingsolver.model.Product;
import com.polymtl.shoppingsolver.model.ShoppingItem;
import com.polymtl.shoppingsolver.ui.CustomerBaseAdapter;
import com.zxing.activity.CaptureActivity;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class PaymentFragment extends Fragment {

    static final int PICK_PRODUCTID_REQUEST = 0;
    static final int PICK_SHOPID_REQUEST = 1;

	private Context mContext;
	private String urlServlet;
	private View rootView;
    private String userId;
    private ListView shoppingListView;
    private static CustomerBaseAdapter mAdapter;
    private TextView tvShopingList;
    //private List<Map<String, Object>> shoppingListData;

	/**public PaymentFragment(Context context){
		this.mcontext = context;
	}*/

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
        //this.mcontext = getActivity();

        if (this.mContext == null) {
            Log.i("err:", "the fragment is not attached to an activity");
            return;
        }

        Bundle bundle = getArguments();
        userId = bundle.getString("userId");
        urlServlet = bundle.getString("urlServlet");

        if(bundle.containsKey("command")) {

            getActivity().runOnUiThread(new RefreshListView());
        }

    }

    private void setUpButtons(){

        ImageButton buttonPay = (ImageButton) rootView.findViewById(R.id.buttonPay);
        buttonPay.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {

                // Test change list
                /*Product itemProduct = new Product();
                itemProduct.setName("Milk");
                itemProduct.setUnit_price(3.20f);
                Category itemCategory = new Category();
                itemCategory.setName("Food");
                itemCategory.setRatioTVQ(0.00f);
                itemCategory.setRatioTPS(0.00f);
                itemProduct.setCategory(itemCategory);
                ShoppingItem shoppingItem = new ShoppingItem(itemProduct);
                shoppingItem.setQuantity(1.0f);
                shoppingItem.getItemTotalPrice();

                Log.i("Update:", "start RefreshListView");
                MainActivity.addShoppingItem(shoppingItem);
                getActivity().runOnUiThread(new RefreshListView());*/

                /*TextView tvTotal = (TextView)rootView.findViewById(R.id.tvTotalVal);
                Double total = Double.parseDouble(tvTotal.getText().toString());
                TextView tvProducts = (TextView)rootView.findViewById(R.id.tvProductsVal);
                String products = tvProducts.getText().toString();
                JSONObject json = new JSONObject();
                json.put("userID", Integer.parseInt(userId));
                json.put("total", total);
                json.put("products", products);
                MakePaymentTask makePaymentTask = new MakePaymentTask();
                makePaymentTask.execute(json);*/
            }
        });

        //
        // buttonPay.setEnabled(false);

        ImageButton buttonScan = (ImageButton)rootView.findViewById(R.id.buttonScan);
        buttonScan.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {

                if (MainActivity.getShopId() == 0) {
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

        tvShopingList = (TextView) rootView.findViewById(R.id.tvShoppingList);

        // TODO: when list cleared, should show this text
        if(MainActivity.getShoppingItems().size() < 1) {
            tvShopingList.setText("Nothing in the list, please scan your products...");
        } else {
            tvShopingList.setEnabled(false);
        }

        shoppingListView = (ListView) rootView.findViewById(R.id.shoppingList);
        mAdapter = new CustomerBaseAdapter(mContext, MainActivity.getShoppingItems());

        shoppingListView.setAdapter(mAdapter);


        shoppingListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("click", "click");
                // open ItemDialogFragment
                // TODO:
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


















}

