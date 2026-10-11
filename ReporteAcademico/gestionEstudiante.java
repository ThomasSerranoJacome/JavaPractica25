/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.reporteacademico;

import java.util.Scanner;

public class GestionEstudiante {
    private Curso curso;
    private int cantidad;
    Scanner scanner = new Scanner(System.in);
    
    public void iniciar(){       
        pedirCantidadEstudiantes();
        registrarEstudiantes();
        modificarNotaEstudiante();
        curso.guardarReporte("estadisticas.txt");
        mostrarEstadisticas();
        scanner.close();
    }
    
    public void pedirCantidadEstudiantes(){
        System.out.println("Ingrese la cantidad es estudiantes:");
        cantidad = scanner.nextInt();
        scanner.nextLine();
        
        curso = new Curso();
    }
    
    public void registrarEstudiantes(){
        for(int i = 0; i<cantidad;i++){
            System.out.println("\n--- Estudiante "+(i+1)+" ---");
            curso.agregarEstudiante(crearEstudiante());
        }
    }
    
    public Estudiante crearEstudiante(){
        System.out.println("Nombre: ");
        String nombre = scanner.nextLine();
        
        System.out.println("Digite el numero de faltas");
        int faltas = scanner.nextInt();
        scanner.nextLine();

        Estudiante estudiante = new Estudiante(nombre, faltas);
        pedirAsignaturas(estudiante);
        estudiante.calcularPromedio();

        return estudiante;
    }
    
    public void pedirAsignaturas(Estudiante estudiante){
        System.out.println("Cantidad de asignaturas: ");
        int cantidadAsignaturas = scanner.nextInt();
        scanner.nextLine();

        for(int i = 0; i < cantidadAsignaturas; i++){
            System.out.println("Nombre de la asignatura " + (i+1) + ": ");
            String nombreAsignatura = scanner.nextLine();

            System.out.println("Nota final: "); 
            double nota = scanner.nextDouble();
            scanner.nextLine();

            Asignatura asignatura = new Asignatura(nombreAsignatura, nota);
            estudiante.agregarAsignatura(asignatura);
        }
    }       
    
    
    public void mostrarEstadisticas(){
        double promedioGeneral= curso.calcularPromedioGeneral();
        
        int aprobados = curso.contarAprobados();
        int reprobados = curso.contarReprobados();
        
        Estudiante mayor = curso.buscarMayorPromedio();
        Estudiante menor = curso.buscarMenorPromedio();
        
        System.out.println("=====================================");
        System.out.println("      ESTADISTICAS DEL GRUPO         ");
        System.out.println("=====================================");
        
        System.out.println("Promedio general: "+promedioGeneral);
        System.out.println("Cantidad de aprobados: "+aprobados);
        System.out.println("Cantidad de reprobados: "+reprobados);
        System.out.println("Mayor promedio: "+mayor.getNombre());
        System.out.println("Menor promedio: "+menor.getNombre());
    }
    
    public void modificarNotaEstudiante(){
        System.out.println("Nombre del estudiante: ");
        String nombreEstudiante = scanner.nextLine();

        System.out.println("Nombre de la asignatura: ");
        String nombreAsignatura = scanner.nextLine();

        System.out.println("Nueva nota: ");
        double nuevaNota = scanner.nextDouble();
        scanner.nextLine();

        curso.modificarNota(nombreEstudiante, nombreAsignatura, nuevaNota);
    }
}