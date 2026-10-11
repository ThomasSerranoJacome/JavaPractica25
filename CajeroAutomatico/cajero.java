/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.cajero;

/**
 *
 * @author PC
 */
public class CAJERO {

    public static void main(String[] args) {

        Banco banco = new Banco();

        banco.agregarCuenta(
                new CuentaBancaria("Thomas", "1234", 500)
        );

        banco.agregarCuenta(
                new CuentaAhorro("Maria", "2222", 1000)
        );

        banco.agregarCuenta(
                new CuentaCorriente("Carlos", "3333", 200, 300)
        );

        Menu menu = new Menu(banco);
        menu.mostrarMenu();
    }
}
