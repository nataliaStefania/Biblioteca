/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.oregoom.biblioteca;
import javax.swing.*;
/**
 *
 * @author Nat
 */
public class Biblioteca {
    static Grafo grafo = new Grafo();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            mostrarMenu();
        });
    }

    public static void mostrarMenu() {
        String[] opciones = {"1. Agregar usuario", "2. Agregar libro", "3. Prestar libro", "4. Devolver libro", "5. Ver usuarios y libros", "6. Salir"};

        int seleccion = JOptionPane.showOptionDialog(
            null, 
            "Seleccione una opción:", 
            "Menú Principal", 
            JOptionPane.DEFAULT_OPTION, 
            JOptionPane.PLAIN_MESSAGE, 
            null, 
            opciones, 
            opciones[0]
        );

        switch (seleccion) {
            case 0:
                agregarUsuario();
                break;
            case 1:
                agregarLibro();
                break;
            case 2:
                prestarLibro();
                break;
            case 3:
                devolverLibro();
                break;
            case 4:
                mostrarUsuariosYLibros(); 
                break;
            case 5:
                System.exit(0);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción no válida", "Error", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }

    public static void agregarUsuario() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del usuario:");
        String id = JOptionPane.showInputDialog("Ingrese el ID del usuario:");

        Usuario usuario = new Usuario(nombre, Integer.parseInt(id));
        Nodo nodoUsuario = new Nodo(id, usuario);
        grafo.agregarNodo(nodoUsuario);

        JOptionPane.showMessageDialog(null, "Usuario agregado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        mostrarMenu();
    }

    public static void agregarLibro() {
        String titulo = JOptionPane.showInputDialog("Ingrese el título del libro:");
        String isbn = JOptionPane.showInputDialog("Ingrese el ISBN del libro:");

        Libro libro = new Libro(titulo, isbn);
        Nodo nodoLibro = new Nodo(isbn, libro);
        grafo.agregarNodo(nodoLibro);

        JOptionPane.showMessageDialog(null, "Libro agregado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        mostrarMenu();
    }

    public static void prestarLibro() {
        String idUsuario = JOptionPane.showInputDialog("Ingrese el ID del usuario:");
        String isbn = JOptionPane.showInputDialog("Ingrese el ISBN del libro a prestar:");

        Nodo nodoUsuario = grafo.buscarNodo(idUsuario);
        Nodo nodoLibro = grafo.buscarNodo(isbn);

        if (nodoUsuario != null && nodoLibro != null) {
            grafo.agregarArista(nodoUsuario, nodoLibro);
            JOptionPane.showMessageDialog(null, "Libro prestado correctamente a " + idUsuario, "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Usuario o libro no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
        }

        mostrarMenu();
    }

    public static void devolverLibro() {
        String idUsuario = JOptionPane.showInputDialog("Ingrese el ID del usuario:");
        String isbn = JOptionPane.showInputDialog("Ingrese el ISBN del libro a devolver:");

        Nodo nodoUsuario = grafo.buscarNodo(idUsuario);
        Nodo nodoLibro = grafo.buscarNodo(isbn);

        if (nodoUsuario != null && nodoLibro != null) {
            grafo.eliminarArista(nodoUsuario, nodoLibro);
            JOptionPane.showMessageDialog(null, "Libro devuelto correctamente por " + idUsuario, "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Usuario o libro no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
        }

        mostrarMenu();
    }
    
    public static void mostrarUsuariosYLibros() {
        StringBuilder usuarios = new StringBuilder("Usuarios:\n");
        StringBuilder libros = new StringBuilder("Libros:\n");

        for (Nodo nodo : grafo.adjList.keySet()) {
            if (nodo.data instanceof Usuario) {
                Usuario usuario = (Usuario) nodo.data;
                usuarios.append("ID: ").append(usuario.id)
                        .append(", Nombre: ").append(usuario.nombre)
                        .append("\n");
            } else if (nodo.data instanceof Libro) {
                Libro libro = (Libro) nodo.data;
                libros.append("Título: ").append(libro.titulo)
                      .append(", ISBN: ").append(libro.isbn)
                      .append("\n");
            }
        }

        JOptionPane.showMessageDialog(null, usuarios.toString() + "\n" + libros.toString(), "Usuarios y Libros", JOptionPane.INFORMATION_MESSAGE);
        mostrarMenu();
    }
}
