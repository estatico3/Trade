package com.example.estat.trade;

import android.app.ListFragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;


import com.example.estat.trade.Models.Order;
import com.example.estat.trade.Models.Parser;
import com.example.estat.trade.Models.Response;

import org.json.JSONException;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;

/**
 * Created by Estat on 19.03.2018.
 */

public class OrderListFragment extends ListFragment {


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);

        /*Log.wtf("aaa", "skins1 = ");
        try {
            final Parser parser = new Parser();
            parser.requestSkins(new Parser.ResultCallback() {
                @Override
                public void skins(org.json.simple.JSONObject response) {
                    Map<Integer,Order> orderMap = null;
                    if (response != null) {
                        Log.wtf("aaa", "Response = " + response);
                    }
                    try {
                        orderMap = parser.parser(response.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    Order[] orders = new Order[orderMap.size()];

                    for (int i = 0; i < orders.length; i++) {
                        orders[i] = orderMap.get(i);
                    }
                    getListView().setAdapter(new ArrayAdapter<Order>(inflater.getContext(),android.R.layout.simple_list_item_1,orders));
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }*/


        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}

  /*  public class OrderGetter extends AsyncTask<Void,Void,Order[]>{

        @Override
        protected Order[] doInBackground(Void... voids) {
            Parser parser = new Parser();
            try {
                parser.requestSkins();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Map<Integer,Order> orderMap = null;
            try {
                orderMap = parser.requestSkins();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Order[] orders = new Order[orderMap.size()];

            for (int i = 0; i < orders.length; i++) {
                orders[i] = orderMap.get(i);
            }

            return orders;
        }
    }
*/
