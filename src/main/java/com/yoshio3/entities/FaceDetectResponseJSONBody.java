/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yoshio3.entities;

import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Yoshio Terada
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FaceDetectResponseJSONBody {

    private String faceId;
    private Map<String, Object> faceRectangle;
    private Map<String, Object> faceAttributes;

    /**
     * @return the faceId
     */
    public String getFaceId() {
        return faceId;
    }

    /**
     * @param faceId the faceId to set
     */
    public void setFaceId(String faceId) {
        this.faceId = faceId;
    }

    /**
     * @return the faceRectangle
     */
    public Map<String, Object> getFaceRectangle() {
        return faceRectangle;
    }

    /**
     * @param faceRectangle the faceRectangle to set
     */
    public void setFaceRectangle(Map<String, Object> faceRectangle) {
        this.faceRectangle = faceRectangle;
    }

    /**
     * @return the faceAttributes
     */
    public Map<String, Object> getFaceAttributes() {
        return faceAttributes;
    }

    /**
     * @param faceAttributes the faceAttributes to set
     */
    public void setFaceAttributes(Map<String, Object> faceAttributes) {
        this.faceAttributes = faceAttributes;
    }

    @Override
    public String toString() {
        return "FaceDetectResponseJSONBody{" + "faceId=" + faceId + ", faceRectangle=" + faceRectangle + ", faceAttributes=" + faceAttributes + '}';
    }
}
