package com.polymtl.shoppingsolver.ui;

import java.util.ArrayList;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
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
import android.widget.ListView;
import android.widget.Toast;

import com.polymtl.shoppingsolver.R;
import com.polymtl.shoppingsolver.model.NavDrawerItem;
import com.polymtl.shoppingsolver.model.ShoppingRecord;
import com.polymtl.shoppingsolver.service.ShoppingSolverIntentService;
import com.polymtl.shoppingsolver.util.ShoppingSolverApplication;

public class MainActivity extends Activity implements PaymentFragment.StartCommunication{

	private String[] mNavigationDrawerItemTitles;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	// nav drawer title
	private CharSequence mDrawerTitle;
	// used to store app title
	private CharSequence mTitle;

	private final static int SCANNIN_GREQUEST_CODE = 1;

    private ShoppingSolverApplication shoppingSolverApp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


        shoppingSolverApp = ShoppingSolverApplication.getInstance();

        shoppingSolverApp.getInstance().addActivity(this);

        setUpDrawerLayout();

		if(savedInstanceState == null){
			selectItem(0);
		}

	}

    //shopping list for test
    private void testShoppingList() {

        ArrayList<ShoppingRecord> shoppingRecords = new ArrayList<>();
        ShoppingRecord shoppingRecord = new ShoppingRecord();
        shoppingRecord.setDescription("Milk");
        shoppingRecord.setUnit_price(3.20f);

        shoppingRecord.setCategoryName("Food");
        shoppingRecord.setFederalTaxRatio(0.00f);
        shoppingRecord.setProvincialTaxRatio(0.00f);

        shoppingRecord.setQuantity(1.0f);
        shoppingRecord.getItemTotalPrice();
        shoppingRecord.setProductBarCode("068100047219");
        shoppingRecords.add(shoppingRecord);

        shoppingRecord = new ShoppingRecord();
        shoppingRecord.setDescription("Beer");
        shoppingRecord.setUnit_price(2.50f);
        shoppingRecord.setCategoryName("Alcohol");
        shoppingRecord.setFederalTaxRatio(0.15f);
        shoppingRecord.setProvincialTaxRatio(0.26f);

        shoppingRecord.setQuantity(12.0f);
        shoppingRecord.getItemTotalPrice();
        shoppingRecord.setProductBarCode("068200010311");

        shoppingRecords.add(shoppingRecord);

        Log.i("productBarCode", shoppingRecords.get(0).getProductBarCode());
        Log.i("productBarCode", shoppingRecords.get(1).getProductBarCode());
        shoppingSolverApp.setShoppingRecords(shoppingRecords);

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
        drawerItem[3] = new NavDrawerItem(R.drawable.ic_reception, "Your Receptions");
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



    private class DrawerItemClickListener implements ListView.OnItemClickListener {
	    
	    @Override
	    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	        selectItem(position);
	    }
	    
	}

    /** Swaps fragments in the main content view */
	private void selectItem(int position) {
	    Fragment fragment = null;
        FragmentManager fragmentManager = getFragmentManager();
	    Bundle bundle=new Bundle();
	    switch (position) {
	        case 0:
	            fragment = new PaymentFragment();
                fragment.setArguments(bundle);
                fragmentManager.beginTransaction().
                        replace(R.id.content_frame, fragment).commit();
	            break;
	        case 1:
	            fragment = new CountFragment();
                fragment.setArguments(bundle);
                /*fragmentManager.beginTransaction().
                        replace(R.id.content_frame, fragment).addToBackStack(null).commit();*/
                fragmentManager.beginTransaction().
                        replace(R.id.content_frame, fragment).commit();

	            break;
            case 2:
                fragment = new HabitFragment();
                fragment.setArguments(bundle);
                /*fragmentManager.beginTransaction().
                        replace(R.id.content_frame, fragment).addToBackStack(null).commit();*/
                fragmentManager.beginTransaction().
                        replace(R.id.content_frame, fragment).commit();
                break;
            case 3:
                // send request to server to get recent transaction
                Intent mServerIntent = new Intent(MainActivity.this, ShoppingSolverIntentService.class);
                mServerIntent.putExtra("command", "getRecentTransactions"); // get product price in shop

                mServerIntent.putExtra("receiver", new SSResultReceiver(new Handler()));
                // start IntentService
                MainActivity.this.startService(mServerIntent);

                break;
	        default:
	            break;
	    }
	    if (fragment != null) {
	    	/*fragment.setArguments(bundle);
	        fragmentManager.beginTransaction().
                    replace(R.id.content_frame, fragment).addToBackStack(null).commit();*/
	        mDrawerList.setItemChecked(position, true);
	        mDrawerList.setSelection(position);
	        setTitle(mNavigationDrawerItemTitles[position]);
	        mDrawerLayout.closeDrawer(mDrawerList);
	    } else {
	        Log.e("MainActivity", "Error in creating fragment");
	    }	    
	}


    // After finishing scan, the scan result will come back to MainActivity by onActivityResult method
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		super.onActivityResult(requestCode, resultCode, data);

        // come back scan request, send the code to Server
        if (requestCode == PaymentFragment.PICK_PRODUCTID_REQUEST) {

            if (resultCode == RESULT_OK) {


                Bundle bundle = data.getExtras();
                String scanResult = bundle.getString("result");

                //create new ShoppingItem object
                ShoppingRecord shoppingRecord = new ShoppingRecord(scanResult);

                shoppingRecord.setProductBarCode(scanResult);
                shoppingRecord.setQuantity(1.0f);
                shoppingSolverApp.setCurrentRecord(shoppingRecord);

                // create new Intent to invoke ShoppingSolverIntentService
                Intent mServerIntent = new Intent(MainActivity.this, ShoppingSolverIntentService.class);
                mServerIntent.putExtra("command", "productInfo"); // get product price in shop

                mServerIntent.putExtra("receiver", new SSResultReceiver(new Handler()));
                // start IntentService
                MainActivity.this.startService(mServerIntent);
            }
        } else if (requestCode == PaymentFragment.PICK_SHOPID_REQUEST) {
            if (resultCode == RESULT_OK) {
                Bundle bundle = data.getExtras();
                long shopId = Long.parseLong(bundle.getString("result"));
                Log.i("scan result", "id " + shopId);
                shoppingSolverApp.getShop().setShopId(shopId);
                Log.i("shopId", "" + shoppingSolverApp.getShop().getShopId());

                // create new Intent to invoke ShoppingSolverIntentService
                Intent mServerIntent = new Intent(MainActivity.this, ShoppingSolverIntentService.class);
                mServerIntent.putExtra("command", "shopInfo"); // get product price in shop
                mServerIntent.putExtra("receiver", new SSResultReceiver(new Handler()));
                // start IntentService to get shop info
                MainActivity.this.startService(mServerIntent);
            }
        }
	}


    /**
     * Class SSResultReceiver used for receiving a callback result from ShoppingSolverIntentService
     * */
    private class SSResultReceiver extends ResultReceiver {

        private final int ERROR = 0, SUCCESS = 1;


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

            String type = resultData.getString("requestType");
            if ("getProductInfo".equals(type)) {
                switch (resultCode) {
                    case SUCCESS:
                        shoppingSolverApp.addCurrentShoppingRecord();
                        shoppingSolverApp.getAdapter().notifyDataSetChanged();
                        break;

                    case ERROR:
                        Toast.makeText(MainActivity.this,
                                "Sorry! Didn't find this product's information!",
                                Toast.LENGTH_SHORT).show();
                        //ShoppingSolverApplication.getInstance().getShop().setShopId(0);
                        break;
                    default:
                        break;
                }
            } else if ("getShopInfo".equals(type)) {
                switch (resultCode) {
                    case SUCCESS:
                        break;
                    case ERROR:
                        Toast.makeText(MainActivity.this,
                                "Sorry! This shop is not registered our service!",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(getApplicationContext(),
                                "Failed to connect to server!",
                                Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            } else if ("getRecentTransactions".equals(type)) {
                switch (resultCode) {
                    case SUCCESS:
                        Bundle bundle = new Bundle();
                        FragmentManager fragmentManager = getFragmentManager();
                        Fragment fragment = new TransactionFragment();
                        bundle.putString("type", "recentTransactions");
                        fragment.setArguments(bundle);
                        /*fragmentManager.beginTransaction().
                                replace(R.id.content_frame, fragment).addToBackStack(null).commit();*/
                        fragmentManager.beginTransaction().
                                replace(R.id.content_frame, fragment).commit();
                        mDrawerList.setItemChecked(3, true);
                        mDrawerList.setSelection(3);
                        setTitle(mNavigationDrawerItemTitles[3]);
                        mDrawerLayout.closeDrawer(mDrawerList);
                        break;
                    case ERROR:
                        Toast.makeText(MainActivity.this,
                                "Failed to get recent transactions!",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(getApplicationContext(),
                                "Failed to connect to server!",
                                Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        }
    }

    @Override
    public void onBackPressed(){
        FragmentManager fm = getFragmentManager();

        // when return back payment fragment from other fragment, update drawerList selected position
        // and update the title

        if (fm.getBackStackEntryCount() > 0) {
            Log.i("MainActivity", "popping backstack");
            mDrawerList.setSelection(0);
            setTitle(mNavigationDrawerItemTitles[0]);
            fm.popBackStack();
        } else {
            Log.i("MainActivity", "nothing on backstack, calling super");
            super.onBackPressed();
        }
    }



    // Used for opening new fragment from a fragment via the activity
    @Override
    public void openShoppingItem(int position) {

        FragmentManager fragmentManager = getFragmentManager();

        Log.i("Open item:", "setComm");
        ItemDialogFragment itemDialogFragment = ItemDialogFragment.newInstance(position);
        Log.i("Open item:", "position" + position);
        Log.i("Open item:", "open item fragment");
        fragmentManager.beginTransaction().replace(R.id.content_frame, itemDialogFragment)
                .addToBackStack("payment").commit(); // addToBackStack: close fragment then return payment fragment


        setTitle("Product");

    }

    @Override
    public void openTransaction() {

        FragmentManager fragmentManager = getFragmentManager();
        Bundle bundle=new Bundle();
        bundle.putString("type", "currentTransaction");
        Fragment fragment = new com.polymtl.shoppingsolver.ui.TransactionFragment();
        fragment.setArguments(bundle);
        fragmentManager.beginTransaction().
                replace(R.id.content_frame, fragment).addToBackStack(null).commit();
        mDrawerList.setItemChecked(3, true);
        mDrawerList.setSelection(3);
        setTitle(mNavigationDrawerItemTitles[3]);
        mDrawerLayout.closeDrawer(mDrawerList);

    }

}
