package com.callejas.cesar.aprendamosastronomia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class VideoMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_menu);
    }

    public void videoTrigger1(View view) {
        Intent i = new Intent(this,VideoActivity.class);
        i.putExtra("video","WZZvTo21smA");// https://www.youtube.com/watch?v=WZZvTo21smA
        startActivity(i);
    }

    public void videoTrigger2(View view) {
        Intent i = new Intent(this,VideoActivity.class);
        i.putExtra("video","Ys-NM2WuDAE"); //https://www.youtube.com/watch?v=Ys-NM2WuDAE
        startActivity(i);
    }

    public void videoTrigger3(View view) {
        Intent i = new Intent(this,VideoActivity.class);
        i.putExtra("video","UymxMp7j7Ps");    //https://www.youtube.com/watch?v=UymxMp7j7Ps
        startActivity(i);
    }

    public void videoTrigger4(View view) {
        Intent i = new Intent(this,VideoActivity.class);
        i.putExtra("video","ma0kKMc9PbE");   //https://www.youtube.com/watch?v=ma0kKMc9PbE
        startActivity(i);
    }






}
