/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tiendaonline;
import java.util.ArrayList;
/**
 *
 * @author PC
 */
public class Inventario {
    private ArrayList<Producto> productos;

    public Inventario() {
        productos = new ArrayList<Producto>();
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public boolean hayStock(Producto producto, int cantidad) {
        return cantidad > 0 && cantidad <= producto.getStock();
    }

    public boolean disminuirStock(Producto producto, int cantidad) {
        if (!hayStock(producto, cantidad)) {
            return false;
        }

        producto.setStock(producto.getStock() - cantidad);
        return true;
    }

    public ArrayList<String> obtenerCategorias() {
        ArrayList<String> categorias = new ArrayList<String>();

        for (int i = 0; i < productos.size(); i++) {
            String categoria = productos.get(i).getCategoria();

            if (!categorias.contains(categoria)) {
                categorias.add(categoria);
            }
        }

        return categorias;
    }

    public ArrayList<Producto> filtrarPorCategoria(String categoria) {
        ArrayList<Producto> filtrados = new ArrayList<Producto>();

        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getCategoria().equals(categoria)) {
                filtrados.add(productos.get(i));
            }
        }

        return filtrados;
    }
}
