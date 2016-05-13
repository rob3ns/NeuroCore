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

import Cerebro.Cerebro;
import Conexion.Database;
import java.util.Scanner;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 * @author rob3ns
 */
public class NeuroCore {

    private Cerebro core;
    private final Scanner sc;
    private Database db;

    public NeuroCore() {
        sc = new Scanner(System.in);
        driverDatabase();
        db = new Database("localhost", "root", "");
    }

    public static void main(String[] args) {
        NeuroCore nc = new NeuroCore();

        nc.iniciarCerebro();
        nc.SelHemisferio();
    }

    private void SelHemisferio() {
        while (!core.getHemis().ishDerecho() && !core.getHemis().ishIzquierdo()) {
            System.out.println("Selecciona lado (derecha-izquierda):");
            String st = sc.nextLine().toLowerCase();
            switch (st) {
                case "derecha":
                    core.sethIzquierdo(true);
                    break;
                case "izquierda":
                    core.sethDerecho(true);
                    break;
                default:
                    System.out.println("Lado incorrecto.");
                    break;
            }
        }
        if (core.getHemis().ishDerecho()) {
            System.out.println("Has seleccionado el lado izquierdo. Hemisferio derecho en marcha.");
        } else {
            System.out.println("Has seleccionado el lado derecho. Hemisferio izquierdo en marcha.");
        }
    }

    private void iniciarCerebro() {
        System.out.println("Inicializando...");
        core = new Cerebro();
    }
    
    private void stopCerebro() {
        System.out.println("Finalizando...");
        //core.stop();
    }

    private void driverDatabase() {
        System.out.println("Cargando driver...");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver cargado!");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("No se ha encontrado el driver!", e);
        }
    }
}
