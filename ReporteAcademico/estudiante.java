/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.reporteacademico;

import java.util.ArrayList;

public class Estudiante {
    private String nombre;
    private ArrayList<Asignatura> asignaturas;
    private int numeroFaltas;
    private double promedio;
    
    public Estudiante(String pNombre,int numeroFaltas){
        nombre = pNombre;
        this.asignaturas = new ArrayList<Asignatura>();
        this.numeroFaltas = numeroFaltas;
    }
    
    public void agregarAsignatura(Asignatura asignatura){
        asignaturas.add(asignatura);
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public ArrayList<Asignatura> getAsignaturas(){
        return asignaturas;
    }
    
    public double getPromedio(){
        return promedio;
    }
    
    public void calcularPromedio(){
        double suma = 0;
        for(int i = 0; i < asignaturas.size(); i++){
            suma += asignaturas.get(i).getNotaFinal();
        }
        promedio = suma/asignaturas.size();
    }
    
    public boolean aprobo(){
        return promedio >= 6 && numeroFaltas < 10;
    }
}