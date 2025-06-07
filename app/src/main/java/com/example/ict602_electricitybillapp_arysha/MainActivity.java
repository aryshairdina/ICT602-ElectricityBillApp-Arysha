package com.example.ict602_electricitybillapp_arysha;


import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Spinner spinnerMonth;
    EditText editUnits, editRebate;
    Button btnCalculate, btnViewList;

    ImageButton btnAbout;
    TextView result;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerMonth = findViewById(R.id.spinnerMonth);
        editUnits = findViewById(R.id.editUnits);
        editRebate = findViewById(R.id.editRebate);
        btnCalculate = findViewById(R.id.btnCalculate);
        btnViewList = findViewById(R.id.btnViewList);
        btnAbout = findViewById(R.id.btnAbout);
        result = findViewById(R.id.textResult);
        dbHelper = new DBHelper(this);

        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        spinnerMonth.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, months));

        btnCalculate.setOnClickListener(v -> {
            String month = spinnerMonth.getSelectedItem().toString();
            String unitsStr = editUnits.getText().toString();
            String rebateStr = editRebate.getText().toString();

            if (unitsStr.isEmpty() || rebateStr.isEmpty()) {
                Toast.makeText(this, "Please enter units and rebate.", Toast.LENGTH_SHORT).show();
                return;
            }

            int units = Integer.parseInt(unitsStr);
            double rebate = Double.parseDouble(rebateStr);
            if (rebate < 0 || rebate > 5) {
                Toast.makeText(this, "Rebate must be 0 to 5%", Toast.LENGTH_SHORT).show();
                return;
            }

            double total = calculateTotal(units);
            double finalCost = total - (total * rebate / 100);
            dbHelper.insertBill(month, units, rebate, total, finalCost);

            result.setText(String.format("YOUR ELECTRICITY BILL:\n\nTotal Charges: RM %.2f\nFinal Cost: RM %.2f", total, finalCost));
        });

        btnViewList.setOnClickListener(v -> startActivity(new Intent(this, BillsList.class)));
        btnAbout.setOnClickListener(v -> startActivity(new Intent(this, AboutPage.class)));
    }

    private double calculateTotal(int units) {
        double total = 0;
        if (units <= 200) {
            total = units * 0.218;
        } else if (units <= 300) {
            total = (200 * 0.218) + ((units - 200) * 0.334);
        } else if (units <= 600) {
            total = (200 * 0.218) + (100 * 0.334) + ((units - 300) * 0.516);
        } else {
            total = (200 * 0.218) + (100 * 0.334) + (300 * 0.516) + ((units - 600) * 0.546);
        }
        return total;
    }

}
