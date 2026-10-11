/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.app;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author PC
 */
public class Agenda {
    // Expansion 16: ArrayList en lugar del arreglo de tamano fijo (20)
    private ArrayList<Contacto> contactos;

    // Expansion 20: archivo donde se guardan los contactos
    private String archivo = "contactos.txt";

    public Agenda() {
        contactos = new ArrayList<Contacto>();
    }

    // Expansion 17: el telefono debe tener exactamente 9 caracteres y todos deben ser numeros.
    private boolean telefonoValido(String telefono) {
        if (telefono.length() != 9) {
            return false;
        }

        for (int i = 0; i < telefono.length(); i++) {
            char caracter = telefono.charAt(i);

            if (caracter < '0' || caracter > '9') {
                return false;
            }
        }

        return true;
    }

    // Expansion 17: el email debe tener un "@" y un ".".
    private boolean emailValido(String email) {
        return email.contains("@") && email.contains(".");
    }

    public boolean agregarContacto(Contacto c) {
        if (c == null) {
            return false;
        }

        if (!telefonoValido(c.getTelefono())) {
            System.out.println("Telefono invalido: debe tener 9 digitos y solo numeros.");
            return false;
        }

        if (!emailValido(c.getEmail())) {
            System.out.println("Email invalido: debe contener \"@\" y \".\".");
            return false;
        }

        // El caracter ";" separa los datos en el archivo, por eso no se permite en el nombre ni en el email.
        if (c.getNombre().contains(";") || c.getEmail().contains(";")) {
            System.out.println("No se permite el caracter \";\" en el nombre ni en el email.");
            return false;
        }

        for (int i = 0; i < contactos.size(); i++) {
            if (contactos.get(i).getNombre().equals(c.getNombre())
                    || contactos.get(i).getTelefono().equals(c.getTelefono())) {
                System.out.println("Ya existe un contacto con ese nombre o telefono.");
                return false;
            }
        }

        contactos.add(c);
        return true;
    }

    // Expansion 18: busqueda parcial. Devuelve TODOS los contactos cuyo nombre contenga el texto.
    // Se pasa todo a minusculas para que "ana" tambien encuentre a "Mariana".
    public ArrayList<Contacto> buscarContacto(String nombre) {
        ArrayList<Contacto> encontrados = new ArrayList<Contacto>();

        for (int i = 0; i < contactos.size(); i++) {
            String nombreContacto = contactos.get(i).getNombre().toLowerCase();

            if (nombreContacto.contains(nombre.toLowerCase())) {
                encontrados.add(contactos.get(i));
            }
        }

        return encontrados;
    }

    public void mostrarTodos() {
        if (contactos.size() == 0) {
            System.out.println("No hay contactos registrados.");
            return;
        }

        System.out.println("\n======= CONTACTOS =======");

        for (int i = 0; i < contactos.size(); i++) {
            System.out.println("\nContacto #" + (i + 1));
            contactos.get(i).mostrarInfo();
        }
    }

    // Expansion 19: muestra solo los contactos de un grupo.
    public void mostrarContactosPorGrupo(String grupo) {
        int encontrados = 0;

        System.out.println("\n======= GRUPO: " + grupo + " =======");

        for (int i = 0; i < contactos.size(); i++) {
            if (contactos.get(i).getGrupo().equals(grupo)) {
                System.out.println();
                contactos.get(i).mostrarInfo();
                encontrados++;
            }
        }

        if (encontrados == 0) {
            System.out.println("No hay contactos en este grupo.");
        }
    }

    public boolean eliminarContacto(String nombre) {
        for (int i = 0; i < contactos.size(); i++) {
            if (contactos.get(i).getNombre().equals(nombre)) {
                contactos.remove(i);
                return true;
            }
        }

        return false;
    }

    // Expansion 20: escribe una linea por contacto: nombre;telefono;email;grupo
    public boolean guardarEnArchivo() {
        try {
            PrintWriter escritor = new PrintWriter(archivo);

            for (int i = 0; i < contactos.size(); i++) {
                Contacto c = contactos.get(i);
                escritor.println(c.getNombre() + ";" + c.getTelefono() + ";" + c.getEmail() + ";" + c.getGrupo());
            }

            escritor.close();
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }

    // Expansion 20: lee el archivo y reemplaza los contactos que hay en memoria.
    // Devuelve false si el archivo todavia no existe.
    public boolean cargarDesdeArchivo() {
        try {
            Scanner lector = new Scanner(new File(archivo));
            contactos.clear();

            while (lector.hasNextLine()) {
                String linea = lector.nextLine();
                String[] partes = linea.split(";");

                if (partes.length == 4) {
                    contactos.add(new Contacto(partes[0], partes[1], partes[2], partes[3]));
                }
            }

            lector.close();
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }
}
