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

import Utils.Log;

/**
 * @author rob3ns
 */
public class Servidor extends Thread {

	private static final int PUERTO = 5000;
	private ServerSocket serverSck;
	private Socket clienteSck;
	private boolean on;
	private Cerebro c;
	private Log log;

	public Servidor(Cerebro c) {
		this.c = c;
		log = new Log(this.getClass());
		on = true;
	}

	@Override
	public void run() {
		try {
			while (on) {
				iniciarServer();
			}
		} catch (IOException ex) {
			log.error("Al iniciar servidor.");
			Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void iniciarServer() throws IOException {
		try {
			serverSck = new ServerSocket(PUERTO);
		} catch (IOException e) {
			log.error("No se ha podido escuchar en puerto " + PUERTO);
			System.exit(-1);
		}

		log.debug("Escucho el puerto " + PUERTO);
		clienteSck = serverSck.accept();
		log.debug("Cliente aceptado: " + clienteSck.getLocalSocketAddress().toString());

		recibirBytes();

		clienteSck.close();
		serverSck.close();
	}

	/**
	 * Bytes de hemisf1 (client) a hemisf2 (sv, this)
	 */
	private void recibirBytes() {
		InputStream inpStr = null;
		OutputStream outpStr = null;
		try {
			DataInputStream fluin = new DataInputStream(inpStr);
			inpStr = clienteSck.getInputStream();
			outpStr = clienteSck.getOutputStream();

			int cantidad = fluin.readInt();
			if (cantidad > 0) {
				for (int i = 0; i < cantidad; i++) {
					byte[] b = new byte[fluin.readInt()];
					fluin.readFully(b);
	
					c.getMgris().agregarBytes(b);
				}
				
				String word = fluin.readUTF();
				c.reciTransferencia(word); //mgris -> c
			}
		} catch (IOException ex) {
			log.error("Al recibir bytes.");
			Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				outpStr.close();
				inpStr.close();
			} catch (IOException ex) {
				log.error("Al cerrar Stream.");
				Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	public boolean isOn() {
		return on;
	}

	public void setOn(boolean on) {
		this.on = on;
	}
}
