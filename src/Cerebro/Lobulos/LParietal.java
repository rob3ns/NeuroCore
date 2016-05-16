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

// Sensaciones, lenguaje, percepción

import Cerebro.Lobulos.Parietal.Atencion;
import Cerebro.Lobulos.Parietal.ConcienciaCorporal;
import Cerebro.Lobulos.Parietal.PercepcionPar;
import Cerebro.Lobulos.Parietal.Sensaciones;
import Cerebro.Lobulos.Temporal.Lenguaje;

// Conciencia corporal, atención
/**
 * @author rob3ns
 */
public class LParietal extends Lobulo{
    
    private Atencion atencion;
    private ConcienciaCorporal conCorp;
    private Lenguaje lenguaje;
    private PercepcionPar percepcion; //TODO: l occip
    private Sensaciones sensaciones;
    
    public LParietal() {
        atencion = new Atencion();
        conCorp = new ConcienciaCorporal();
        lenguaje = new Lenguaje();
        percepcion = new PercepcionPar();
        sensaciones = new Sensaciones();
    }
}
