/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemanotascurva;
import java.util.Scanner;
import java.util.InputMismatchException;
/**
 *
 * @author PC
 */


public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese la cantidad de alumnos: ");

        while (!scanner.hasNextInt()) {
            System.out.println("Debe ingresar un numero entero.");
            scanner.next();
            System.out.print("Ingrese la cantidad de alumnos: ");
        }

        int n = scanner.nextInt();
        scanner.nextLine();

        if (n <= 0) {
            System.out.println("La cantidad debe ser mayor que cero.");
            scanner.close();
            return;
        }

        Alumno[] alumnos = new Alumno[n];
        double suma = 0;

        for (int i = 0; i < n; i++) {
            System.out.println("\n--- Alumno --- " + (i + 1));

            System.out.print("Nombre: ");
            while (scanner.hasNextInt()) {
                System.out.println("Solo se permiten letras.");
                scanner.next();
                System.out.print("Nombre: ");
            }

            String nombre = scanner.nextLine();
            
            double notaParcial1 = leerNota(scanner, "Nota del parcial 1 (0 a 10): ");
            double notaParcial2 = leerNota(scanner, "Nota del parcial 2 (0 a 10): ");
            double examenFinal = leerNota(scanner, "Nota del examen final (0 a 10): ");            

            scanner.nextLine();

            alumnos[i] = new Alumno(nombre, notaParcial1, notaParcial2, examenFinal);

            suma += alumnos[i].getNotaOriginal();
        }

        double promedio = suma / n;

        System.out.println("\nPromedio del curso: " + promedio);

        if (promedio < 6) {
            System.out.println("El promedio es menor a 6. Elija el tipo de curva:");
            System.out.println("1. Curva porcentual");
            System.out.println("2. Curva segun la nota mas alta");

            int tipoCurva;

            do {
                System.out.print("Opcion (1 o 2): ");

                while (!scanner.hasNextInt()) {
                    System.out.println("Debe ingresar un numero.");
                    scanner.next();
                    System.out.print("Opcion (1 o 2): ");
                }

                tipoCurva = scanner.nextInt();
            } while (tipoCurva != 1 && tipoCurva != 2);

            scanner.nextLine();

            if (tipoCurva == 1) {
                double porcentaje;

            do {
                System.out.print("Porcentaje de la curva (mayor que 0): ");

                while (!scanner.hasNextDouble()) {
                    System.out.println("Debe ingresar un numero valido.");
                    scanner.next();
                    System.out.print("Porcentaje de la curva (mayor que 0): ");
                }

                porcentaje = scanner.nextDouble();
            } while (porcentaje <= 0);

            scanner.nextLine();

            System.out.println("Se aplicara una curva de " + porcentaje + "%.");

            for (Alumno alumno : alumnos) {
            alumno.aplicarCurvaPorcentual(porcentaje);
            }
        } else {
            double mejor = buscarNotaMasAlta(alumnos);
            double curva = 10 - mejor;

            System.out.println("Se aplicara una curva de +" + curva + " puntos.");

            for (Alumno alumno : alumnos) {
            alumno.aplicarCurvaPuntos(curva);
            }
        }
    } else {
        System.out.println("No se necesita aplicar curva.");
    }

        System.out.println("\n========== RESULTADOS ==========");

        for (Alumno alumno : alumnos) {
            System.out.println(
                alumno.getNombre()
                + " | Original: " + alumno.getNotaOriginal()
                + " | Final: " + alumno.getNotaFinal()
                + " | " + (alumno.estaAprobado() ? "Aprobo" : "Reprobo")
            );
        }
        
        
        double sumaFinal = 0;
        int alumnosRecuperados = 0;

        for (Alumno alumno : alumnos) {
            sumaFinal += alumno.getNotaFinal();

            if (alumno.getNotaOriginal() < 6 && alumno.estaAprobado()) {
                alumnosRecuperados++;
            }
        }

        double promedioFinal = sumaFinal / alumnos.length;

        double sumaDiferenciasCuadradas = 0;

        for (Alumno alumno : alumnos) {
            double diferencia = alumno.getNotaFinal() - promedioFinal;
            sumaDiferenciasCuadradas += diferencia * diferencia;
        }

        double desviacionEstandar = Math.sqrt(
            sumaDiferenciasCuadradas / alumnos.length
        );

        System.out.println("\n====== ESTADISTICAS FINALES ======");
        System.out.printf("Promedio final: %.2f%n", promedioFinal);
        System.out.printf("Desviacion estandar: %.2f%n", desviacionEstandar);
        System.out.println("Alumnos recuperados gracias a la curva: " + alumnosRecuperados);


        scanner.close();
    }
    
    public static double buscarNotaMasAlta(Alumno[] alumnos) {
        double mejor = alumnos[0].getNotaOriginal();

        for (int i = 1; i < alumnos.length; i++) {
            if ( alumnos[i].getNotaOriginal() > mejor ) {
            mejor = alumnos[i].getNotaOriginal();
            }
        }

        return mejor;
    }
    
    
    public static double leerNota(Scanner scanner, String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                double nota = scanner.nextDouble();

                if (nota >= 0 && nota <= 10) {
                    return nota;
                }

                System.out.println("La nota debe estar entre 0 y 10.");

            } catch (InputMismatchException e) {
                System.out.println("Debe ingresar una nota valida.");
                scanner.next();
            }
        }
    }

    
}

