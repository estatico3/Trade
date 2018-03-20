package com.example.estat.trade;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.estat.trade.Models.Order;
import com.example.estat.trade.Models.Parser;
import com.google.gson.Gson;

import org.json.JSONException;

import java.io.IOException;
import java.util.Map;

public class OrderListActivity extends ListActivity {

    Order[] orders = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Orders");
        new OrderGetter().execute();
    }

    private class OrderGetter extends AsyncTask<Void,Void,Order[]> {

        @Override
        protected Order[] doInBackground(Void... voids) {

            Order[] orders = null;
            Log.wtf("aaa", "skins1 = ");

            final Parser parser = new Parser("https://api.vmeste.market/orders/cart");
            String respose = null;
            Map<Integer,Order> orderMap = null;
            try {
                respose = parser.requestSkins();
                orderMap = parser.parser(respose);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            orders = new Order[orderMap.size()];

            for (int i = 0; i < orders.length; i++) {
                orders[i] = orderMap.get(i);
            }
            return orders;
        }

        @Override
        protected void onPostExecute(Order[] orders) {
            super.onPostExecute(orders);
            getListView().setAdapter(new ArrayAdapter<Order>(OrderListActivity.this,android.R.layout.simple_list_item_1,orders));
            OrderListActivity.this.orders = orders;
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Order order = orders[(int)id];
        Gson gson = new Gson();
        String object = gson.toJson(order);
        Intent intent = new Intent(this,OrderActivity.class);
        intent.putExtra("order",object);
        startActivity(intent);
    }


}


            /*parser.requestSkins(new Parser.ResultCallback() {
                @Override
                public void skins(org.json.simple.JSONObject response) {
                    Map<Integer, Order> orderMap = null;
                    if (response != null) {
                        Log.wtf("aaa", "Response = " + response);
                    }
                    try {
                        orderMap = parser.parser(response.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    orders = new Order[orderMap.size()];

                    for (int i = 0; i < orders.length; i++) {
                        orders[i] = orderMap.get(i);
                    }

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }*/




