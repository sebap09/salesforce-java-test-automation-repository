package com.example.api.responses.soap;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Getter;

@Getter
@XmlAccessorType(XmlAccessType.FIELD)
public class Result {

    @XmlElement(namespace = "urn:partner.soap.sforce.com")
    private String metadataServerUrl;

    @XmlElement(namespace = "urn:partner.soap.sforce.com")
    private boolean passwordExpired;

    @XmlElement(namespace = "urn:partner.soap.sforce.com")
    private boolean sandbox;

    @XmlElement(namespace = "urn:partner.soap.sforce.com")
    private String serverUrl;

    @XmlElement(namespace = "urn:partner.soap.sforce.com")
    private String sessionId;

    @XmlElement(namespace = "urn:partner.soap.sforce.com")
    private String userId;

    @XmlElement(namespace = "urn:partner.soap.sforce.com")
    private int userInfo;
}