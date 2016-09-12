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

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Map.Entry;

import Cerebro.Neurona;
import Utils.Caster;
import Utils.Log;
import Utils.Opcodes.Opcode;

/**
 * @author rob3ns
 */
public class Cliente extends Thread {

	private static final String HOST = "localhost"; //TODO
	private static final int PUERTO = 5000;
	private Socket clienteSck;
	private final Map<Integer, Neurona> neuronas;
	private Log log;
	private String word;
	private Opcode opcode;

	public Cliente(String word, Map<Integer, Neurona> info, Opcode  op) {
		this.word = word;
		neuronas = info;
		log = new Log(this.getClass());
		this.opcode = op;
	}

	@Override
	public void run() {
		try {
			iniciarCliente();
		} catch (IOException ex) {
			log.error("No se ha podido iniciar el cliente.");
		}
	}

	private void iniciarCliente() throws IOException {
		try {
			clienteSck = new Socket(HOST, PUERTO);
			enviarBytes();
			clienteSck.close();
		} catch (UnknownHostException e) {
			log.error("Unknown host: " + HOST);
			//System.exit(1);
		} catch (IOException e) {
			log.error("No I/O");
			//System.exit(1);
		}


	}

	private void enviarBytes() {
		InputStream inpStr = null;
		OutputStream outpStr = null;
		try {
			DataOutputStream fluout = new DataOutputStream(outpStr);
			inpStr = clienteSck.getInputStream();
			outpStr = clienteSck.getOutputStream();

			fluout.write(opcode.getInt());

			if (!neuronas.isEmpty()) {
				fluout.write(neuronas.size());
				for (Entry<Integer, Neurona> n : neuronas.entrySet()) {
					byte[] b = Caster.bitSetToByteArray(n.getValue().getNucleo());

					fluout.write(b.length);
					fluout.write(b);
				}
				fluout.writeUTF(word);
			} else { // test
				int n = 0;
				fluout.write(n);
				fluout.writeUTF("");
			}
		} catch (IOException ex) {
			log.error("Al enviar bytes.");
		} finally {
			try {
				inpStr.close();
				outpStr.close();
			} catch (IOException ex) {
				log.error("Al cerrar stream.");
			}
		}
	}
}
