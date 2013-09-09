/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.bean;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Guilherme Gehling
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class LatLong implements Serializable {

    private Long id;
    private String latitude_inicial, longitude_inicial, latitude_final, longitude_final;
    private String distancia;

    public LatLong() {
    }

    public LatLong(Long id, String latitude_inicial, String longitude_inicial, String latitude_final, String longitude_final, String distancia) {
        this.id = id;
        this.latitude_inicial = latitude_inicial;
        this.longitude_inicial = longitude_inicial;
        this.latitude_final = latitude_final;
        this.longitude_final = longitude_final;
        this.distancia = distancia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLatitude_inicial() {
        return latitude_inicial;
    }

    public void setLatitude_inicial(String latitude_inicial) {
        this.latitude_inicial = latitude_inicial;
    }

    public String getLongitude_inicial() {
        return longitude_inicial;
    }

    public void setLongitude_inicial(String longitude_inicial) {
        this.longitude_inicial = longitude_inicial;
    }

    public String getLatitude_final() {
        return latitude_final;
    }

    public void setLatitude_final(String latitude_final) {
        this.latitude_final = latitude_final;
    }

    public String getLongitude_final() {
        return longitude_final;
    }

    public void setLongitude_final(String longitude_final) {
        this.longitude_final = longitude_final;
    }

    public String getDistancia() {
        return distancia;
    }

    public void setDistancia(String distancia) {
        this.distancia = distancia;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LatLong other = (LatLong) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("LatLong {id=%d latitude_inicial=%lti longitude_inicial=%loi latitude_final=%ltf longitude_final=%lof distancia=%dist}", id, latitude_inicial, longitude_inicial, latitude_final, longitude_final, distancia);
    }
}
