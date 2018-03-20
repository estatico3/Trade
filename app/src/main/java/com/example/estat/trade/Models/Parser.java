package com.example.estat.trade.Models;

import android.annotation.SuppressLint;
import android.service.carrier.CarrierMessagingService;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import okhttp3.OkHttpClient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by admin on 19.03.18.
 */
public class Parser {

    private Map<Integer,Order> orders;

    public Map<Integer, Order> getOrders() {
        return orders;
    }

    OkHttpClient client = new OkHttpClient();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.vmeste.market")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    CSMONEYService apiService = retrofit.create(CSMONEYService.class);

    public interface ResultCallback {
        void skins(Object response);
    }

    private interface CSMONEYService {
        @GET("/orders/cart")
        Call<Object> getItem();

    }

    private void initializeApi(){
    }

    public void requestSkins(final ResultCallback resultCallback) throws IOException {
        Log.wtf("tag", "Inside Skins");

        Call<Object> getSkins = apiService.getItem();
        Log.wtf("tag", "Inside Skins");
        Log.wtf("tag", "Inside Skins");
        getSkins.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, retrofit2.Response<Object> response) {
                try {
                    Log.wtf("tag", "Inside Skins 1 = " + response.code());
                    Log.wtf("tag", "Inside Skins 1 = " + response.errorBody());

                    if(response.isSuccessful()){
                        if (resultCallback != null) {
                            resultCallback.skins(response.body());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.wtf("tag", "Error 1 = " + t);
            }
        });
    }

    @SuppressLint("NewApi")
    private Map<Integer,Order> parser(String response) throws JSONException {
        JSONObject all = null;
        JSONObject data = null;
        org.json.JSONArray orders = null;
        JSONObject obj = null;

        Payway[] payways = null;
        Distway[] distways = null;
        Item[] items = null;

        Map<String,Payway> mapPayway = new HashMap<>();
        Map<String,Distway> mapDistway = new HashMap<>();
        Map<String,State> mapState= new HashMap<>();

        Map<Integer,Order> mapOrders = new HashMap<>();

        if(response != null){
            all = new JSONObject(response);
        }
        if(all.has("data")){
            data = all.getJSONObject("data");
        }

        if(data.has("payways")){
            mapPayway = (Map<String,Payway>)parseStruct("payways",data);
        }

        if(data.has("distways")){
            mapDistway = (Map<String,Distway>)parseStruct("distways",data);
        }

        if(data.has("states")){
            mapState = (Map<String,State>)parseStruct("states",data);
        }

        if(data.has("orders")){
            orders = (org.json.JSONArray)data.get("orders");
        }

        for (int i = 0; i < orders.length(); i++) {
            Order order = new Order();
            obj = (JSONObject)orders.get(i);
            String orderName = null;

            if(obj.has("name")){
                orderName = obj.getString("name");
                order.setName(orderName);
            }
            if(obj.has("state")){
                String state = obj.getString("state");

                if(mapState.containsKey(state)){
                    order.setState(mapState.get(state));
                }
            }

            if(obj.has("payways")){
                org.json.JSONArray arrayP = ((org.json.JSONArray)obj.get("payways"));
                payways = new Payway[arrayP.length()];

                for (int j = 0; j < arrayP.length(); j++) {
                    String name = (String)arrayP.get(j);

                    Payway payway = null;

                    if (mapPayway.containsKey(name) ){
                        payway = mapPayway.get(name);
                    }

                    payways[j] = payway;
                }

            }
            if(obj.has("distways")){
                org.json.JSONArray arrayD = ((org.json.JSONArray)obj.get("distways"));
                distways = new Distway[arrayD.length()];

                for (int j = 0; j < arrayD.length(); j++) {
                    String name = (String)arrayD.get(j);

                    Distway distway = null;

                    if(mapDistway.containsKey(name)){
                        distway = mapDistway.get(name);
                    }
                    distways[j] = distway;
                }

            }
            if(obj.has("items")){
                org.json.JSONObject arrayI = ((org.json.JSONObject)obj.get("items"));
                items = new Item[arrayI.length()];
                Iterator<String> iter = arrayI.keys();
                int j = 0;

                while(iter.hasNext()) {
                    String key = iter.next();

                    Double price = null;
                    String name = null;
                    Integer qty = null;
                    String urlPhoto = null;


                    obj = (JSONObject) arrayI.get(key);
                    if (obj.has("price")){
                        price = obj.getDouble("price");
                    }
                    if (obj.has("name")){
                        name = obj.getString("name");
                    }
                    if (obj.has("qty")){
                        qty = obj.getInt("qty");
                    }
                    if (obj.has("photo")){
                        urlPhoto = ((JSONObject)obj.get("photo")).getString("url");
                    }
                    Item item = new Item(price,name,qty,urlPhoto);
                    items[j++] = item;
                }
            }
            order.setDistways(distways);
            order.setItems(items);
            order.setPayways(payways);

            if(!mapOrders.containsKey(i))
            mapOrders.put(i,order);
        }

        return mapOrders;
    }

    @SuppressLint("NewApi")
    public Object parseStruct(String keyStruct, JSONObject data) throws JSONException {
        Object object = null;
        JSONObject obj = null;

        Map<String,Payway> mapPayway = null;
        Map<String,Distway> mapDistway = null;
        Map<String,State> mapState= null;

        switch (keyStruct){
            case "payways" :mapPayway = new HashMap<>();break;
            case "distways" :mapDistway = new HashMap<>();break;
            case "states" : mapState = new HashMap<>();break;
        }

        if(data.has(keyStruct)){
            org.json.JSONObject arrayP = ((org.json.JSONObject)data.get(keyStruct));
            Iterator<String> iter = arrayP.keys();

            while (iter.hasNext()){
                String key = iter.next();
                obj = (JSONObject)arrayP.get(key);

                String name = obj.getString("name");
                String note = null;
                int sort = 0;

                if(keyStruct.equals("states")){
                    sort = obj.getInt("sort");
                } else {
                    note = obj.getString("note");
                }

                switch (keyStruct){
                    case "payways" :{
                        if (!mapPayway.containsKey(key))
                        mapPayway.put(key,new Payway(name,note));
                        break;
                    }
                    case "distways" :{
                        if (!mapDistway.containsKey(key))
                        mapDistway.put(key,new Distway(name,note));
                        break;
                    }

                    case "states" : {
                        if (!mapState.containsKey(key))
                        mapState.put(key,new State(name,sort));
                        break;
                    }

                }
            }

            switch (keyStruct){
                case "payways" : object = mapPayway;break;
                case "distways" : object = mapDistway;break;
                case "states" : object = mapState;break;
            }
        }

        return object;
    }
}
