/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.bean;

import java.sql.Time;
import javax.xml.crypto.Data;

/**
 *
 * @author Guilherme Gehling
 */
public class Posicao {

    private int id;
    private String latitude, longitude;
    private double distancia;
    private Data data;
    private Time hora;
}
