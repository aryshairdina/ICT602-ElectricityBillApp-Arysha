package com.example.ict602_electricitybillapp_arysha;


import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class BillDetails extends AppCompatActivity {

    TextView detailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_details);

        detailText = findViewById(R.id.detailText);
        BillsModel bill = (BillsModel) getIntent().getSerializableExtra("bill");

        String info = "Month: " + bill.month +
                "\nUnits: " + bill.units + " kWh" +
                "\nTotal Charges: RM " + String.format("%.2f", bill.total) +
                "\nRebate: " + bill.rebate + "%" +
                "\nFinal Cost: RM " + String.format("%.2f", bill.finalCost);

        detailText.setText(info);
    }
}
