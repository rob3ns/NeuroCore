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
package Main;

import java.util.Scanner;

import Cerebro.Cerebro;
import Conexion.Database;
import utils.Log;

/**
 * @author rob3ns
 */
public class NeuroCore {

	private Cerebro core;
	private final Scanner sc;
	private Database db;
	private Log log;

	public NeuroCore() {
		sc = new Scanner(System.in);
		log = new Log(this.getClass());
		//driverDatabase();
		db = new Database("localhost", "root", "");
	}

	public static void main(String[] args) {
		NeuroCore nc = new NeuroCore();

		nc.iniciarCerebro();
		nc.SelHemisferio();
	}

	private void SelHemisferio() {
		while (!core.getHemis().isIniciado()) {
			log.print("Selecciona lado (derecho/izquierdo):");
			String st = log.readStr(sc).toLowerCase();
			
			if (st.equals("d")) {
				core.sethIzquierdo(true);
			} else if (st.equals("i")) {
				core.sethDerecho(true);
			} else {
				log.println("Lado incorrecto.");
			}

		}
		if (core.getHemis().ishDerecho()) {
			log.println("Has seleccionado el lado izquierdo. Hemisferio derecho en marcha.");
		} else {
			log.println("Has seleccionado el lado derecho. Hemisferio izquierdo en marcha.");
		}
		
		String input = "";
		while (!input.equals("stop")) {
			log.print("Yo: ");
			input = log.readStr(sc);
			
			log.println("NC: " + core.generarRespuesta(input) + "\n"); //TODO
		}
		
		stopCerebro();
	}

	private void iniciarCerebro() {
		log.println("Inicializando...");
		core = new Cerebro(db);
	}

	private void stopCerebro() {
		log.println("Finalizando...");
		core.stop();
		log.println("Finalizado.");
		log.close();
		System.exit(1);
	}

	private void driverDatabase() {
		log.debug("Cargando driver...");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			log.debug("Driver cargado!");
		} catch (ClassNotFoundException e) {
			log.error("Driver no encontrado");
			throw new IllegalStateException("", e);
		}
	}
}
