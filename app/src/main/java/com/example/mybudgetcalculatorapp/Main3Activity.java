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

public class Main3Activity extends AppCompatActivity {
    private final static int EXIT_CODE = 100;

    EditText editText9, editText10, editText11, editText12;
    Button btn_calcul3, btn_reset3, btn_flech3;
    Spinner spinner_currency3;
    TextView tv_result_activity_3;
    double result_activity3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        btn_calcul3 = findViewById(R.id.btn_calcule3);
        btn_reset3= findViewById(R.id.btn_reset3);
        btn_flech3 = findViewById(R.id.btnflech3);

        editText9 = findViewById(R.id.editText9);
        editText10 = findViewById(R.id.editText10);
        editText11 = findViewById(R.id.editText11);
        editText12 = findViewById(R.id.editText12);

        spinner_currency3 = findViewById(R.id.spinner_currency3);

        tv_result_activity_3 = findViewById(R.id.tv_reslt_activity3);

        btn_calcul3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double nbr9 =  Double.parseDouble(editText9.getText().toString());
                double nbr10 = Double.parseDouble(editText10.getText().toString());
                double nbr11 = Double.parseDouble(editText11.getText().toString());
                double nbr12 = Double.parseDouble(editText12.getText().toString());
               result_activity3 = nbr9 + nbr10 + nbr11 + nbr12;
                if (spinner_currency3.getSelectedItem().toString().equals("MAD"))
                {
                    tv_result_activity_3.setText("Total des dépenses variables:"+result_activity3+ "MAD");
                }
                if (spinner_currency3.getSelectedItem().toString().equals("USD"))
                {
                    tv_result_activity_3.setText("Total des dépenses variables:"+result_activity3+ "USD");
                }
                if (spinner_currency3.getSelectedItem().toString().equals("EUR"))
                {
                    tv_result_activity_3.setText("Total des dépenses variables:"+result_activity3+ "EUR");
                }
                if (spinner_currency3.getSelectedItem().toString().equals("CAD"))
                {
                    tv_result_activity_3.setText("Total des dépenses variables:"+result_activity3+ "CAD");
                }
            }
        });
        btn_reset3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText9.setText("0");
                editText10.setText("0");
                editText11.setText("0");
                editText12.setText("0");
            }
        });

        btn_flech3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GotoMainActivity4();
            }
        });
    }

    private void GotoMainActivity4() {

        Intent i2 = getIntent();
        Bundle b2= i2.getExtras();
        double Result1 = b2.getDouble("tv_result_activity_1");
        String spennier1_value = b2.getString("spinner_value");
        double value_ed1 = b2.getDouble("value_ed1");
        double value_ed2 = b2.getDouble("value_ed2");
        double value_ed3 = b2.getDouble("value_ed3");
        double value_ed4 = b2.getDouble("value_ed4");
        double Result2 = b2.getDouble("tv_result_activity_2");
        String spennier_value2 = b2.getString("spinner_value2");
        double value_ed5 = b2.getDouble("value_ed5");
        double value_ed6 = b2.getDouble("value_ed6");
        double value_ed7 = b2.getDouble("value_ed7");
        double value_ed8 = b2.getDouble("value_ed8");

        Intent i = new Intent(Main3Activity.this,Main4Activity.class);
        Bundle b = new Bundle();
        //activity1
        b.putDouble("tv_result_activity_1",Result1);
        b.putString("spinner_value", spennier1_value);
        b.putDouble("value_ed1",value_ed1);
        b.putDouble("value_ed2",value_ed2);
        b.putDouble("value_ed3",value_ed3);
        b.putDouble("value_ed4",value_ed4);
        //activity2
        b.putDouble("tv_result_activity_2",Result2);
        b.putString("spinner_value2", spennier_value2);
        b.putDouble("value_ed5",value_ed5);
        b.putDouble("value_ed6",value_ed6);
        b.putDouble("value_ed7",value_ed7);
        b.putDouble("value_ed8",value_ed8);
        //activity3
        b.putDouble("tv_result_activity_3",result_activity3);
        b.putString("spinner_value3", spinner_currency3.getSelectedItem().toString());
        b.putDouble("value_ed9", Double.parseDouble(editText9.getText().toString()));
        b.putDouble("value_ed10", Double.parseDouble(editText10.getText().toString()));
        b.putDouble("value_ed11", Double.parseDouble(editText11.getText().toString()));
        b.putDouble("value_ed12", Double.parseDouble(editText12.getText().toString()));

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
