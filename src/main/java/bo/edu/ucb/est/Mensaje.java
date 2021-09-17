/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.ucb.est;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

/**
 *
 * @author 59169
 */
public class Mensaje {
    SendMessage m;
    String text;
    
    public Mensaje(SendMessage m){
        this.m = m;
    }
    
    public SendMessage construirMensajeBienvenida(){
        text = "Bienvenido al Bot Calculadora.\n" +
                    "Seleccione una de las siguientes opciones:\n" +
                    "1. Sumar dos números.\n" +
                    "2. Calcular serie de fibonacci";
        m.setText(text);
        return m;
    }
    
    public SendMessage construirPregunta1(){
        text = "Ingrese el primer numero";
        m.setText(text);
        return m;
    }
    
    public SendMessage construirPregunta2(){
        text = "Ingrese el segundo numero";
        m.setText(text);
        return m;
    }
    
    public SendMessage construirError(){
        text = "Funcionalidad no implementada, intente otro día";
        this.m.setText(text);
        return m;
    }
    
    public SendMessage construirMensajeSuma(int num){
        text = "La suma es: "+ num;
        m.setText(text);
        return m;
    }
}
