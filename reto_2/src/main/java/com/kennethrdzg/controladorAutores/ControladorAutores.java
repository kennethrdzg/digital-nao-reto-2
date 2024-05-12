package com.kennethrdzg.controladorAutores;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.kennethrdzg.autor.Autor;
import com.kennethrdzg.vistaAutores.VistaAutores;

public class ControladorAutores{
    private VistaAutores vistaAutores;
    private HttpClient client;

    public ControladorAutores(VistaAutores vistaAutores){
        this.vistaAutores = vistaAutores;
        this.client = HttpClient.newHttpClient();
    }

    public void obtenerAutores(String apiURL) throws IOException, InterruptedException{
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiURL)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        String jsonData = response.body();
        JSONObject jsonObject = new JSONObject(jsonData);
        if(! jsonObject.getJSONObject("search_metadata").getString("status").equals("Success")){
            System.out.println("Consulta fallida.");
            return;
        }

        JSONArray autoresJson = jsonObject.getJSONArray("profiles");

        for(int i = 0; i < autoresJson.length(); i++){
            JSONObject autorJsonObject = autoresJson.getJSONObject(i);
            //System.out.println(autorJsonObject);
            String id = autorJsonObject.getString("author_id");
            String nombre = autorJsonObject.getString("name");
            String link = autorJsonObject.getString("link");
            String email = "E-mail no verificado";
            if(autorJsonObject.has("email")){
                email = autorJsonObject.getString("email");
            }
            String afiliaciones = autorJsonObject.getString("affiliations");
            int citado_por = autorJsonObject.getInt("cited_by");
            Autor autor = new Autor(id, nombre, link, citado_por, email, afiliaciones);
            vistaAutores.agregarAutor(autor);
        }
        vistaAutores.mostrarAutores();
    }
}