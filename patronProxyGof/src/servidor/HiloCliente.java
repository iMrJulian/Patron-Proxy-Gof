/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import Logica.Estudiante;
import Logica.ProxyEstudiante;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.LinkedList;

/**
 *
 * @author julian
 */
public class HiloCliente implements Runnable {

    private Socket socket;
    private LinkedList<Socket> usuarios;
    private DataInputStream dataInput;
    private DataOutputStream dataOutput;
    BufferedReader input;
    PrintStream output;
    Estudiante estudiante = new ProxyEstudiante();

    public HiloCliente(Socket socket, LinkedList usuarios) {
        try {
            this.socket = socket;
            this.usuarios = usuarios;
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintStream(socket.getOutputStream());
            String texto = input.readLine();
            System.out.println(texto);
            output.println(estudiante.doSomething());

        } catch (IOException e) {

        } finally {
        }
    }

    @Override
    public void run() {

    }
}
