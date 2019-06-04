/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

/**
 *
 * @author julian
 */
public class Servidor {

    BufferedReader input;
    PrintStream output;
    LinkedList<Socket> usuarios;

    private DataInputStream dataInput;
    private DataOutputStream dataOutput;

    public Servidor() {
       
        this.usuarios = new LinkedList<Socket>();
        try {
            ServerSocket socketServidor = new ServerSocket(8085);
            while (true) {     
                Socket cliente = socketServidor.accept();
                usuarios.add(cliente);
                Runnable nuevoCliente = new HiloCliente(cliente, usuarios);
                Thread hilo = new Thread(nuevoCliente);
                hilo.start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new Servidor();
    }

}