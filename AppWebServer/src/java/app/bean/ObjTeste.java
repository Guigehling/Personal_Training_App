/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Guilherme Gehling
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ObjTeste {

    private String letra1, letra2;

    public ObjTeste() {
    }

    public String getLetra1() {
        return letra1;
    }

    public void setLetra1(String letra1) {
        this.letra1 = letra1;
    }

    public String getLetra2() {
        return letra2;
    }

    public void setLetra2(String letra2) {
        this.letra2 = letra2;
    }
    
}
