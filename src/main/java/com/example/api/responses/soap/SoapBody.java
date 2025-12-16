package com.example.api.responses.soap;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Getter;

@Getter
@XmlAccessorType(XmlAccessType.FIELD)
public class SoapBody {

    @XmlElement(name = "loginResponse", namespace = "urn:partner.soap.sforce.com")
    private LoginResponse loginResponse;
}
