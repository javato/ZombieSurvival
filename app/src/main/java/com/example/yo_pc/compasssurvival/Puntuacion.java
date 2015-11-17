package com.example.yo_pc.compasssurvival;

import java.util.ArrayList;

/**
 * Created by Jvt-WinLaptop on 17/11/2015.
 */
public class Puntuacion {
    String nombre;
    int puntuacion;
    String localizacion;
    ArrayList<Puntuacion> p = new ArrayList<Puntuacion>();
    Puntuacion(int puntuacion, String nombre){
        this.puntuacion = puntuacion;
        this.nombre = nombre;
        localizacion = "Albacete";
    }
    public void putScore(){
        p.add(this);

    }
}
