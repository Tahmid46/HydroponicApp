package com.example.tahmid.hydroponicapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.annotation.Annotation;

public class SplashScreen extends AppCompatActivity {

    private ImageView im;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        im=findViewById(R.id.imId);
        tv=findViewById(R.id.txId);

        Animation myanim=AnimationUtils.loadAnimation(this,R.anim.mytransition);
        im.startAnimation(myanim);
        tv.startAnimation(myanim);
        final Intent intent=new Intent(getApplicationContext(),MainActivity.class);

       Thread timer=new Thread(){
           public void run(){
               try{
                   sleep(5000);
               }catch (InterruptedException e){
                   e.printStackTrace();
               }
               finally {
                   startActivity(intent);
                   overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                   finish();
               }
           }
       };
        timer.start();
    }


}
