package com.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

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
            
        }
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese consulta: ");
        String query = scanner.nextLine();

        String url = "https://serpapi.com/search?engine=google_scholar&q=" + query + "&api_key=" + api_key;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
        scanner.close();
    }
}