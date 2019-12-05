package com.jay.place;

import android.content.Intent;
import android.location.Address;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.schibstedspain.leku.LocationPickerActivity;

import static com.schibstedspain.leku.LocationPickerActivityKt.ADDRESS;
import static com.schibstedspain.leku.LocationPickerActivityKt.LATITUDE;
import static com.schibstedspain.leku.LocationPickerActivityKt.LOCATION_ADDRESS;
import static com.schibstedspain.leku.LocationPickerActivityKt.LONGITUDE;
import static com.schibstedspain.leku.LocationPickerActivityKt.TIME_ZONE_DISPLAY_NAME;
import static com.schibstedspain.leku.LocationPickerActivityKt.TIME_ZONE_ID;
import static com.schibstedspain.leku.LocationPickerActivityKt.TRANSITION_BUNDLE;
import static com.schibstedspain.leku.LocationPickerActivityKt.ZIPCODE;


public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    private static final int MAP_BUTTON_REQUEST_CODE = 101;
    TextView tvResult;
    public static final String API_KEY = "AIzaSyDnZBEWmgvqLbnxvRDebxeyI8bz2CTmOjQ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = findViewById(R.id.tvResult);
    }

    public void onClick(View view){
        Intent locationPickerIntent = new LocationPickerActivity.Builder()
                .withLocation(41.4036299, 2.1743558)
                .withGeolocApiKey(API_KEY)
                .shouldReturnOkOnBackPressed()
//                .withStreetHidden()
//                .withCityHidden()
                .withZipCodeHidden()
                .withSatelliteViewHidden()
                .withGooglePlacesEnabled()
                .withVoiceSearchHidden()
                .build(this);

        startActivityForResult(locationPickerIntent, MAP_BUTTON_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null){
            double latitude = data.getDoubleExtra(LATITUDE, 0.0);
            double longitude = data.getDoubleExtra(LONGITUDE, 0.0);
            String address = data.getStringExtra(LOCATION_ADDRESS);
            Address fullAddress = data.getParcelableExtra(ADDRESS);
            String addressJson = "";
            if (fullAddress != null) {
                addressJson = new Gson().toJson(fullAddress);
            }

            tvResult.setText("latitude:"+latitude+"|longitude:"+longitude+"|address:"+address+"|addressJson="+addressJson);
        }
    }
}
