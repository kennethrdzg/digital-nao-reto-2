package com.kennethrdzg.controladorInvestigadores;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;

import com.kennethrdzg.investigador.Investigador;
import com.kennethrdzg.investigadoresLista.InvestigadoresLista;
import com.kennethrdzg.vistaInvestigador.VistaInvestigadores;

public class ControladorInvestigadores{
    private VistaInvestigadores vista;
    private InvestigadoresLista modelo;
    //private ArrayList<Autor> modeloAutores;
    private HttpClient client;
    private Connection conexion;

    public ControladorInvestigadores(InvestigadoresLista modelo, VistaInvestigadores vista, Connection conexion){
        this.modelo = modelo;
        this.vista = vista;
        this.conexion = conexion;
        this.client = HttpClient.newHttpClient();
    }

    public void obtenerInvestigadores(String apiURL) throws IOException, InterruptedException, SQLException{
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiURL)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        String jsonData = response.body();
        JSONObject jsonObject = new JSONObject(jsonData);
        if(! jsonObject.getJSONObject("search_metadata").getString("status").equals("Success")){
            System.out.println("Consulta fallida.");
            return;
        }

        JSONArray investigadoresJsonArray = jsonObject.getJSONArray("profiles");

        for(int i = 0; i < investigadoresJsonArray.length(); i++){
            JSONObject investigadorJsonObject = investigadoresJsonArray.getJSONObject(i);
            //System.out.println(autorJsonObject);
            String id = investigadorJsonObject.getString("author_id");
            String nombre = investigadorJsonObject.getString("name");
            String link = investigadorJsonObject.getString("link");
            String email = "E-mail no verificado";
            if(investigadorJsonObject.has("email")){
                email = investigadorJsonObject.getString("email");
            }
            String afiliaciones = investigadorJsonObject.getString("affiliations");
            int citado_por = investigadorJsonObject.getInt("cited_by");
            Investigador investigador = new Investigador(id, nombre, link, citado_por, email, afiliaciones);
            modelo.agregarInvestigador(investigador);
        }
        vista.mostrarInvestigadores(modelo.getInvestigadores());
        almacenarInvestigadores();
    }

    public void almacenarInvestigadores() throws SQLException{
        String sql = "INSERT INTO autores (AutorId, Nombre, Link, CitadoPor, Email, Afiliaciones) VALUES(?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = conexion.prepareStatement(sql);
        for(Investigador investigador: modelo.getInvestigadores()){
            statement.setString(1, investigador.getId());
            statement.setString(2, investigador.getNombre());
            statement.setString(3, investigador.getLink());
            statement.setInt(4, investigador.getCitadoPor());
            statement.setString(5, investigador.getEmail());
            statement.setString(6, investigador.getAfiliaciones());
            statement.executeUpdate();
        }
        statement.close();
    }
}