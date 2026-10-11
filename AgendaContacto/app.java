/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.app;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author PC
 */
public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Agenda agenda = new Agenda();
        int opcion;

        // Expansion 20: al iniciar, se cargan los contactos guardados la ultima vez
        if (agenda.cargarDesdeArchivo()) {
            System.out.println("Contactos cargados desde el archivo.");
        }

        do {
            System.out.println("\n========= AGENDA =========");
            System.out.println("1. Registrar contacto");
            System.out.println("2. Buscar contacto");
            System.out.println("3. Listar contactos");
            System.out.println("4. Eliminar contacto");
            System.out.println("5. Ver contactos por grupo");
            System.out.println("6. Guardar en archivo");
            System.out.println("7. Cargar desde archivo");
            System.out.println("8. Salir");
            opcion = leerEntero(scanner, "Seleccione una opcion: ");

            switch (opcion) {
                case 1:
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();

                    System.out.print("Telefono (9 digitos): ");
                    String telefono = scanner.nextLine();

                    System.out.print("Email: ");
                    String email = scanner.nextLine();

                    String grupo = elegirGrupo(scanner);

                    Contacto contacto = new Contacto(nombre, telefono, email, grupo);

                    if (agenda.agregarContacto(contacto)) {
                        System.out.println("Contacto agregado correctamente.");
                    } else {
                        System.out.println("No se pudo agregar el contacto.");
                    }
                    break;

                case 2:
                    System.out.print("Ingrese el nombre (o parte del nombre) a buscar: ");
                    String nombreBuscar = scanner.nextLine();

                    ArrayList<Contacto> resultados = agenda.buscarContacto(nombreBuscar);

                    if (resultados.size() == 0) {
                        System.out.println("No se encontraron contactos.");
                    } else {
                        System.out.println("\nSe encontraron " + resultados.size() + " contacto(s):");

                        for (int i = 0; i < resultados.size(); i++) {
                            System.out.println();
                            resultados.get(i).mostrarInfo();
                        }
                    }
                    break;

                case 3:
                    agenda.mostrarTodos();
                    break;

                case 4:
                    System.out.print("Ingrese el nombre completo a eliminar: ");
                    String nombreEliminar = scanner.nextLine();

                    if (agenda.eliminarContacto(nombreEliminar)) {
                        System.out.println("Contacto eliminado.");
                    } else {
                        System.out.println("Contacto no encontrado.");
                    }
                    break;

                case 5:
                    String grupoElegido = elegirGrupo(scanner);
                    agenda.mostrarContactosPorGrupo(grupoElegido);
                    break;

                case 6:
                    if (agenda.guardarEnArchivo()) {
                        System.out.println("Contactos guardados en el archivo.");
                    } else {
                        System.out.println("No se pudo guardar el archivo.");
                    }
                    break;

                case 7:
                    if (agenda.cargarDesdeArchivo()) {
                        System.out.println("Contactos cargados desde el archivo.");
                    } else {
                        System.out.println("No se encontro el archivo. Primero guarde los contactos.");
                    }
                    break;

                case 8:
                    agenda.guardarEnArchivo();
                    System.out.println("Contactos guardados. Saliendo de la agenda...");
                    break;

                default:
                    System.out.println("Opcion no valida.");
            }

        } while (opcion != 8);

        scanner.close();
    }

    public static String elegirGrupo(Scanner scanner) {
        System.out.println("Grupos: 1. Familia   2. Trabajo   3. Amigos");
        int opcion = leerEntero(scanner, "Seleccione el grupo: ");

        while (opcion < 1 || opcion > 3) {
            System.out.println("Grupo no valido.");
            opcion = leerEntero(scanner, "Seleccione el grupo: ");
        }

        if (opcion == 1) {
            return "Familia";
        } else if (opcion == 2) {
            return "Trabajo";
        } else {
            return "Amigos";
        }
    }

    public static int leerEntero(Scanner scanner, String mensaje) {
        System.out.print(mensaje);

        while (!scanner.hasNextInt()) {
            System.out.println("Ingrese un numero valido.");
            scanner.next();
            System.out.print(mensaje);
        }

        int numero = scanner.nextInt();
        scanner.nextLine();
        return numero;
    }
}
