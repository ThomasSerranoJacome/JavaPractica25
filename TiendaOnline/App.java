/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tiendaonline;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author PC
 */
public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Inventario inventario = crearInventario();


        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        clientes.add(new Cliente("Ana Gomez", "ana@correo.com"));
        clientes.add(new Cliente("Luis Perez", "luis@correo.com"));

        Cliente cliente = iniciarSesion(scanner, clientes);

        Carrito carrito = new Carrito(cliente, inventario, 20);
        int opcion;

        do {
            System.out.println("\n===== TIENDA ONLINE =====");
            System.out.println("1. Ver catalogo");
            System.out.println("2. Comprar producto");
            System.out.println("3. Ver carrito");
            System.out.println("4. Salir");
            opcion = leerEntero(scanner, "Seleccione una opcion: ");

            switch (opcion) {
                case 1:
                    mostrarCatalogo(inventario.getProductos());
                    break;

                case 2:

                    ArrayList<Producto> lista = elegirCategoria(scanner, inventario);
                    mostrarCatalogo(lista);

                    int indice = leerEntero(scanner, "Seleccione el producto (1-" + lista.size() + "): ") - 1;

                    if (indice < 0 || indice >= lista.size()) {
                        System.out.println("Producto no valido.");
                        break;
                    }

                    int cantidad = leerEntero(scanner, "Ingrese la cantidad: ");

                    if (carrito.agregarProducto(lista.get(indice), cantidad)) {
                        System.out.println("Producto agregado correctamente.");
                    } else {
                        System.out.println("No se pudo agregar. Revise la cantidad y el stock.");
                    }
                    break;

                case 3:
                    carrito.mostrarFactura();
                    break;

                case 4:
                    System.out.println("Gracias por comprar, " + cliente.getNombre() + ".");
                    break;

                default:
                    System.out.println("Opcion no valida.");
            }

        } while (opcion != 4);

        scanner.close();
    }

    public static Inventario crearInventario() {
        Inventario inventario = new Inventario();

        inventario.agregarProducto(new ProductoElectronico("Teclado", 80000, 10, 12));
        inventario.agregarProducto(new ProductoElectronico("Mouse", 50000, 15, 6));
        inventario.agregarProducto(new ProductoElectronico("Audifonos", 120000, 8, 12));
        inventario.agregarProducto(new ProductoAlimenticio("Galletas", 3000, 50, "2027-03-15"));
        inventario.agregarProducto(new ProductoAlimenticio("Jugo", 4500, 30, "2026-12-01"));
        inventario.agregarProducto(new Producto("Cuaderno", 7000, 40, "Papeleria"));

        return inventario;
    }

    public static Cliente iniciarSesion(Scanner scanner, ArrayList<Cliente> clientes) {
        System.out.println("===== INICIO DE SESION =====");
        System.out.print("Ingrese su email: ");
        String email = scanner.nextLine();

        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getEmail().equals(email)) {
                System.out.println("Bienvenido, " + clientes.get(i).getNombre() + ".");
                return clientes.get(i);
            }
        }

        System.out.println("Email no registrado. Vamos a crear su cuenta.");
        System.out.print("Ingrese su nombre: ");
        String nombre = scanner.nextLine();

        Cliente nuevo = new Cliente(nombre, email);
        clientes.add(nuevo);
        System.out.println("Cuenta creada. Bienvenido, " + nombre + ".");
        return nuevo;
    }

    public static ArrayList<Producto> elegirCategoria(Scanner scanner, Inventario inventario) {
        ArrayList<String> categorias = inventario.obtenerCategorias();

        System.out.println("\n===== CATEGORIAS =====");
        System.out.println("0. Todas");
        for (int i = 0; i < categorias.size(); i++) {
            System.out.println((i + 1) + ". " + categorias.get(i));
        }

        int opcion = leerEntero(scanner, "Seleccione una categoria: ");

        while (opcion < 0 || opcion > categorias.size()) {
            System.out.println("Categoria no valida.");
            opcion = leerEntero(scanner, "Seleccione una categoria: ");
        }

        if (opcion == 0) {
            return inventario.getProductos();
        }

        return inventario.filtrarPorCategoria(categorias.get(opcion - 1));
    }

    public static void mostrarCatalogo(ArrayList<Producto> lista) {
        System.out.println("\n========== CATALOGO ==========");

        for (int i = 0; i < lista.size(); i++) {
            Producto p = lista.get(i);

            String linea = (i + 1) + ". " + p.getNombre()
                    + " - $" + p.getPrecio()
                    + " | Stock: " + p.getStock()
                    + " | " + p.getCategoria();

            if (!p.getDetalle().equals("")) {
                linea += " | " + p.getDetalle();
            }

            System.out.println(linea);
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
