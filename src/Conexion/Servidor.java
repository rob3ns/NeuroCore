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
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author rob3ns
 */
public class Servidor extends Thread {

    private static final int PUERTO = 5000;
    private ServerSocket serverSck;
    private Socket clienteSck;
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
            System.out.println("Error al iniciar servidor.");
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void iniciarServer() throws IOException {
        try {
            serverSck = new ServerSocket(PUERTO);
        } catch (IOException e) {
            System.out.println("No se ha podido escuchar en puerto " + PUERTO);
            System.exit(-1);
        }

        System.out.println("#Escucho el puerto " + PUERTO);
        clienteSck = serverSck.accept();
        System.out.println("#Cliente aceptado: " + clienteSck.getLocalSocketAddress().toString());

        recibirBytes();
        c.reciTransferencia();

        clienteSck.close();
        serverSck.close();
    }

    private void recibirBytes() {
        InputStream inpStr = null;
        OutputStream outpStr = null;
        try {
            inpStr = clienteSck.getInputStream();
            outpStr = clienteSck.getOutputStream();
            //DataOutputStream fluout = new DataOutputStream(aux);
            DataInputStream fluin = new DataInputStream(inpStr);

            int cantidad = fluin.readInt();
            for (int i = 0; i < cantidad; i++) {
                byte[] b = new byte[fluin.readInt()];
                fluin.readFully(b);

                c.getMgris().agregarBytes(b); // pasamos los bytes al cerebro
            }
        } catch (IOException ex) {
            System.out.println("Error del servidor al recibir bytes.");
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                outpStr.close();
                inpStr.close();
            } catch (IOException ex) {
                System.out.println("Error del servidor al cerrar Stream.");
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
