/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cerebro;

import Cerebro.Hemisferios.HDerecho;
import Cerebro.Hemisferios.HIzquierdo;
import Cerebro.Materia.MBlanca;
import Cerebro.Materia.MGris;
import java.util.ArrayList;

/**
 *
 * @author Rubén
 */
public class Cerebro {
    
    private ArrayList<Neurona> neuronas;
    private MBlanca mblanca; // conecta neuronas (axones) entre hemisferios y lobulos
    private MGris mgris; // procesa info
    private HDerecho hDer;
    private HIzquierdo hIzq;
}
