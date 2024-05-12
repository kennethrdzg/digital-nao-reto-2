package com.kennethrdzg;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

import com.kennethrdzg.controladorAutores.ControladorAutores;
import com.kennethrdzg.vistaAutores.VistaAutores;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException{
        String apiKey = "";
        
        try {
            Scanner apiKeyScanner = new Scanner(new File("apiKey.env"));
            apiKey = apiKeyScanner.nextLine();
            apiKeyScanner.close();
        } catch( FileNotFoundException e){
            System.out.println("Ocurrió un error al leer la clave de la API");
            e.printStackTrace();
            System.exit(1);
        }

        String apiURL = "https://serpapi.com/search.json?engine=google_scholar_profiles&mauthors=UANL&api_key=" + apiKey;

        // Crear instancia de vista y controlador
        VistaAutores vistaAutores = new VistaAutores();
        ControladorAutores controladorAutores = new ControladorAutores(vistaAutores);
        controladorAutores.obtenerAutores(apiURL);
    }
}

/*
class Perfil{
    String nombre;
    String id;
    int citado_por;
    Perfil(String nombre, String id, int citado_por){
        this.nombre = nombre;
        this.id = id;
        this.citado_por = citado_por;
    }
    @Override
    public String toString() {
        return nombre + ", citado por " + citado_por;
    }
}
*/