package com.polymtl.shoppingsolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import android.app.Activity;
import android.app.Fragment;
//import android.support.v4.app.FragmentManager;
import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.polymtl.shoppingsolver.model.Category;
import com.polymtl.shoppingsolver.model.NavDrawerItem;
import com.polymtl.shoppingsolver.model.Product;
import com.polymtl.shoppingsolver.model.ShoppingItem;
import com.polymtl.shoppingsolver.service.ShoppingSolverIntentService;
import com.polymtl.shoppingsolver.ui.HabitFragment;
import com.polymtl.shoppingsolver.ui.ItemDialogFragment;
import com.polymtl.shoppingsolver.ui.ReceptionFragment;

public class MainActivity extends Activity implements PaymentFragment.StartCommunication{

	private String[] mNavigationDrawerItemTitles;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	// nav drawer title
	private CharSequence mDrawerTitle;
	// used to store app title
	private CharSequence mTitle;
	
	//private String userId;
	//private String urlServlet;
	
	private final static int SCANNIN_GREQUEST_CODE = 1;

    private static ArrayList<ShoppingItem> shoppingItems;
    private static int currentPosition;
    private static long shopId;

    private Product itemProduct;
    private ShoppingItem shoppingItem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        init();

        testShoppingList(); // Test data

        setUpDrawerLayout();

		if(savedInstanceState == null){
			selectItem(0);
		}

	}

    private void init() {

        Intent intent = getIntent();
        //userId = intent.getStringExtra("login");
        //urlServlet = intent.getStringExtra("urlServlet");

        shoppingItems = new ArrayList<>();
    }

    private void testShoppingList() {

        itemProduct = new Product();
        itemProduct.setProductName("Milk");
        itemProduct.setUnit_price(3.20f);

        itemProduct.setCategoryName("Food");
        itemProduct.setFederalTaxRatio(0.00f);
        itemProduct.setProvincialTaxRatio(0.00f);
        shoppingItem = new ShoppingItem(itemProduct);
        shoppingItem.setQuantity(1.0f);
        shoppingItem.getItemTotalPrice();
        shoppingItems.add(shoppingItem);

        itemProduct = new Product();
        itemProduct.setProductName("Beer");
        itemProduct.setUnit_price(2.50f);
        itemProduct.setCategoryName("Alcohol");
        itemProduct.setFederalTaxRatio(0.15f);
        itemProduct.setProvincialTaxRatio(0.26f);
        shoppingItem = new ShoppingItem(itemProduct);
        shoppingItem.setQuantity(12.0f);
        shoppingItem.getItemTotalPrice();

        shoppingItems.add(shoppingItem);

    }

    private void setUpDrawerLayout() {

        mTitle = mDrawerTitle = getTitle();
        mNavigationDrawerItemTitles = getResources().getStringArray(R.array.nav_drawer_items);
        mDrawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        mDrawerList = (ListView)findViewById(R.id.left_drawer);
        NavDrawerItem[] drawerItem = new NavDrawerItem[4];
        drawerItem[0] = new NavDrawerItem(R.drawable.ic_payment,"Make Payment");
        drawerItem[1] = new NavDrawerItem(R.drawable.ic_count_detail,"Count Detail");
        drawerItem[2] = new NavDrawerItem(R.drawable.ic_habit, "Consumption habit");
        drawerItem[3] = new NavDrawerItem(R.drawable.ic_reception, "Your receptions");
        DrawerItemAdapter adapter = new DrawerItemAdapter(this,R.layout.listview_item_row,drawerItem);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                R.drawable.ic_drawer,
                R.string.drawer_open,
                R.string.drawer_close
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getActionBar().setTitle(mTitle);
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActionBar().setTitle(mDrawerTitle);
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
		
		if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action bar actions click
        switch (item.getItemId()) {
        case R.id.action_settings:
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
	}
	
	@Override
	public void setTitle(CharSequence title) {
	    mTitle = title;
	    getActionBar().setTitle(mTitle);
	}
	
	@SuppressWarnings("deprecation")
	@Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void setComm(int position) {

        FragmentManager fragmentManager = getFragmentManager();

         Log.i("Open item:", "setComm");
         ItemDialogFragment itemDialogFragment = ItemDialogFragment.newInstance(position);
         Log.i("Open item:", "position" + position);
         Log.i("Open item:", "open item fragment");
         fragmentManager.beginTransaction().replace(R.id.content_frame, itemDialogFragment)
                 .addToBackStack("payment").commit();

    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
	    
	    @Override
	    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	        selectItem(position);
	    }
	    
	}

    /** Swaps fragments in the main content view */
	private void selectItem(int position) {
	    Fragment fragment = null;
	    Bundle bundle=new Bundle();
	    switch (position) {
	        case 0:
	            fragment = new PaymentFragment();
	            break;
	        case 1:
	            fragment = new CountFragment();
	            break;
            case 2:
                fragment = new HabitFragment();
                break;
            case 3:
                fragment = new ReceptionFragment();
                break;
	        default:
	            break;
	    }
	    if (fragment != null) {
	    	//bundle.putString("userId", userId);
	    	//bundle.putString("urlServlet", urlServlet);
	    	fragment.setArguments(bundle);
	    	
	        FragmentManager fragmentManager = getFragmentManager();
	        fragmentManager.beginTransaction().
                    replace(R.id.content_frame, fragment).commit();
	        mDrawerList.setItemChecked(position, true);
	        mDrawerList.setSelection(position);
	        setTitle(mNavigationDrawerItemTitles[position]);
	        mDrawerLayout.closeDrawer(mDrawerList);
	    } else {
	        Log.e("MainActivity", "Error in creating fragment");
	    }	    
	}

	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		super.onActivityResult(requestCode, resultCode, data);

        // come back scan request, send the code to Server
        if (requestCode == PaymentFragment.PICK_PRODUCTID_REQUEST) {

            if (resultCode == RESULT_OK) {
                Bundle bundle = data.getExtras();
                String scanResult = bundle.getString("result");

                // TODO:
                // create new Intent to invoke ShoppingSolverIntentService
                Intent mServerIntent = new Intent(MainActivity.this, ShoppingSolverIntentService.class);
                mServerIntent.putExtra("command", "productInfo");
                mServerIntent.putExtra("productBarCode", scanResult);
                mServerIntent.putExtra("idShop", MainActivity.getShopId());
                mServerIntent.putExtra("receiver", new SSResultReceiver(new Handler()));
                // start IntentService
                MainActivity.this.startService(mServerIntent);
            }
        } else if (requestCode == PaymentFragment.PICK_SHOPID_REQUEST) {
            if (resultCode == RESULT_OK) {
                Bundle bundle = data.getExtras();
                MainActivity.setShopId(Long.parseLong(bundle.getString("result")));
            }
        }
	}

    public static ArrayList<ShoppingItem> getShoppingItems() {
        return MainActivity.shoppingItems;
    }

    public static void addShoppingItem(ShoppingItem item) {
        MainActivity.shoppingItems.add(item);
    }

    public static void removeOneShoppingItem(int position) {
        MainActivity.shoppingItems.remove(position);
    }


    public static void setCurrentPosition(int position) {
        currentPosition = position;
    }

    public static int getCurrentPosition() {
        return currentPosition;
    }

    public static void setShopId(long id) {
        shopId = id;
    }
    public static long getShopId() {
        return shopId;
    }

    private class SSResultReceiver extends ResultReceiver {

        private final int ERROR = 0, PRODUCTINFO = 1, PAYMENT = 2,
                CLIENTINFO = 3, CONSUMPTIVEHABIT = 4, SALEINFO = 5;


        /**
         * Create a new ResultReceive to receive results.  Your
         * {@link #onReceiveResult} method will be called from the thread running
         * <var>handler</var> if given, or from an arbitrary thread if null.
         *
         * @param handler
         */
        public SSResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            super.onReceiveResult(resultCode, resultData);

            switch (resultCode) {
                case PRODUCTINFO:
                    Product newProduct = (Product)resultData.getSerializable("product");
                    boolean isExist = false;
                    for (ShoppingItem item: shoppingItems) {
                        // if this product is in the list, then add quantity
                        if (item.getProduct().getProductBarCode().equals(newProduct.getProductBarCode())) {
                            isExist = true;
                            item.setQuantity(item.getQuantity() + 1.0f);
                            PaymentFragment.getmAdapter().notifyDataSetChanged();
                        }
                    }
                    // if this product is not in the list, then add new item
                    if (!isExist) {
                        ShoppingItem newItem = new ShoppingItem(newProduct);
                        newItem.setQuantity(1.0f);

                    }
                    break;

                case ERROR:
                    break;
                default:
                    break;
            }
        }
    }
	
}
