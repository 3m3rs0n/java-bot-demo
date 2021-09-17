/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.ucb.est;

import java.util.ArrayList;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
/**
 *
 * @author ecampohermoso
 */
public class HelloWorldBot extends TelegramLongPollingBot {
    ArrayList<User> Usuarios = new ArrayList();

    @Override
    public String getBotToken() {
        return "";
    }

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println(update.getMessage().getChatId().toString()+ ": " + update.getMessage().getText());
        if(update.hasMessage()) { // Verificamos que tenga mensaje
            // Creo el objeto para enviar un mensaje
            SendMessage message = new SendMessage();
            String id = update.getMessage().getChatId().toString();
            message.setChatId(id); //Define a quien le vamos a enviar el mensaje
            Mensaje m = new Mensaje(message);
            String mensajeRecibido = update.getMessage().getText();
            User u = obtenerUsuario(id);
            if(u == null){ //Si el usuario es "nuevo" se lo agrega a la lista y muestra la pantalla de bienvenida
                u = new User(id);
                Usuarios.add(u);
                message = m.construirMensajeBienvenida();
            }else{
                if(u.ultimaRespuesta().equals("1")){
                    if(verificarNumero(mensajeRecibido)){
                        message = m.construirPregunta2();
                        u.agregarRespuesta("suma");
                    }else{
                        message = m.construirMensajeBienvenida();
                    }
                }else if(u.penultimaRespuesta().equals("suma")){
                    if(verificarNumero(mensajeRecibido)){
                        mandarMensaje(m.construirMensajeSuma(u.suma(mensajeRecibido)));
                    }
                    message = m.construirMensajeBienvenida();
                }else{
                    switch (mensajeRecibido) {
                        case "1":
                            message = m.construirPregunta1();
                            break;
                        case "2":
                            mandarMensaje(m.construirError());
                            message = m.construirMensajeBienvenida();
                            break;
                        default:
                            message = m.construirMensajeBienvenida();
                            break;
                    }
                }
            }
            u.agregarRespuesta(mensajeRecibido);
            mandarMensaje(message);
        }
    }
    
    public boolean verificarNumero(String num){
        boolean flag = true;
        try {
            int n = Integer.parseInt(num);
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }
    
    public void mandarMensaje(SendMessage m){
        try {
            execute(m); // Envia el mensaje
        } catch (TelegramApiException e) {
           
        }
    }
    
    public User obtenerUsuario(String id){
        User user = null;
        for(int i=0; i<Usuarios.size(); i++){
            String user1 = Usuarios.get(i).getId();
            if(id.equals(user1)){
                user = Usuarios.get(i);
            }
        }
        return user;
    }

    @Override
    public String getBotUsername() {
        return "ucb_est_emerson_bot";
    }
    
}
