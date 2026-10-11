/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cajero;

import java.util.ArrayList;
/**
 *
 * @author PC
 */
public class Banco {

    private ArrayList<CuentaBancaria> cuentas;

    public Banco() {
        cuentas = new ArrayList<>();
    }

    public void agregarCuenta(CuentaBancaria cuenta) {
        cuentas.add(cuenta);
    }

    public CuentaBancaria buscarCuenta(String titular) {
        for (CuentaBancaria cuenta : cuentas) {
            if (cuenta.getTitular().equalsIgnoreCase(titular)) {
                return cuenta;
            }
        }

        return null;
    }

    public boolean validarPin(String titular, String pin) {
        CuentaBancaria cuenta = buscarCuenta(titular);

        return cuenta != null && cuenta.getPin().equals(pin);
    }
}
