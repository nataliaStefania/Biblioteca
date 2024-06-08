/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oregoom.biblioteca;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Nat
 */

public class Grafo {
    
      Map<Nodo, Set<Nodo>> adjList;

    public Grafo() {
        adjList = new HashMap<>();
    }

    public void agregarNodo(Nodo nodo) {
        adjList.putIfAbsent(nodo, new HashSet<>());
    }

    public void agregarArista(Nodo nodo1, Nodo nodo2) {
        adjList.get(nodo1).add(nodo2);
        adjList.get(nodo2).add(nodo1);
    }

    public void eliminarArista(Nodo nodo1, Nodo nodo2) {
        Set<Nodo> aristasNodo1 = adjList.get(nodo1);
        Set<Nodo> aristasNodo2 = adjList.get(nodo2);
        if (aristasNodo1 != null) {
            aristasNodo1.remove(nodo2);
        }
        if (aristasNodo2 != null) {
            aristasNodo2.remove(nodo1);
        }
    }

    public Set<Nodo> obtenerAdyacentes(Nodo nodo) {
        return adjList.get(nodo);
    }

    public Nodo buscarNodo(String id) {
        for (Nodo nodo : adjList.keySet()) {
            if (nodo.id.equals(id)) {
                return nodo;
            }
        }
        return null;
    }
}
