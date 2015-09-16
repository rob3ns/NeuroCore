/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cerebro;

import java.util.BitSet;
import java.util.LinkedList;

/**
 *
 * @author Rubén
 */
public class Neurona {
    private LinkedList<Neurona> axones; // axones conectados a mi dentrita, envia info a 20.000
    private LinkedList<Neurona> dendritas; // dentritas conectadas a mi axion ,recibe info de 20.000
    private BitSet nucleo; // informacion

    public Neurona() {
        this.axones =  new LinkedList();
        this.dendritas =  new LinkedList();
        this.nucleo = new BitSet();
    }
    
    public void CrearSinapsis (Neurona neu, boolean recibe)
    {
        if (recibe) // Recibe info de una dendrita
            this.dendritas.add(neu);
        else
            this.axones.add(neu);
    }
    
    /**
     * Pasamos toda nuestra info a la neurona neu.
     * @param neu 
     */
    public void SinapsisUnidir (Neurona neu)
    {
        for (Neurona n : axones)
            neu.getNucleo().or(n.getNucleo()); // A la neurona que vamos a enviar le pasamos la info de todas las demas a/d.
    }

    public void SinapsisBidir()
    {
        for (Neurona n : dendritas) // Recibe info
            this.nucleo.or(n.getNucleo());
                
        for (Neurona n : axones) // Envia info
            n.getNucleo().or(this.nucleo);
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
