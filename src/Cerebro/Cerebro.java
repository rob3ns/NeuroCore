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

import Cerebro.Hemisferios.Hemisferio;
import Cerebro.Materia.MBlanca;
import Cerebro.Materia.MGris;
import java.util.ArrayList;

/**
 * @author rob3ns
 */
public class Cerebro {

	private ArrayList<Neurona> neuronas;
	private MBlanca matBlanca; // conecta neuronas (axones) entre hemisferios y lobulos
	private MGris matGris; // procesa info
	private Hemisferio hemis;

	public Cerebro() {
		this.neuronas = new ArrayList<Neurona>();
		this.matBlanca = new MBlanca(this);
		this.matGris = new MGris();
		this.hemis = new Hemisferio();
	}

	public String generarRespuesta(String s) {
		String res = "";
		//TODO:
		return res;
	}
	public ArrayList<Neurona> getNeuronas() {
		return neuronas;
	}

	public void setNeuronas(ArrayList<Neurona> neuronas) {
		this.neuronas = neuronas;
	}

	public MBlanca getMblanca() {
		return matBlanca;
	}

	public void setMblanca(MBlanca mblanca) {
		this.matBlanca = mblanca;
	}

	public MGris getMgris() {
		return matGris;
	}

	public void setMgris(MGris mgris) {
		this.matGris = mgris;
	}

	public Hemisferio getHemis() {
		return hemis;
	}

	public void setHemis(Hemisferio hemis) {
		this.hemis = hemis;
	}

	public void sethDerecho(boolean hDerecho) {
		this.hemis.sethDerecho(hDerecho);
		this.hemis.sethIzquierdo(!hDerecho);
	}

	public void sethIzquierdo(boolean hIzquierdo) {
		this.hemis.sethIzquierdo(hIzquierdo);
		this.hemis.sethDerecho(!hIzquierdo);
	}

	/**
	 * Pasar datos al otro hemisf (cl -> serv)
	 * Llamada: TODO
	 */
	 public void transferHemisferioOpuesto() {
		 //TODO: determinar que neuronas son las que se envian
		 this.matBlanca.transferencia(neuronas);
	 }

	 /**
	  * Recibir datos del otro hemisf
	  * Llamada en servidor
	  */
	 public void reciTransferencia() {
		 this.neuronas.addAll(matGris.pasarInfo());
	 }
	 
	 public void stop() {
		 matBlanca.stop();
	 }
}
