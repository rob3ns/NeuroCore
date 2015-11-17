/*
 * Copyright (C) 2015 NeuroCore <http://rob3ns.github.io/NeuroCore/>
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the
 * Free Software Foundation; either version 2 of the License, or (at your
 * option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package Conexion;

import Cerebro.Cerebro;
import Cerebro.Materia.MGris;
import Cerebro.Neurona;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author rob3ns
 */
public class Servidor extends Thread {

    private static final int PUERTO = 5000;
    private ServerSocket serverSck;
    private Socket clienteSck;
    private ArrayList<Neurona> neuronas;
    private Cerebro c;

    public Servidor(Cerebro c) {
        this.c = c;
    }

    @Override
    public void run() {
        try {
            while (true) {
                iniciarServer();
            }
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void iniciarServer() throws IOException {
        try {
            serverSck = new ServerSocket(PUERTO);
        } catch (IOException e) {
            System.out.println("No se ha podido escuchar en puerto 4321");
            System.exit(-1);
        }

        System.out.println("#Escucho el puerto " + PUERTO);
        clienteSck = serverSck.accept();
        System.out.println("#Cliente aceptado: " + clienteSck.getLocalSocketAddress().toString());

        OutputStream aux = clienteSck.getOutputStream();
        InputStream in = clienteSck.getInputStream();

        DataOutputStream fluout = new DataOutputStream(aux);
        DataInputStream fluin = new DataInputStream(in);

        int cantidad = fluin.readInt();

        for (int i = 0; i < cantidad; i++) {
            byte[] b = new byte[fluin.readInt()];
            fluin.readFully(b);

            c.getMgris().agregarBytes(b); // pasamos los bytes al cerebro
        }
        
        c.reciTransferencia();
        fluout.writeUTF("Ok");
        
        clienteSck.close();
        serverSck.close();
    }
}
