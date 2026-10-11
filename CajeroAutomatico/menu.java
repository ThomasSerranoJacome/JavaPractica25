/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cajero;
import java.util.Scanner;
/**
 *
 * @author PC
 */
public class Menu {

    private Scanner scanner;
    private Banco banco;

    public Menu(Banco banco) {
        this.banco = banco;
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {

        System.out.println("====== CAJERO ELECTRONICO ======");

        System.out.print("Ingrese el nombre del titular: ");
        String titular = scanner.nextLine().trim();

        int intentos = 0;
        CuentaBancaria cuenta = null;

        while (intentos < 3) {

            System.out.print("Ingrese su PIN: ");
            String pin = scanner.nextLine();

            if (banco.validarPin(titular, pin)) {
                cuenta = banco.buscarCuenta(titular);
                break;
            }

            intentos++;
            System.out.println("Titular o PIN incorrecto.");
            System.out.println("Intentos restantes: " + (3 - intentos));
        }

        if (cuenta == null) {
            System.out.println("Acceso bloqueado.");
            scanner.close();
            return;
        }

        System.out.println("Bienvenido, " + cuenta.getTitular());

        int opcion;

        do {
            System.out.println("\n====== MENU DEL CAJERO ======");
            System.out.println("1. Consultar saldo");
            System.out.println("2. Depositar dinero");
            System.out.println("3. Retirar dinero");
            System.out.println("4. Mostrar estadisticas");
            System.out.println("5. Transferir dinero");
            System.out.println("6. Consultar movimientos");
            System.out.println("7. Salir");

            opcion = leerEntero("Seleccione una opcion: ");

            switch (opcion) {

                case 1:
                    System.out.printf(
                            "Saldo actual: $%.2f%n",
                            cuenta.getSaldo()
                    );
                    break;

                case 2:
                    double deposito = leerDouble(
                            "Ingrese el monto del deposito: "
                    );

                    if (deposito > 0) {
                        cuenta.depositar(deposito);

                        System.out.printf(
                                "Nuevo saldo: $%.2f%n",
                                cuenta.getSaldo()
                        );
                    } else {
                        System.out.println(
                                "El monto debe ser mayor que cero."
                        );
                    }
                    break;

                case 3:
                    double retiro = leerDouble(
                            "Ingrese el monto del retiro: "
                    );

                    if (cuenta.retirar(retiro)) {
                        System.out.printf(
                                "Retiro realizado. Saldo: $%.2f%n",
                                cuenta.getSaldo()
                        );
                    } else {
                        System.out.println(
                                "Monto invalido o saldo insuficiente."
                        );
                    }
                    break;

                case 4:
                    cuenta.mostrarEstadisticas();
                    break;

                case 5:
                    System.out.print(
                            "Nombre del titular destino: "
                    );
                    String titularDestino = scanner.nextLine().trim();

                    CuentaBancaria destino =
                            banco.buscarCuenta(titularDestino);

                    if (destino == null) {
                        System.out.println(
                                "No se encontro esa cuenta."
                        );
                        break;
                    }

                    double monto = leerDouble(
                            "Ingrese el monto a transferir: "
                    );

                    if (cuenta.transferir(destino, monto)) {
                        System.out.println(
                                "Transferencia realizada correctamente."
                        );
                    } else {
                        System.out.println(
                                "Transferencia invalida. "
                                + "Verifique el saldo, el monto "
                                + "y la cuenta destino."
                        );
                    }
                    break;

                case 6:
                    cuenta.mostrarMovimientos();
                    break;

                case 7:
                    System.out.println(
                            "Gracias por usar este cajero."
                    );
                    break;

                default:
                    System.out.println(
                            "Seleccione una opcion entre 1 y 7."
                    );
            }

        } while (opcion != 7);

        scanner.close();
    }

    public int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un numero entero.");
            }
        }
    }

    public double leerDouble(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);

                // Permite usar punto o coma decimal.
                String entrada = scanner.nextLine().replace(',', '.');

                return Double.parseDouble(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un numero valido.");
            }
        }
    }
}
