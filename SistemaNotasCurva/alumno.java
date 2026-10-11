/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemanotascurva;

/**
 *
 * @author PC
 */
public class Alumno {
    private String nombre;
    private double notaOriginal;
    private double notaFinal;
    private double notaParcial1;
    private double notaParcial2;
    private double examenFinal;

    public Alumno(String nombre, double notaParcial1, double notaParcial2, double examenFinal) {
        this.nombre = nombre;
        this.notaParcial1 = notaParcial1;
        this.notaParcial2 = notaParcial2;
        this.examenFinal = examenFinal;
        this.notaOriginal = calcularNotaOriginal();
        this.notaFinal = notaOriginal;
    } 
    
    public double calcularNotaOriginal() {
        return (notaParcial1 * 0.30)
             + (notaParcial2 * 0.30)
             + (examenFinal * 0.40);
    }

    public void aplicarCurvaPorcentual(double porcentaje) {
        double aumento = notaOriginal * porcentaje / 100;
        
        notaFinal = notaOriginal + aumento;

        if (notaFinal > 10) {
            notaFinal = 10;
        }
    }
    
    public void aplicarCurvaPuntos(double puntos){
        notaFinal = notaOriginal + puntos;

        if (notaFinal > 10) {
        notaFinal = 10;
        }
    }

    public boolean estaAprobado() {
        return notaFinal >= 6;
    }

    public String getNombre() {
        return nombre;
    }

    public double getNotaOriginal() {
        return notaOriginal;
    }

    public double getNotaFinal() {
        return notaFinal;
    }
}

