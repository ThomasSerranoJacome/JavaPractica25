/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tiendaonline;

/**
 *
 * @author PC
 */
public class Carrito {
    private Cliente cliente;          
    private Inventario inventario;   
    private Producto[] productos;
    private int[] cantidades;
    private int cantidadItems;

    // Expansion 12: reglas del descuento
    private double limiteDescuento = 200000;
    private double porcentajeDescuento = 0.10;

    public Carrito(Cliente cliente, Inventario inventario, int capacidad) {
        this.cliente = cliente;
        this.inventario = inventario;
        productos = new Producto[capacidad];
        cantidades = new int[capacidad];
        cantidadItems = 0;
    }

    public Cliente getCliente() {
        return cliente;
    }

    private int buscarPosicion(Producto producto) {
        for (int i = 0; i < cantidadItems; i++) {
            if (productos[i] == producto) {
                return i;
            }
        }
        return -1;
    }

    public boolean agregarProducto(Producto producto, int cantidad) {
        if (producto == null) {
            return false;
        }

        int posicion = buscarPosicion(producto);

        if (posicion == -1 && cantidadItems >= productos.length) {
            return false;
        }

        if (!inventario.disminuirStock(producto, cantidad)) {
            return false;
        }

        if (posicion != -1) {
            cantidades[posicion] += cantidad;
        } else {
            productos[cantidadItems] = producto;
            cantidades[cantidadItems] = cantidad;
            cantidadItems++;
        }

        return true;
    }

    public double calcularSubtotal() {
        double subtotal = 0;

        for (int i = 0; i < cantidadItems; i++) {
            subtotal += productos[i].getPrecio() * cantidades[i];
        }

        return subtotal;
    }

    public double calcularDescuento() {
        double subtotal = calcularSubtotal();

        if (subtotal > limiteDescuento) {
            return subtotal * porcentajeDescuento;
        }

        return 0;
    }

    public double calcularTotal() {
        return calcularSubtotal() - calcularDescuento();
    }

    public void mostrarFactura() {
        System.out.println("\n========== FACTURA ==========");
        System.out.println("Cliente: " + cliente.getNombre());
        System.out.println("Email: " + cliente.getEmail());
        System.out.println("-----------------------------");

        if (cantidadItems == 0) {
            System.out.println("No hay productos en el carrito.");
            System.out.println("=============================");
            return;
        }

        for (int i = 0; i < cantidadItems; i++) {
            double subtotalProducto = productos[i].getPrecio() * cantidades[i];

            System.out.println(
                productos[i].getNombre()
                + " x" + cantidades[i]
                + " - $" + subtotalProducto
            );
        }

        System.out.println("-----------------------------");
        System.out.println("Subtotal: $" + calcularSubtotal());
        System.out.println("Descuento: -$" + calcularDescuento());
        System.out.println("Total a pagar: $" + calcularTotal());
        System.out.println("=============================");
    }
}

