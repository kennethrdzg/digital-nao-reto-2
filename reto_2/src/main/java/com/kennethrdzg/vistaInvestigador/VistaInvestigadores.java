package com.kennethrdzg.vistaInvestigador;

import java.util.ArrayList;
import com.kennethrdzg.investigador.Investigador;

public class VistaInvestigadores{
    public void mostrarAutor(Investigador investigador){
        System.out.println("ID: " + investigador.getId());
        System.out.println("Nombre: " + investigador.getNombre());
        System.out.println("Link: " + investigador.getLink());
        System.out.println("E-mail: " + investigador.getEmail());
        System.out.println("Afiliaciones: " + investigador.getAfiliaciones());
        System.out.println("Citado por: " + investigador.getCitadoPor());
    }
    public void mostrarInvestigadores(ArrayList<Investigador> investigadores){
        for(Investigador investigador: investigadores){
            mostrarAutor(investigador);
            System.out.println("--------------------");
        }
    }
}