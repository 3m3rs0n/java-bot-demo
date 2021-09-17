/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.ucb.est;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author 59169
 */
public class User {
    String id;
    List<String> respuestas;
    
    public User(String id){
        this.id = id;
        this.respuestas = new ArrayList();
        this.respuestas.add("");
    }
    
    public String getId(){
        return this.id;
    }
    
    public List<String> getRespuestas(){
        return this.respuestas;
    }
    
    public void agregarRespuesta(String u){
        this.respuestas.add(u);
    }
    
    public String ultimaRespuesta(){
        String ultimo = respuestas.get((respuestas.size()-1));
        return ultimo;
    }
    
    public String penultimaRespuesta(){
        String ultimo = respuestas.get((respuestas.size()-2));
        return ultimo;
    }
    
    public int suma(String m){
        int numero1 = Integer.parseInt(ultimaRespuesta());
        int numero2 = Integer.parseInt(m);
        return (numero1+numero2);
    }
    
}
