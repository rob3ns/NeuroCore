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
import java.util.Scanner;

/**
 * @author rob3ns
 */
public class NeuroCore {

    private static Cerebro core = new Cerebro();

    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);

        while (!core.getHemis().ishDerecho() && !core.getHemis().ishIzquierdo())
        {
            System.out.println("Selecciona lado (derecha-izquierda):");
            SelHemisferio(sc);
        }
        if (core.getHemis().ishDerecho())
            System.out.println("Has seleccionado el lado izquierdo. Hemisferio derecho en marcha.");
        else
                        System.out.println("Has seleccionado el lado derecho. Hemisferio izquierdo en marcha.");
    }
    
    public static void SelHemisferio (Scanner sc)
    {
        String st  = sc.nextLine().toLowerCase();
        if (st.equals("derecha")) // Hizq controla lado der
            core.sethIzquierdo(true);
        else if (st.equals("izquierda"))
            core.sethDerecho(true);
    }
}
