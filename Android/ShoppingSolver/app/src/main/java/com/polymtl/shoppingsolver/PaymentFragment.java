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
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class PaymentFragment extends Fragment {

    static final int PICK_SCAN_REQUEST = 0;

	private Context mContext;
	private String urlServlet;
	private View rootView;
    private String userId;
    private ListView shoppingListView;
    private CustomerBaseAdapter mAdapter;
    private TextView tvShopingList;
    //private List<Map<String, Object>> shoppingListData;

	/**public PaymentFragment(Context context){
		this.mcontext = context;
	}*/


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
                Log.i("Update:", "pay button clicked");
                // Test change list

                Product itemProduct = new Product();
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
                getActivity().runOnUiThread(new RefreshListView());

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
                // TODO Auto-generated method stub
                Intent openCameraIntent = new Intent(getActivity(),CaptureActivity.class);
                // Start this activity and get a result back from this activity when it ends
                getActivity().startActivityForResult(openCameraIntent, PICK_SCAN_REQUEST);
            }

        });
    }


   /* private void setData() {

       shoppingListData = new ArrayList<Map<String, Object>>();
        Map<String, Object> map;

        map = new HashMap<String, Object>();
        Product milk = new Product();
        Category food = new Category();
        food.setName("Food");
        food.setRatioTPS(0.0f);
        food.setRatioTVQ(0.0f);
        milk.setCategory(food);
        milk.setName("Milk");
        milk.setUnit_price(3.20f);
        ItemByIndividual milkItem = new ItemByIndividual(milk);
        map.put("name", milkItem.getProduct().getName());
        map.put("unitPrice", milk.getUnit_price());
        map.put("quantity", milkItem.getQuantity());

        shoppingListData.add(map);
    }*/

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

        //code to add header and footer to listview
        /*ViewGroup header = (ViewGroup) inflater.inflate(R.layout.header, shoppingListView,
               false);

        shoppingListView.addHeaderView(header, null, false);*/

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

    // When shopping list item quantity changed then update this item quantity
    /*private void updateItemQuantityView(int itemIndex, int gap) {

        View v = shoppingListView.getChildAt(itemIndex - shoppingListView.getFirstVisiblePosition());
        CustomerArrayAdapter.ViewHolder viewHolder = (CustomerArrayAdapter.ViewHolder) v.getTag();
        if (viewHolder != null) {
            int quantity = Integer.parseInt(viewHolder.quantity.getText().toString()) + gap;
            if (quantity <= 0) {
                deleteShoppingListItem(itemIndex);
            } else {
                viewHolder.quantity.setText(Integer.parseInt(viewHolder.quantity.getText().toString()) + gap);
            }
        }

    }*/

    // Delete one of shopping list item
    private void deleteShoppingListItem(int itemIndex) {
        // TODO:
        //shoppingListData.remove(itemIndex);

        mAdapter.notifyDataSetChanged();
    }

    private void addShoppingListItem() {
        // TODO:
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
			/*Toast.makeText(PaymentFragment.this.mContext, "You've just made a payment!", Toast.LENGTH_SHORT).show();
			TextView tvTotal = (TextView)PaymentFragment.this.rootView.findViewById(R.id.tvTotalVal);
			tvTotal.setText("");
			TextView tvProducts = (TextView)PaymentFragment.this.rootView.findViewById(R.id.tvProductsVal);
			tvProducts.setText("");
			ImageButton buttonPay = (ImageButton) PaymentFragment.this.rootView.findViewById(R.id.buttonPay);
			buttonPay.setEnabled(false);*/
		}
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

