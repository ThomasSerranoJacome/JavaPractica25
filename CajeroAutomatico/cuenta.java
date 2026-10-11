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
public abstract class Cuenta {

    protected String titular;
    protected String pin;
    protected double saldo;

    protected ArrayList<String> movimientos;

    public Cuenta(String titular, String pin, double saldo) {
        this.titular = titular;
        this.pin = pin;
        this.saldo = saldo;
        this.movimientos = new ArrayList<>();
    }

    public String getTitular() {
        return titular;
    }

    public String getPin() {
        return pin;
    }

    public double getSaldo() {
        return saldo;
    }

    public abstract void depositar(double monto);

    public abstract boolean retirar(double monto);

    protected void registrarMovimiento(String movimiento) {
        if (movimientos.size() == 5) {
            movimientos.remove(0);
        }

        movimientos.add(movimiento);
    }

    public void mostrarMovimientos() {
        System.out.println("\n=== ULTIMOS MOVIMIENTOS ===");

        if (movimientos.isEmpty()) {
            System.out.println("No hay movimientos registrados.");
            return;
        }

        for (String movimiento : movimientos) {
            System.out.println(movimiento);
        }
    }
}

