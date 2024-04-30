package com.kennethrdzg;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

import org.json.*;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException{
        File archivo = new File("api_key.txt");
        
        String api_key = "";
        try {
            Scanner api_key_scanner = new Scanner(archivo);
            api_key = api_key_scanner.nextLine();
            api_key_scanner.close();
        } catch (FileNotFoundException e){
            System.out.println("Ocurrió un error al leer la clave de la API");
            e.printStackTrace();
            System.exit(1);
            
        }
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese consulta: ");
        String query = scanner.nextLine();
        scanner.close();
        String url = "https://serpapi.com/search.json?engine=google_scholar_profiles&mauthors=" + query + "&api_key=" + api_key;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        //System.out.println(response.body());

        String jsonData = response.body();

        JSONObject jsonObj = new JSONObject(jsonData);

        //System.out.println(jsonObj);
        System.out.println(jsonObj.getJSONObject("search_metadata").getString("status"));

        if(! jsonObj.getJSONObject("search_metadata").getString("status").equals("Success")){
            System.out.println("Consulta fallida");
            return;
        }

        JSONArray perfiles_json = jsonObj.getJSONArray("profiles");
        ArrayList<Perfil> perfiles = new ArrayList<Perfil>();
        for(int i = 0; i < perfiles_json.length(); i++){
            JSONObject perfil_json = perfiles_json.getJSONObject(i);
            Perfil perfil = new Perfil(perfil_json.getString("name"), perfil_json.getString("author_id"), perfil_json.getInt("cited_by"));
            perfiles.add(perfil);
            System.out.println(perfiles.get(i).toString());
        }
        
    }
}

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