package com.polymtl.shoppingsolver.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.polymtl.shoppingsolver.R;
import com.polymtl.shoppingsolver.util.ShoppingSolverApplication;



/**
 * Created by Zoe on 15-04-02.
 */
public class CustomerBaseAdapter extends BaseAdapter {

    private static LayoutInflater inflater = null;

    private ShoppingSolverApplication application;

    public CustomerBaseAdapter() {}

    public CustomerBaseAdapter(Context context) {

        /****Layout inflator to call external xml layout*/
        inflater = LayoutInflater.from(context);

        application = ShoppingSolverApplication.getInstance();

    }

    /**** Create a holder class to contain inflated xml file elements
     * It uses the ViewHolder pattern to avoid calling findViewById() when it is not necessary****/

    public static class ViewHolder {
        public TextView productName;
        public TextView priceQuantity;
        public ImageButton btn_delete;

    }

    @Override
    public int getCount() {
        // How many items are in the data set represented by this Adapter
        return application.getRecordsCount();

    }

    @Override
    public Object getItem(int position) {
        // Get the data item associated with the specified position in the data set
        return position;
    }

    @Override
    public long getItemId(int position) {
        // Get the row id associated with the specified position in the lis
        return position;
    }

    /***** Create each ListView row
     * It reuses the convertView passed to getView() to avoid inflating View when it is not necessary******/
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item, null);

            /***** View holder object to contain item.xml file elements ********/
            holder = new ViewHolder();
            holder.productName = (TextView) convertView.findViewById(R.id.tvProductName);
            holder.priceQuantity = (TextView) convertView.findViewById(R.id.tvPriceQuantity);
            holder.btn_delete = (ImageButton) convertView.findViewById(R.id.btn_delete);
            holder.btn_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    application.removeRecord(position);
                    notifyDataSetChanged();
                }
            });

            holder.productName.setText(application.getShoppingRecords().get(position).getDescription());
            holder.priceQuantity.setText("Unit price: " + application.getShoppingRecords().get(position).getUnit_price()
                    + "$  quantity: " + application.getShoppingRecords().get(position).getQuantity());
            /***** Set holder with LayoutInflater *****/
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        /**** Get each ShoppingItem from ArrayList *****/

        return convertView;
    }
}
