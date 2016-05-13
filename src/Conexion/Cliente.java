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

import Cerebro.Neurona;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import Utils.Caster;
import Utils.Log;

/**
 * @author rob3ns
 */
public class Cliente extends Thread {

	private static final String HOST = "localhost";
	private static final int PUERTO = 5000;
	private Socket clienteSck;
	private final ArrayList<Neurona> neuronas;
	private Log log;

	public Cliente(ArrayList<Neurona> info) {
		neuronas = info;
		log = new Log(this.getClass());
	}

	@Override
	public void run() {
		try {
			iniciarCliente();
		} catch (IOException ex) {
			Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void iniciarCliente() throws IOException {
		try {
			clienteSck = new Socket(HOST, PUERTO);
		} catch (UnknownHostException e) {
			log.error("Unknown host: " + HOST);
			System.exit(1);
		} catch (IOException e) {
			log.error("No I/O");
			System.exit(1);
		}

		enviarBytes();
		clienteSck.close();
	}

	private void enviarBytes() {
		InputStream inpStr = null;
		OutputStream outpStr = null;
		try {
			DataOutputStream fluout = new DataOutputStream(outpStr);
			inpStr = clienteSck.getInputStream();
			outpStr = clienteSck.getOutputStream();

			fluout.write(neuronas.size());
			for (Neurona n : Caster.safeIterable(neuronas)) {
				byte[] b = Caster.bitSetToByteArray(n.getNucleo());

				fluout.write(b.length);
				fluout.write(b);
			}
		} catch (IOException ex) {
			log.error("Error del cliente al enviar bytes.");
			Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				inpStr.close();
				outpStr.close();
			} catch (IOException ex) {
				log.error("Error del cliente al cerrar Stream.");
				Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
}
