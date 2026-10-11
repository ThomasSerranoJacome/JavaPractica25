/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cajero;

/**
 *
 * @author PC
 */
public class CuentaBancaria extends Cuenta {

    private int cantidadRetiros;
    private int cantidadDepositos;
    private double totalRetirado;
    private double totalDepositado;

    public CuentaBancaria(String titular, String pin, double saldo) {
        super(titular, pin, saldo);

        cantidadRetiros = 0;
        cantidadDepositos = 0;
        totalRetirado = 0;
        totalDepositado = 0;
    }

    @Override
    public void depositar(double monto) {
        if (monto <= 0) {
            System.out.println("El monto debe ser mayor que cero.");
            return;
        }

        saldo += monto;
        cantidadDepositos++;
        totalDepositado += monto;

        registrarMovimiento(String.format("Deposito: +$%.2f", monto));
    }

    @Override
    public boolean retirar(double monto) {
        if (monto <= 0 || monto > saldo) {
            return false;
        }

        saldo -= monto;
        cantidadRetiros++;
        totalRetirado += monto;

        registrarMovimiento(String.format("Retiro: -$%.2f", monto));

        return true;
    }  
    
    public boolean transferir(CuentaBancaria cuentaDestino, double monto) {
        if (cuentaDestino == null || cuentaDestino == this
                || monto <= 0 || monto > saldo) {
            return false;
        }

        if (!retirar(monto)) {
            return false;
        }

        cuentaDestino.depositar(monto);

        return true;
    }


    public void mostrarEstadisticas() {
        System.out.println("\n=== ESTADISTICAS ===");
        System.out.println("Cantidad de depositos: " + cantidadDepositos);
        System.out.println("Cantidad de retiros: " + cantidadRetiros);
        System.out.printf("Total depositado: $%.2f%n", totalDepositado);
        System.out.printf("Total retirado: $%.2f%n", totalRetirado);
        System.out.printf("Saldo actual: $%.2f%n", saldo);
    }
}
