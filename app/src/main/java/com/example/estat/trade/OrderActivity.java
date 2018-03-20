package com.example.estat.trade;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.estat.trade.Models.Distway;
import com.example.estat.trade.Models.Item;
import com.example.estat.trade.Models.Order;
import com.example.estat.trade.Models.Payway;
import com.google.gson.Gson;

import java.util.ArrayList;

public class OrderActivity extends Activity implements View.OnClickListener
{

    TextView name = null;
    ListView items = null;
    ItemAdapter adapter = null;
    Button paway_but= null;
    Button distway_but= null;
    RadioButton[] paywayButtons = null;
    RadioButton[] distwayButtons = null;
    Order order = null;
    TextView paywayTitle = null;
    TextView distwayTitle = null;
    RadioGroup paywayGroup = null;
    RadioGroup distwayGroup = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        String jsonOrder = getIntent().getStringExtra("order");
        Gson gson = new Gson();
        order = gson.fromJson(jsonOrder,Order.class);

        name = (TextView)findViewById(R.id.name);
        name.setText(order.getName());

        items = findViewById(R.id.items);

        ArrayList<Item> itemsList = new ArrayList<>();

        double totalCost = 0;

        for (int i = 0;i<order.getItems().length;i++){
            totalCost+=order.getItems()[i].getPrice();
            itemsList.add(order.getItems()[i]);
        }

        adapter = new ItemAdapter(this,itemsList);

        items.setAdapter(adapter);
        paywayTitle = findViewById(R.id.payway_text);
        paywayTitle.setText("К оплате : " + totalCost+"руб.");
        paywayGroup = findViewById(R.id.payways);
        distwayGroup = findViewById(R.id.distways);

        paywayButtons = new RadioButton[order.getPayways().length];

        int i = 0;
        for(Payway way: order.getPayways()) {
            paywayButtons[i] = new RadioButton(this);
            paywayButtons[i].setText(way.getName());
            paywayButtons[i].setId(i);
            paywayGroup.addView(paywayButtons[i++]);
        }

        paywayGroup = findViewById(R.id.payways);

        distwayButtons = new RadioButton[order.getDistways().length];
        i = 0;
        for(Distway way: order.getDistways()) {
            distwayButtons[i] = new RadioButton(this);
            distwayButtons[i].setText(way.getName());
            distwayButtons[i].setId(i);
            distwayGroup.addView(distwayButtons[i++]);
        }

        paway_but = findViewById(R.id.payway_but);
        paway_but.setOnClickListener(this);

        distway_but = findViewById(R.id.distway_but);
        distway_but.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Intent inten = null;
        Gson gson = new Gson();
        switch (v.getId()){
            case R.id.payway_but:
                if(paywayGroup.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(this, "Выберите способ оплаты.", Toast.LENGTH_SHORT).show();
                    break;
                }
                Payway payway = order.getPayways()[paywayGroup.getCheckedRadioButtonId()];
                String paywayObj = gson.toJson(payway);
                inten = new Intent(this,PaywayActivity.class);
                inten.putExtra("payway",paywayObj);
                startActivity(inten);
                break;
            case R.id.distway_but:
                if(distwayGroup.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(this, "Выберите пункт выдачи.", Toast.LENGTH_SHORT).show();
                    break;
                }
                Distway distway = order.getDistways()[distwayGroup.getCheckedRadioButtonId()];
                String distwayObj = gson.toJson(distway);
                inten = new Intent(this,DistwayActivity.class);
                inten.putExtra("distway",distwayObj);
                startActivity(inten);
                break;
        }
    }
}
