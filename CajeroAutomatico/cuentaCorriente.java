/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cajero;

/**
 *
 * @author PC
 */
public class CuentaCorriente extends CuentaBancaria {

    private double limiteSobregiro;

    public CuentaCorriente(
            String titular, String pin, double saldo,
            double limiteSobregiro) {

        super(titular, pin, saldo);
        this.limiteSobregiro = limiteSobregiro;
    }

    @Override
    public boolean retirar(double monto) {
        if (monto <= 0 || monto > saldo + limiteSobregiro) {
            return false;
        }

        saldo -= monto;
        registrarMovimiento(
                String.format("Retiro: -$%.2f", monto)
        );

        return true;
    }
}

