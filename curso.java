/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.reporteacademico;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
/**
 *
 * @author PC
 */
public class Curso {
    private ArrayList<Estudiante> estudiantes;
    
    public Curso(){
        this.estudiantes = new ArrayList<Estudiante>();
    }
    
    public void agregarEstudiante(Estudiante estudiante){
        estudiantes.add(estudiante);
    }
    
    public double calcularPromedioGeneral(){
        double suma = 0;
        
        for(int i=0; i < estudiantes.size();i++){
            suma += estudiantes.get(i).getPromedio();
        }
        
        return suma/estudiantes.size();
    }
    
    public int contarAprobados(){
        int cantidad=0;
        
        for(int i=0; i < estudiantes.size();i++){
            if(estudiantes.get(i).aprobo()){
                cantidad++;
            }
        }
        return cantidad;
    }
    
    public int contarReprobados(){
        int cantidad=0;
        
        for(int i=0; i < estudiantes.size();i++){
            if(!estudiantes.get(i).aprobo()){
                cantidad++;
            }
        }
        return cantidad;
    }
    
     public Estudiante buscarMayorPromedio(){
        Estudiante mayor = estudiantes.get(0);
        
        for(int i = 1; i < estudiantes.size();i++){
            if(estudiantes.get(i).getPromedio() > mayor.getPromedio()){
                mayor = estudiantes.get(i);
            }
        }
        
        return mayor;
    }
    
    public Estudiante buscarMenorPromedio(){
        Estudiante menor = estudiantes.get(0);
        
        for(int i = 1; i < estudiantes.size();i++){
            if(estudiantes.get(i).getPromedio() < menor.getPromedio()){
                menor = estudiantes.get(i);
            }
        }
        
        return menor;
    }
    
    public void guardarReporte(String nombreArchivo){
        
        double promedioGeneral = calcularPromedioGeneral();
        
        int aprobados = contarAprobados();
        int reprobados = contarReprobados();
        
        Estudiante mayor = buscarMayorPromedio();
        Estudiante menor = buscarMenorPromedio();
        
        try{
            PrintWriter reporte = new PrintWriter(nombreArchivo);
            reporte.println("--- REPORTE ACADEMICO ---");
            for(int i = 0; i < estudiantes.size(); i++){
            reporte.println(estudiantes.get(i).getNombre() + " - promedio: " + estudiantes.get(i).getPromedio());
            }
            reporte.println("Promedio general: "+promedioGeneral);
            reporte.println("Cantidad de aprobados: "+aprobados);
            reporte.println("Cantidad de reprobados: "+reprobados);
            reporte.println("Mayor promedio: "+mayor.getNombre());
            reporte.println("Menor promedio: "+menor.getNombre());
            reporte.close();
        }catch (FileNotFoundException e) {
            System.out.println("No se pudo crear el archivo");            
        }
    }
    
    public Estudiante buscarEstudiante(String nombre){
        for(int i = 0; i < estudiantes.size(); i++){
            if(estudiantes.get(i).getNombre().equals(nombre)){
                return estudiantes.get(i);
            }
        }
        return null;
    }
    
    public void modificarNota(String nombreEstudiante, String nombreAsignatura, double nuevaNota){
        Estudiante estudiante = buscarEstudiante(nombreEstudiante);

        if(estudiante == null){
            System.out.println("No existe el estudiante");
            return;
        }

        for(int i = 0; i < estudiante.getAsignaturas().size(); i++){
            if(estudiante.getAsignaturas().get(i).getNombreAsignatura().equals(nombreAsignatura)){
                estudiante.getAsignaturas().get(i).setNotaFinal(nuevaNota);
                estudiante.calcularPromedio();
                return;
            }
        }

        System.out.println("No existe la asignatura");
    }   
}
