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
package Cerebro.Materia;

import java.util.LinkedHashMap;
import java.util.Map;

import Cerebro.Cerebro;
import Cerebro.Neurona;
import Conexion.Cliente;
import Conexion.Servidor;
import Opcodes.Opcode.Opc;
import utils.Log;

/**
 * @author rob3ns
 */
// Conex entre neuronas -axones - sockets y demas entre hemisferios
public class MBlanca {

	private Cliente client;
	private Servidor server;
	private Log log;

	/**
	 * Inicializada en Cerebro
	 */
	public MBlanca(Cerebro c) {
		iniciarMBlanca(c);
	}

	/**
	 * Servidor a la espera del otro hemisf
	 * @param c Cerebro
	 */
	private void iniciarMBlanca(Cerebro c) {
		log = new Log(this.getClass());

		server = new Servidor(c);
		server.start();
	}

	/**
	 * Transfer. al otro hemisf
	 * @param neuronas Lo que pasas al otro, Array
	 */
	public void transferencia(String word, Map<Integer, Neurona> neuronas, Opc op) {
		client = new Cliente(word, neuronas, op);
		if (!client.isAlive()) {
			client.start();
		}
	}

	public void stop() {
		stopClient();
		stopServer();
	}

	private void stopServer() {
		server.setOn(false);
		server.interrupt();
		//dummyClient();
	}

	private void stopClient() {
		if (client != null && client.isAlive()) {
			try {
				client.join();
			} catch (InterruptedException e) {
				log.error("In client join.");
			}
		}
	}

	/**
	 * Server waiting response
	 */
	private void dummyClient() {
		if (server.isAlive()) {
			client = new Cliente("" ,new LinkedHashMap<Integer, Neurona>(), Opc.INFO);
			client.start();

			if (client.isAlive()) {
				try {
					client.join();
				} catch (InterruptedException e) {
					log.error("Client 2 failed while stopping server.");
				}
			}
		} else {
			log.debug("Server not alive, dummy client.");
		}
	}

	public Cliente getClient() {
		return client;
	}

	public void setClient(Cliente cl) {
		this.client = cl;
	}

	public Servidor getServer() {
		return server;
	}

	public void setServer(Servidor sv) {
		this.server = sv;
	}
}
