package com.example.mybudgetcalculatorapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.mybudgetcalculatorapp.R;

public class Main2Activity extends AppCompatActivity {
    private final static int EXIT_CODE = 100;

    EditText editText5, editText6, editText7, editText8;
    Button btn_calcul2, btn_reset2, btn_flech2;
    Spinner spinner_currency2;
    TextView tv_result_activity_2;
    double result_activity2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btn_calcul2 = findViewById(R.id.btn_calcule2);
        btn_reset2 = findViewById(R.id.btn_reset2);
        btn_flech2 = findViewById(R.id.btnflech2);

        editText5 = findViewById(R.id.editText5);
        editText6 = findViewById(R.id.editText6);
        editText7 = findViewById(R.id.editText7);
        editText8 = findViewById(R.id.editText8);

        spinner_currency2 = findViewById(R.id.spinner_currency2);

        tv_result_activity_2 = findViewById(R.id.tv_reslt_activity2);

        btn_calcul2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double nbr5 = Double.parseDouble(editText5.getText().toString());
                double nbr6 = Double.parseDouble(editText6.getText().toString());
                double nbr7 = Double.parseDouble(editText7.getText().toString());
                double nbr8 = Double.parseDouble(editText8.getText().toString());
               result_activity2 = nbr5 + nbr6 + nbr7 + nbr8;
                if (spinner_currency2.getSelectedItem().toString().equals("MAD"))
                {
                    tv_result_activity_2.setText("Total des dépenses fixes:"+result_activity2+ "MAD");
                }
                if (spinner_currency2.getSelectedItem().toString().equals("USD"))
                {
                    tv_result_activity_2.setText("Total des dépenses fixes:"+result_activity2+ "USD");
                }
                if (spinner_currency2.getSelectedItem().toString().equals("EUR"))
                {
                    tv_result_activity_2.setText("Total des dépenses fixes:"+result_activity2+ "EUR");
                }
                if (spinner_currency2.getSelectedItem().toString().equals("CAD"))
                {
                    tv_result_activity_2.setText("Total des dépenses fixes:"+result_activity2+ "CAD");
                }
            }
        });
        btn_reset2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText5.setText("0");
                editText6.setText("0");
                editText7.setText("0");
                editText8.setText("0");
            }
        });

        btn_flech2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GotoMainActivity3();
            }
        });


    }
    private void GotoMainActivity3() {
        Intent i2 = getIntent();
        Bundle b2= i2.getExtras();
        double Result1 = b2.getDouble("tv_result_activity_1");
        String spennier1_value = b2.getString("spinner_value");
        double value_ed1 = b2.getDouble("value_ed1");
        double value_ed2 = b2.getDouble("value_ed2");
        double value_ed3 = b2.getDouble("value_ed3");
        double value_ed4 = b2.getDouble("value_ed4");

        Intent i = new Intent(Main2Activity.this,Main3Activity.class);
        Bundle b = new Bundle();
        //activity1
        b.putDouble("tv_result_activity_1",Result1);
        b.putString("spinner_value", spennier1_value);
        b.putDouble("value_ed1",value_ed1);
        b.putDouble("value_ed2",value_ed2);
        b.putDouble("value_ed3",value_ed3);
        b.putDouble("value_ed4",value_ed4);
        //avtivity2
        b.putDouble("tv_result_activity_2",result_activity2);
        b.putString("spinner_value2", spinner_currency2.getSelectedItem().toString());
        b.putDouble("value_ed5", Double.parseDouble(editText5.getText().toString()));
        b.putDouble("value_ed6", Double.parseDouble(editText6.getText().toString()));
        b.putDouble("value_ed7", Double.parseDouble(editText7.getText().toString()));
        b.putDouble("value_ed8", Double.parseDouble(editText8.getText().toString()));

        i.putExtras(b);
        startActivityForResult(i,EXIT_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == EXIT_CODE) {

            if (resultCode == RESULT_OK) {
                if (data.getBooleanExtra("EXIT", true)) {
                    finish();
                }
            }
        }
    }
}

