package com.callejas.cesar.aprendamosastronomia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<appDatosG> datosGenerales = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        agregarDatos();
    }


    public void agregarDatos(){
        appDatosG ingresoDatos;
        ingresoDatos = new appDatosG(getString(R.string.tituloSistemaSol),getString(R.string.sistemaSolInfo),R.drawable.solarsysnew);
        datosGenerales.add(ingresoDatos);

        ingresoDatos = new appDatosG(getString(R.string.tituloSol),getString(R.string.elSolInfo),R.drawable.sunnew);
        datosGenerales.add(ingresoDatos);

        ingresoDatos = new appDatosG(getString(R.string.tituloLuna),getString(R.string.laLunaInfo),R.drawable.lunanew);
        datosGenerales.add(ingresoDatos);

        ingresoDatos= new appDatosG(getString(R.string.tituloEstrellas),getString(R.string.estrellasInfo),R.drawable.estrellasnew);
        datosGenerales.add(ingresoDatos);

        ingresoDatos = new appDatosG(getString(R.string.tituloConstelacion),getString(R.string.constelacionesInfo),R.drawable.constelacionesnew);
        datosGenerales.add(ingresoDatos);

        ingresoDatos = new appDatosG(getString(R.string.tituloNavesEspaciales),getString(R.string.navesEspacialesInfo),R.drawable.spaceshipsnew);
        datosGenerales.add(ingresoDatos);

    }

    public void sistemaSolar(View view){
        Intent intent = new Intent(this,ViewGral.class);
        intent.putExtra("datos",datosGenerales.get(0));
        startActivity(intent);
    }

    public void losPlanetas(View view){
        Intent intent = new Intent(this,View_planetas.class);
        startActivity(intent);
    }

    public void satelitesGrandes(View view){
        Intent intent = new Intent(this,View_satelites.class);
        startActivity(intent);
    }

    public void elSol(View view){
        Intent intent = new Intent(this,ViewGral.class);
        intent.putExtra("datos",datosGenerales.get(1));
        startActivity(intent);
    }

    public void laLuna(View view){
        Intent intent = new Intent(this,ViewGral.class);
        intent.putExtra("datos",datosGenerales.get(2));
        startActivity(intent);
    }

    public void lasEstrellas(View view){
        Intent intent = new Intent(this,ViewGral.class);
        intent.putExtra("datos",datosGenerales.get(3));
        startActivity(intent);
    }

    public void constelaciones(View view){
        Intent intent = new Intent(this,ViewGral.class);
        intent.putExtra("datos",datosGenerales.get(4));
        startActivity(intent);
    }

    public void navesEsp(View view){
        Intent intent = new Intent(this,ViewGral.class);
        intent.putExtra("datos",datosGenerales.get(5));
        startActivity(intent);
    }


    public void galaxias(View view){
        Intent intent = new Intent(this,View_galaxias.class);
        startActivity(intent);
    }

    public void startGamesMenu(View view){
        Intent intent = new Intent(this,Game_mainMenu.class);
        startActivity(intent);
    }

    public void startVideoMenu(View view) {
        Intent intent = new Intent(this,VideoMenu.class);
        startActivity(intent);
    }
}
