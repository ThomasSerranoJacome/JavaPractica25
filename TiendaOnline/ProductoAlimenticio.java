/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tiendaonline;

/**
 *
 * @author PC
 */
public class ProductoAlimenticio extends Producto {
    private String fechaCaducidad;

    public ProductoAlimenticio(String nombre, double precio, int stock, String fechaCaducidad) {
        // super(...) llama al constructor del padre. La categoria queda fija en "Alimentos".
        super(nombre, precio, stock, "Alimentos");
        this.fechaCaducidad = fechaCaducidad;
    }

    public String getFechaCaducidad() {
        return fechaCaducidad;
    }

    @Override
    public String getDetalle() {
        return "Caduca: " + fechaCaducidad;
    }
}

