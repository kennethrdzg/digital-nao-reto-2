package com.kennethrdzg;
import java.io.IOException;
import java.util.Scanner;

import com.kennethrdzg.controladorInvestigadores.ControladorInvestigadores;
import com.kennethrdzg.investigadoresLista.InvestigadoresLista;
import com.kennethrdzg.vistaInvestigador.VistaInvestigadores;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException, SQLException{
        String apiKey = "";
        String dbPassword = "";
        try {
            Scanner apiKeyScanner = new Scanner(new File("apiKey.env"));
            apiKey = apiKeyScanner.nextLine();
            apiKeyScanner.close();

            Scanner dbPwScanner = new Scanner(new File("dbPassword.env"));
            dbPassword = dbPwScanner.nextLine();
            dbPwScanner.close();

        } catch( FileNotFoundException e){
            System.out.println("Ocurrió un error al leer la clave de la API");
            e.printStackTrace();
            System.exit(1);
        }

        String apiURL = "https://serpapi.com/search.json?engine=google_scholar_profiles&mauthors=UANL&api_key=" + apiKey;

        // Crear instancia de vista y controlador
        Connection conexion = null;
        try{
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/autores?user=root&password="+dbPassword+"&useSSL=false");
        }
        catch(SQLException e){
            System.err.println("Ocurrió un error al conectar con la base de datos");
            e.printStackTrace();
            System.exit(1);
        }
        
        /*
         * if(conexion == null){
            System.out.println("Error");
            return;
        }
         */
        
        //Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/reto2", "root", "ForgedInFire01");
        InvestigadoresLista modelo = new InvestigadoresLista();
        VistaInvestigadores vista = new VistaInvestigadores();
        ControladorInvestigadores controlador = new ControladorInvestigadores(modelo, vista, conexion);
        controlador.obtenerInvestigadores(apiURL);
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