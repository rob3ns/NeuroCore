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
package Cerebro;

import java.util.BitSet;
import java.util.LinkedList;

import Utils.Caster;

/**
 * @author rob3ns
 */
public class Neurona {

	private LinkedList<Neurona> axones; // axones conectados a mi dentrita, envia info a 20.000
	private LinkedList<Neurona> dendritas; // dentritas conectadas a mi axion ,recibe info de 20.000
	private BitSet nucleo; // informacion

	/**
	 * Inicializada en Cerebro
	 */
	public Neurona() {
		this.axones = new LinkedList<Neurona>();
		this.dendritas = new LinkedList<Neurona>();
		this.nucleo = new BitSet();
	}

	public void CrearSinapsis(Neurona neu, boolean recibe) {
		if (recibe) {
			this.dendritas.add(neu);
		} else {
			this.axones.add(neu);
		}
	}

	/**
	 * Pasamos toda nuestra info a la neurona.
	 *
	 * @param neu Neurona
	 */
	public void SinapsisUnidir(Neurona neu) {
		for (Neurona n : Caster.safeIterable(axones)) {
			neu.getNucleo().or(n.getNucleo()); // A la neurona que vamos a enviar le pasamos la info de todas las demas a/d.
		}
	}

	public void SinapsisBidir() {
		for (Neurona n : Caster.safeIterable(dendritas)) { // Recibe info
			this.nucleo.or(n.getNucleo());
		}

		for (Neurona n : Caster.safeIterable(axones)) { // Envia info
			n.getNucleo().or(this.nucleo);
		}
	}

	public void nuevoString(final String s) {
		this.nucleo = Caster.stringToBitSet(s);
	}

	public String traducirInfo() {
		return Caster.bitSetToString(this.nucleo);
	}

	public LinkedList<Neurona> getAxones() {
		return axones;
	}

	public void setAxones(LinkedList<Neurona> axones) {
		this.axones = axones;
	}

	public LinkedList<Neurona> getDendritas() {
		return dendritas;
	}

	public void setDendritas(LinkedList<Neurona> dendritas) {
		this.dendritas = dendritas;
	}

	public BitSet getNucleo() {
		return nucleo;
	}

	public void setNucleo(BitSet nucleo) {
		this.nucleo = nucleo;
	}
}
