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

// movimiento, resolución problemas, concentración, pensamiento

import Cerebro.Lobulos.Frontal.Concentracion;
import Cerebro.Lobulos.Frontal.Movimiento;
import Cerebro.Lobulos.Frontal.Personalidad;
import Cerebro.Lobulos.Frontal.ResProblemas;

// personalidad, humor
/**
 * @author rob3ns
 */
public class LFrontal extends Lobulo{
    
    private Concentracion concentr;
    private Movimiento mov;
    private Personalidad pers;
    private ResProblemas resProblemas;
    
    public LFrontal() {
        concentr = new Concentracion();
        mov = new Movimiento();
        pers = new Personalidad();
        resProblemas = new ResProblemas();
    }
}
