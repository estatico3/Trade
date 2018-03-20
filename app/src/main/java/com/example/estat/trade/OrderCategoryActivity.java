package com.example.estat.trade;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

/**
 * Created by Estat on 19.03.2018.
 */

public class OrderCategoryActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_cataloge_activity);

    }

    @Override
    protected void onStart() {
        super.onStart();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        OrderListFragment fragment = new OrderListFragment();
        transaction.replace(R.id.container,fragment);
        transaction.addToBackStack("");
        transaction.commit();
    }
}
