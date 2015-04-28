package com.polymtl.shoppingsolver.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.polymtl.shoppingsolver.R;
import com.polymtl.shoppingsolver.model.SaleInfo;
import com.polymtl.shoppingsolver.ui.DrawPathAsyncTask;
import com.polymtl.shoppingsolver.util.AdjustCamera;
import com.polymtl.shoppingsolver.util.DetectConnectivity;
import com.polymtl.shoppingsolver.util.ShoppingSolverApplication;
import java.util.ArrayList;
import java.util.List;
import static com.polymtl.shoppingsolver.ui.CustomerPolyLine.*;

public class MapsShopsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_shops);
        setUpMapIfNeeded();
        ShoppingSolverApplication.getInstance().addActivity(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();

            // Then enable the My Location layer on the Map
            //The My Location button will be visible on the top right of the map.
            mMap.setMyLocationEnabled(true);

            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));

        // Move the camera instantly to Montreal.
        Log.i("setUpMap: ", "Move the camera instantly to Montreal.");
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(45.508536, -73.597929), 15));

        SaleInfo saleInfo = ShoppingSolverApplication.getInstance().getSaleInfo();
        LocationManager locationManager =  (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);


        if (!DetectConnectivity.isGpsProviderAccessed(getApplicationContext())
                && DetectConnectivity.isNetworkProviderAccessed(getApplicationContext())) {
            new AlertDialog.Builder(this)
                    .setMessage("Google Maps needs access to your location. Please turn on location access.")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Location services disabled")
                    .setPositiveButton(R.string.settings, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivityForResult(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS), 1);
                        }
                    }).setNegativeButton(R.string.ignore, null).show();
        } else {

            Location yourLocation = null;
            if (DetectConnectivity.isGpsProviderAccessed(getApplicationContext())
                    && locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER) != null) {
                yourLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            } else if (DetectConnectivity.isNetworkProviderAccessed(getApplicationContext())) {
                yourLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }


            LatLng yourLatLng = new LatLng(yourLocation.getLatitude(), yourLocation.getLongitude());

            setMarker(yourLatLng.toString(), "Your are here", yourLatLng);

            LatLng latLngShop = saleInfo.getLatLng();
            if (latLngShop != null) {
                String title = saleInfo.getDescription() + "\nSpecial: " + saleInfo.getPrice() + "$";
                String info = "Address: " + saleInfo.getAddress();

                setMarker(title, info, latLngShop);
                List<LatLng> points = new ArrayList<>();
                points.add(yourLatLng);
                points.add(latLngShop);

                new DrawPathAsyncTask(mMap, yourLatLng,latLngShop, makeURL(yourLatLng, latLngShop)).execute();

                AdjustCamera.fixZoom(mMap, points);

            } else {
                Toast.makeText(getApplicationContext(),
                        "Did not find the location!",
                        Toast.LENGTH_SHORT).show();
            }
        }


    }

    public void setMarker(String title, String info, LatLng latLng) {

        MarkerOptions markerOpt = new MarkerOptions();

        markerOpt.position(latLng);
        markerOpt.draggable(false);
        markerOpt.visible(true);
        markerOpt.anchor(0.5f, 0.5f);//set to be center of the picture
        markerOpt.icon(BitmapDescriptorFactory.fromResource((R.drawable.marker)));
        markerOpt.title(title);
        markerOpt.snippet(info);
        mMap.addMarker(markerOpt);

    }


}
