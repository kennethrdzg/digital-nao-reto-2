package com.kennethrdzg.vistaAutores;

import java.util.ArrayList;
import com.kennethrdzg.autor.Autor;

public class VistaAutores{
    private ArrayList<Autor> autores;

    public VistaAutores(){
        this.autores = new ArrayList<Autor>();
    }
    public VistaAutores(ArrayList<Autor> autores){
        this.autores = autores;
    }

    public void mostrarAutores(){
        for(Autor autor: autores){
            System.out.println("ID: " + autor.getId());
            System.out.println("Nombre: " + autor.getNombre());
            System.out.println("Link: " + autor.getLink());
            System.out.println("E-mail: " + autor.getEmail());
            System.out.println("Afiliaciones: " + autor.getAfiliaciones());
            System.out.println("Citado por: " + autor.getCitadoPor());
            System.out.println("--------------------");
        }
    }

    public void agregarAutor(Autor autor){
        autores.add(autor);
    }
}