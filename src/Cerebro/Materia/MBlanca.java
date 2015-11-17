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

import Cerebro.Cerebro;
import Cerebro.Neurona;
import Conexion.Cliente;
import Conexion.Servidor;
import java.util.ArrayList;

/**
 * @author rob3ns
 */
// Conex entre neuronas -axones - sockets y demas entre hemisferios
public class MBlanca {

    private Cliente cl;
    private Servidor sv;

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
        sv = new Servidor(c);
        sv.start();
    }

    /**
     * Transfer. al otro hemisf
     * @param neuronas Lo que pasas al otro, Array
     */
    public void transferencia(ArrayList<Neurona> neuronas) {
        cl = new Cliente(neuronas);
        if (!cl.isAlive()) { // dudo que este llegue a pasar
            cl.start();
        }
    }
}
