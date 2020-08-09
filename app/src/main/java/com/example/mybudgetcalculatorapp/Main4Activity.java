package com.example.mybudgetcalculatorapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mybudgetcalculatorapp.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static java.util.Locale.getDefault;

public class Main4Activity extends AppCompatActivity {
    TextView tv_result1, tv_result2, tv_result3;
    Button btn_createpdf, btn_return;
    Bitmap bmp,bmp2, scaledbmp, scaledbmp2;
    int pageWidth = 1200;
    Date dateObj;
    DateFormat dateFormat;
    double Result1;
    double Result2;
    double Result3;
    double v_ed1,v_ed2,v_ed3,v_ed4,v_ed5,v_ed6,v_ed7,v_ed8,v_ed9,v_ed10,v_ed11,v_ed12;
    String spennier1_value;
    String spennier_value2;
    String spennier_value3;
    double sum;
    double Total;
    double epargne;
    double taux_eparge;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        tv_result1 = findViewById(R.id.tv_rslt1);
        tv_result2 = findViewById(R.id.tv_rslt2);
        tv_result3 = findViewById(R.id.text_view_resultFinal);
        btn_createpdf = findViewById(R.id.btn_createpdf);
        btn_return = findViewById(R.id.btn_return);
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.rsz_reef);
        bmp2 = BitmapFactory.decodeResource(getResources(),R.drawable.rsz_paper);
        scaledbmp = Bitmap.createScaledBitmap(bmp, 1200, 518, false);
        scaledbmp2 = Bitmap.createScaledBitmap(bmp2, 204, 204, false);

        Intent i2 = getIntent(); //cette intention il va contenaire un bundle
        Bundle b2 = i2.getExtras();
        Result1 = b2.getDouble("tv_result_activity_1");
        spennier1_value = b2.getString("spinner_value");
        v_ed1 = b2.getDouble("value_ed1");
        v_ed2 = b2.getDouble("value_ed2");
        v_ed3 = b2.getDouble("value_ed3");
        v_ed4 = b2.getDouble("value_ed4");
        Result2 = b2.getDouble("tv_result_activity_2");
        spennier_value2 = b2.getString("spinner_value2");
        v_ed5 = b2.getDouble("value_ed5");
        v_ed6 = b2.getDouble("value_ed6");
        v_ed7 = b2.getDouble("value_ed7");
        v_ed8 = b2.getDouble("value_ed8");
        Result3 = b2.getDouble("tv_result_activity_3");
        spennier_value3 = b2.getString("spinner_value3");
        v_ed9 = b2.getDouble("value_ed9");
        v_ed10 = b2.getDouble("value_ed10");
        v_ed11 = b2.getDouble("value_ed11");
        v_ed12 = b2.getDouble("value_ed12");

        sum = Result2 + Result3;
        Total= (Result1 - sum);
             /*   String str2 = String.format("%1.2f", Total);
                Total = Double.valueOf(str2);*/
        epargne = ((double)Total*(70.0/100.0));
               /*  String str3 = String.format("%1.2f", epargne);
                 epargne = Double.valueOf(str3);
                */
        taux_eparge = (epargne/Result1)*100.0;
             /*   String str4 = String.format("%1.2f", taux_eparge);
                taux_eparge = Double.valueOf(str4);*/

        tv_result1.setText("Total du revenu mensuel=" + Result1 + spennier1_value);
        tv_result2.setText("Total des dépenses mensuelles =" + (sum) + spennier_value2);
        tv_result3.setText("Différence entre le total du revenu mensuel et le total des dépenses mensuelles = " + Total + spennier_value3);

        //add epg in db

        dbHelper myDB = new dbHelper(Main4Activity.this);
        myDB.addepargne(epargne, taux_eparge, spennier_value3);

        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        btn_createpdf.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                createPdf();
            }
        });
        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main4Activity.this, FirstActivity.class);
                startActivity(intent);
            }
        });
    }



    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void createPdf() {
        dateObj = new Date();
        // create a new document
        PdfDocument document = new PdfDocument();
        // crate a page description
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(1200, 2010, 1).create();
        // start a page
        PdfDocument.Page page = document.startPage(pageInfo);
        Canvas canvas = page.getCanvas();
        Canvas canvas2 = page.getCanvas();
        Paint myPaint = new Paint();
        Paint myPaint2 = new Paint();
        Paint titlePaint = new Paint();

        canvas.drawBitmap(scaledbmp, 0, 0, myPaint);
        canvas2.drawBitmap(scaledbmp2, 520, 300, myPaint2);

        titlePaint.setTextAlign(Paint.Align.CENTER);
        titlePaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        titlePaint.setTextSize(70);
        titlePaint.setColor(Color.WHITE);
        canvas.drawText("Mon budget mensuel", pageWidth / 2, 270, titlePaint);

        canvas.drawText("Date: " + (new SimpleDateFormat("yyyy-MM-dd_hh:mm:ss", getDefault())).format(new Date()), pageWidth-20, 570, myPaint);

        myPaint.setStyle(Paint.Style.STROKE);
        myPaint.setStrokeWidth(2);
        canvas.drawRect(20, 580, pageWidth-20, 660, myPaint);

        myPaint.setTextAlign(Paint.Align.LEFT);
        myPaint.setStyle(Paint.Style.FILL);
        myPaint.setTextSize(25);
        canvas.drawText("Revenu", 50, 630, myPaint);
        canvas.drawText("Montant par mois"+ "("+spennier1_value+")", 900, 630, myPaint);

        canvas.drawLine(580, 590, 580, 640, myPaint);

        canvas.drawText("Revenu d'emploi ", 40, 750, myPaint);
        canvas. drawText(String.valueOf(v_ed1), 900, 750, myPaint);
        myPaint.setTextAlign(Paint.Align.LEFT);

        canvas.drawText("Primes", 40, 800, myPaint);
        canvas. drawText(String.valueOf(v_ed2), 900, 800, myPaint);
        myPaint.setTextAlign(Paint.Align.LEFT);

        canvas.drawText("Travail autonome", 40, 850, myPaint);
        canvas. drawText(String.valueOf(v_ed3), 900, 850, myPaint);
        myPaint.setTextAlign(Paint.Align.LEFT);

        canvas.drawText("Autres", 40, 900, myPaint);
        canvas. drawText(String.valueOf(v_ed4), 900, 900, myPaint);
        myPaint.setTextAlign(Paint.Align.LEFT);

        myPaint.setStyle(Paint.Style.STROKE);
        myPaint.setStrokeWidth(2);
        canvas.drawRect(20, 950, pageWidth-20, 1030, myPaint);
        canvas.drawLine(580, 960, 580, 1010, myPaint);
        myPaint.setTextAlign(Paint.Align.LEFT);
        myPaint.setStyle(Paint.Style.FILL);
        myPaint.setTextSize(25);
        canvas.drawText("Dépenses fixes", 50, 1000, myPaint);
        canvas.drawText("Montant par mois"+ "("+spennier_value2+")", 900, 1000, myPaint);

        canvas.drawText("Loyer ou paiement hypothécaire", 50, 1100, myPaint);
        canvas.drawText(String.valueOf(v_ed5), 900, 1100, myPaint);

        canvas.drawText("Services publics (électricité, eau, chauffage)", 50, 1150, myPaint);
        canvas.drawText(String.valueOf(v_ed6), 900, 1150, myPaint);

        canvas.drawText("Communications (téléphone, Internet, câble)", 50, 1200, myPaint);
        canvas.drawText(String.valueOf(v_ed7), 900, 1200, myPaint);

        canvas.drawText("Autres", 50, 1250, myPaint);
        canvas.drawText(String.valueOf(v_ed8), 900, 1250, myPaint);

        myPaint.setStyle(Paint.Style.STROKE);
        myPaint.setStrokeWidth(2);
        canvas.drawRect(20, 1300, pageWidth-20, 1380, myPaint);
        canvas.drawLine(580, 1310, 580, 1360, myPaint);
        myPaint.setTextAlign(Paint.Align.LEFT);
        myPaint.setStyle(Paint.Style.FILL);
        myPaint.setTextSize(25);

        canvas.drawText("Dépenses variables", 50, 1350, myPaint);
        canvas.drawText("Montant par mois"+ "("+spennier_value3+")", 900, 1350, myPaint);

        canvas.drawText("Épicerie", 50, 1450, myPaint);
        canvas.drawText(String.valueOf(v_ed9), 900, 1450, myPaint);

        canvas.drawText("Soins de santé (soins dentaires, médicaments, lunettes..)", 50, 1500, myPaint);
        canvas.drawText(String.valueOf(v_ed10), 900, 1500, myPaint);

        canvas.drawText("Vêtements et chaussures", 50, 1550, myPaint);
        canvas.drawText(String.valueOf(v_ed11), 900, 1550, myPaint);

        canvas.drawText("Autres dépenses variables", 50, 1600, myPaint);
        canvas.drawText(String.valueOf(v_ed12), 900, 1600, myPaint);

        canvas.drawText("Total du revenu mensuel= " + Result1 + spennier1_value , 50, 1680, myPaint);
        canvas.drawText("Total des dépenses mensuelles =" + (sum) + spennier_value2, 50, 1730, myPaint);
        canvas.drawText("Différence entre le total du revenu mensuel et le total des dépenses mensuelles = " + (Result1 - sum) + spennier_value3 , 50, 1780 , myPaint);
        // finish the page
        document.finishPage(page);
        // write the document content
        String directory_path = Environment.getExternalStorageDirectory().getPath() + "/Mon Budget Calculator App/";
        File file = new File(directory_path);
        if (!file.exists()) {
            file.mkdirs();
        }
        String targetPdf = directory_path + "Budget"
                + (new SimpleDateFormat("yyyy-MM-dd_hh:mm:ss", getDefault())).format(new Date()) + ".pdf";;
        File filePath = new File(targetPdf);
        try {
            document.writeTo(new FileOutputStream(filePath));
            Toast.makeText(this, "Done", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Log.e("main", "error " + e.toString());
            Toast.makeText(this, "Something wrong: " + e.toString(), Toast.LENGTH_LONG).show();
        }
        // close the document
        document.close();
    }
}
