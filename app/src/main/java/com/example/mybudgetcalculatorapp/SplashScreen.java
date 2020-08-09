package com.example.mybudgetcalculatorapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import static java.lang.Thread.sleep;
import com.example.mybudgetcalculatorapp.R;

public class SplashScreen extends AppCompatActivity {

    private final static int EXIT_CODE = 100;

    TextView txtSplashText;
    //ImageView imgViewLogo;

    private static int SPLASH_TIME_OUT = 5000;
    //Hooks
    View first,second,third,fourth,fifth,sixth;
    TextView a, slogan;
    //Animations
    Animation topAnimantion,bottomAnimation,middleAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

     /*   txtSplashText = findViewById(R.id.textviewLogoText);
       // imgViewLogo = findViewById(R.id.imgviewLogo);


        Animation animation = AnimationUtils.loadAnimation(this,R.anim.trasnsition);
       // imgViewLogo.setAnimation(animation);
        txtSplashText.setAnimation(animation);*/

        //Hooks
        first = findViewById(R.id.first_line);
        second = findViewById(R.id.second_line);
        third = findViewById(R.id.third_line);
        fourth = findViewById(R.id.fourth_line);
        fifth = findViewById(R.id.fifth_line);
        sixth = findViewById(R.id.sixth_line);
        a = findViewById(R.id.a);

        //Animation Calls
        topAnimantion = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        middleAnimation = AnimationUtils.loadAnimation(this, R.anim.middle_animation);

        //Setting Animations to the elements of Splash
        first.setAnimation(topAnimantion);
        second.setAnimation(topAnimantion);
        third.setAnimation(topAnimantion);
        fourth.setAnimation(topAnimantion);
        fifth.setAnimation(topAnimantion);
        sixth.setAnimation(topAnimantion);
        a.setAnimation(middleAnimation);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{

                    sleep(5000);


                }catch (Exception e){

                    e.printStackTrace();
                }finally {


                    GotoPlayActivity();
                }
            }
        });
        thread.start();
    }

    private void GotoPlayActivity() {

        startActivityForResult( new Intent(SplashScreen.this,FirstActivity.class),EXIT_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == EXIT_CODE){

            if (resultCode == RESULT_OK){
                if (data.getBooleanExtra("EXIT",true)){
                    finish();
                }
            }
        }


    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("BUGBUG","onStop() in SplashActivity");
        finish();

    }
}
