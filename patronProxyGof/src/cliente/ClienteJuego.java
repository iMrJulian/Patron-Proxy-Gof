/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.net.Socket;

/**
 *
 * @author julian
 */
public class ClienteJuego {
    private Socket socket;

    public ClienteJuego() {
        try {
            socket = new Socket("localhost", 8085);
            new ControlCliente(socket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
