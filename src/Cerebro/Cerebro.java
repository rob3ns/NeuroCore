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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import Utils.Caster;
import Cerebro.Hemisferios.Hemisferio;
import Cerebro.Lobulos.LFrontal;
import Cerebro.Lobulos.LOccipital;
import Cerebro.Lobulos.LParietal;
import Cerebro.Lobulos.LTemporal;
import Cerebro.Materia.MBlanca;
import Cerebro.Materia.MGris;
import Conexion.Database;

/**
 * @author rob3ns
 */
public class Cerebro {

	private Map<Integer, Neurona> neuronas;
	private MBlanca matBlanca; // conecta neuronas (axones) entre hemisferios y lobulos
	private MGris matGris; // procesa info
	private Hemisferio hemis;
	
	private Database db;
	
	//Lobulos
	private LTemporal lTemp;
	private LOccipital lOcc;
	private LParietal lPar;
	private LFrontal lFron;

	public Cerebro(Database db) {
		neuronas = new LinkedHashMap<Integer, Neurona>();
		matBlanca = new MBlanca(this);
		matGris = new MGris();
		hemis = new Hemisferio();
		lTemp = new LTemporal(this);
		lOcc = new LOccipital();
		lPar = new LParietal();
		lFron = new LFrontal();
		this.db = db;
	}

	public String generarRespuesta(String s) {
		String res = "";
		//TODO:
		return res;
	}

	public Map<Integer, Neurona> getNeuronas() {
		return neuronas;
	}

	public void setNeuronas(Map<Integer, Neurona> neuronas) {
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
		 Integer key = calcLastKey();
		 
		 ArrayList<Neurona> info = matGris.pasarInfo();
		 for (Neurona neu : info) {
			 neuronas.put(key++, neu);
		 }
	 }
	 
	 private Integer calcLastKey() {
		 Integer num = 0;
		 for (Integer i : neuronas.keySet()) {
			 num = i;
		 }

		 return num;
	 }
	 
	 public void stop() {
		 matBlanca.stop();
	 }

	public Database getDb() {
		return db;
	}

	public void setDb(Database db) {
		this.db = db;
	}
	 
}
