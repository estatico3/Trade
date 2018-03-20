package com.example.estat.trade;

import android.app.ListFragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.estat.trade.Models.Order;
import com.example.estat.trade.Models.Parser;
import com.example.estat.trade.Models.Response;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;

/**
 * Created by Estat on 19.03.2018.
 */

public class OrderListFragment extends ListFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
    @Override
    public void onStart() {
        super.onStart();

        Log.wtf("aaa", "skins1 = ");
        try {
            Parser parser = new Parser();
            parser.requestSkins(new Parser.ResultCallback() {
                @Override
                public void skins(Object response) {
                    if (response != null) {
                        Log.wtf("aaa", "Response = " + response);
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
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
}