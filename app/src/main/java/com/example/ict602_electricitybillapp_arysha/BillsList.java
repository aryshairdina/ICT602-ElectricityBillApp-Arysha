package com.example.ict602_electricitybillapp_arysha;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class BillsList extends AppCompatActivity {

    ListView listView;
    DBHelper dbHelper;
    ArrayList<String> list;
    ArrayList<BillsModel> bills;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bills_list);

        listView = findViewById(R.id.listView);
        dbHelper = new DBHelper(this);
        list = new ArrayList<>();
        bills = new ArrayList<>();

        Cursor c = dbHelper.getAllBills();
        while (c.moveToNext()) {
            String month = c.getString(1);
            int units = c.getInt(2);
            double rebate = c.getDouble(3);
            double total = c.getDouble(4);
            double finalCost = c.getDouble(5);

            list.add(month + " - RM " + String.format("%.2f", finalCost));
            bills.add(new BillsModel(month, units, rebate, total, finalCost));
        }

        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list));
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(this, BillDetails.class);
            intent.putExtra("bill", bills.get(position));
            startActivity(intent);
        });
    }
}
