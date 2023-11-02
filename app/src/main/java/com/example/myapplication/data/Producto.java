package com.example.myapplication.data;

import java.util.ArrayList;

public class Producto {
    private String nombre;
    private String precio;
    private String descripcion;

    public static ArrayList<Producto> productos = new ArrayList<>();


    static {
        Producto unProducto = new Producto("Televisor", "1000000", "Televisor Tokio");
    }

    public Producto(String nombre, String precio, String descripcion) {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public static void agregarProducto (Producto unProducto) {
        productos.add(unProducto);
    }

    public String getNombre(){
        return nombre;
    }

    public String getPrecio(){
        return precio;
    }

    public String getDescripcion(){
        return descripcion;
    }


}
