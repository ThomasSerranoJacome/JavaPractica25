/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cajero;

/**
 *
 * @author PC
 */
public class CuentaAhorro extends CuentaBancaria {

    private double interes = 0.01;

    public CuentaAhorro(String titular, String pin, double saldo) {
        super(titular, pin, saldo);
    }

    @Override
    public void depositar(double monto) {
        if (monto <= 0) {
            System.out.println("El monto debe ser mayor que cero.");
            return;
        }

        super.depositar(monto);

        double ganancia = monto * interes;
        saldo += ganancia;

        System.out.printf("Interes generado: $%.2f%n", ganancia);
    }
}
