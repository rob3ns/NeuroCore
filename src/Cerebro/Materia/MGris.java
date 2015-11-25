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

import Cerebro.Neurona;
import java.util.ArrayList;
import java.util.BitSet;

/**
 * @author rob3ns
 */
// Procesar info (bits)
public class MGris {

    private ArrayList<Neurona> neuronas;

    /**
     * Inicializada en Cerebro
     */
    public MGris() {
        //TODO
        neuronas = new ArrayList();
    }

    /**
     * Agrega una nueva neur a partir de los bytes Solo añade nucleo
     *
     * @param b
     */
    public void agregarBytes(byte[] b) {
        Neurona n = new Neurona();
        n.nuevoString(b.toString());

        neuronas.add(n);
    }

    /**
     * Return de neuronas y limpia las anteriores (eq. a flushbitreader)
     *
     * @return Array N
     */
    public ArrayList<Neurona> pasarInfo() {
        ArrayList<Neurona> ret = neuronas;
        neuronas.clear();
        return ret;
    }
}
