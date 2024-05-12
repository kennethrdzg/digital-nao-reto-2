package com.kennethrdzg.investigadoresLista;

import java.util.ArrayList;

import com.kennethrdzg.investigador.Investigador;;

public class InvestigadoresLista{
    private ArrayList<Investigador> investigadores;

    public InvestigadoresLista(){
        this.investigadores = new ArrayList<>();
    }

    public void agregarInvestigador(Investigador investigador){
        investigadores.add(investigador);
    }

    public ArrayList<Investigador> getInvestigadores(){
        return investigadores;
    }
}