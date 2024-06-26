package com.kennethrdzg.investigador;
public class Investigador{
    private String id;
    private String nombre;
    private String link;
    private int citado_por;
    private String email;
    private String afiliaciones;
    public Investigador(String id, String nombre, String link, int citado_por, String email, String afiliaciones){
        this.id = id;
        this.nombre = nombre;
        this.link = link;
        this.citado_por = citado_por;
        this.email = email;
        this.afiliaciones = afiliaciones;
    }

    // Id
    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    // Nombre
    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    // Link
    public String getLink(){
        return link;
    }

    public void setLink(String link){
        this.link = link;
    }
    // Citado por
    public int getCitadoPor(){
        return citado_por;
    }

    public void setCitadoPor(int citado_por){
        this.citado_por = citado_por;
    }
    //Email
    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }
    // Afiliaciones
    public String getAfiliaciones(){
        return afiliaciones;
    }

    public void setAfiliaciones(String afiliaciones){
        this.afiliaciones = afiliaciones;
    }

}