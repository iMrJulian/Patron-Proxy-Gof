/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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
    protected String[][] matriz;
    protected String nombre;
    protected int id;
    protected int turno;

    public HiloCliente(Socket socket, LinkedList usuarios) {
        try {
            this.socket = socket;
            this.usuarios = usuarios;
            dataInput = new DataInputStream(socket.getInputStream());
            dataOutput = new DataOutputStream(socket.getOutputStream());

        } catch (IOException e) {

        } finally {
        }
    }

    @Override
    public void run() {
        try {       
            dataOutput.writeInt(id % 2);
            while (true) {
                String texto = dataInput.readUTF();
                System.out.println(texto);
                for (Socket usuario : usuarios) {
                    if (!usuario.equals(this.socket)) {
                        System.out.println(usuario + " : " + this.socket);
                        dataOutput = new DataOutputStream(usuario.getOutputStream());
                        dataOutput.writeUTF(texto);
                    }
                }
            }
        } catch (IOException ex) {

        }
    }
}
