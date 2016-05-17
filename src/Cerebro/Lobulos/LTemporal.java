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

package Cerebro.Lobulos;

// Audición, lenguaje, memoria

import Cerebro.Cerebro;
import Cerebro.Lobulos.Temporal.Audicion;
import Cerebro.Lobulos.Temporal.Lenguaje;
import Cerebro.Lobulos.Temporal.Memoria.Memoria;
import Cerebro.Lobulos.Temporal.Memoria.MemoriaC;
import Cerebro.Lobulos.Temporal.Memoria.MemoriaL;

/**
 * @author rob3ns
 */
public class LTemporal extends Lobulo{
    
    private Audicion audicion;
    private Lenguaje lenguaje;
    private MemoriaC memC;
    private MemoriaL memL;
    
    public LTemporal(Cerebro c) {
        audicion = new Audicion();
        lenguaje = new Lenguaje();
        memC = new MemoriaC(c);
        memL = new MemoriaL(c);
    }
}
