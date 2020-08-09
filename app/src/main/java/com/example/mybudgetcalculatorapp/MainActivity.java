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

public class MainActivity extends AppCompatActivity {
    private final static int EXIT_CODE = 100;

    EditText editText1, editText2, editText3, editText4;
    Button btn_calcule, btn_reset, btn_flech1;
    Spinner spinner_currency;
    TextView tv_result_activity_1;
    double result_activity1 = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mn);

        btn_calcule = findViewById(R.id.btn_calculer);
        btn_reset = findViewById(R.id.btn_reset);
        btn_flech1 = findViewById(R.id.gotoMainActivity2);

        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        editText4 = findViewById(R.id.editText4);

        spinner_currency = findViewById(R.id.spinner_currency);

        tv_result_activity_1 = findViewById(R.id.tv_reslt_activity1);

        btn_calcule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double nbr1 = Double.parseDouble(editText1.getText().toString());
                double nbr2 = Double.parseDouble(editText2.getText().toString());
                double nbr3 = Double.parseDouble(editText3.getText().toString());
                double nbr4 = Double.parseDouble(editText4.getText().toString());
                result_activity1 = nbr1 + nbr2 + nbr3 + nbr4;
                if (spinner_currency.getSelectedItem().toString().equals("MAD"))
                {

                    tv_result_activity_1.setText("Total du revenu mensuel:"+result_activity1+ "MAD");
                }
                 if (spinner_currency.getSelectedItem().toString().equals("USD"))
                {
                    tv_result_activity_1.setText("Total du revenu mensuel:"+result_activity1+ "USD");
                }
                 if (spinner_currency.getSelectedItem().toString().equals("EUR"))
                {
                    tv_result_activity_1.setText("Total du revenu mensuel:"+result_activity1+ "EUR");
                }
                if (spinner_currency.getSelectedItem().toString().equals("CAD"))
                {
                    tv_result_activity_1.setText("Total du revenu mensuel:"+result_activity1+ "CAD");
                }
            }
        });
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText1.setText("0");
                editText2.setText("0");
                editText3.setText("0");
                editText4.setText("0");
            }
        });

        btn_flech1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GotoMainActivity2();
            }
        });



    }
    private void GotoMainActivity2() {
        Intent i = new Intent(MainActivity.this,Main2Activity.class);
        Bundle b = new Bundle();
        b.putString("spinner_value", spinner_currency.getSelectedItem().toString());
        b.putDouble("tv_result_activity_1",result_activity1);
        b.putDouble("value_ed1", Double.parseDouble(editText1.getText().toString()));
        b.putDouble("value_ed2", Double.parseDouble(editText2.getText().toString()));
        b.putDouble("value_ed3", Double.parseDouble(editText3.getText().toString()));
        b.putDouble("value_ed4", Double.parseDouble(editText4.getText().toString()));
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
