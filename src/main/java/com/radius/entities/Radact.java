/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.radius.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gkrosnicki
 */
@Entity
@Table(name = "radact")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Radact.findAll", query = "SELECT r FROM Radact r")})
public class Radact implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "sessionid")
    private String sessionid;
    @Column(name = "called")
    private String called;
    @Column(name = "calling")
    private String calling;
    @Column(name = "inputoctets")
    private Integer inputoctets;
    @Column(name = "outputoctets")
    private Integer outputoctets;
    @Column(name = "sessiontime")
    private Integer sessiontime;
    @Column(name = "username")
    private String username;
    @Column(name = "nasip")
    private String nasip;
    @Column(name = "setupdate")
    @Temporal(TemporalType.DATE)
    private Date setupdate;
    @Column(name = "setuptime")
    @Temporal(TemporalType.TIME)
    private Date setuptime;
    @Column(name = "callorigin")
    private String callorigin;
    @Column(name = "calltype")
    private String calltype;
    @Column(name = "protocol")
    private String protocol;
    @Column(name = "connectdate")
    @Temporal(TemporalType.DATE)
    private Date connectdate;
    @Column(name = "connecttime")
    @Temporal(TemporalType.TIME)
    private Date connecttime;
    @Column(name = "disconnectdate")
    @Temporal(TemporalType.DATE)
    private Date disconnectdate;
    @Column(name = "disconnecttime")
    @Temporal(TemporalType.TIME)
    private Date disconnecttime;
    @Column(name = "cause")
    private String cause;
    @Column(name = "framedip")
    private String framedip;
    @Column(name = "relsource")
    private String relsource;
    @Column(name = "mediaip")
    private String mediaip;
    @Column(name = "nasport")
    private String nasport;

    public Radact() {
    }

    public Radact(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    public String getCalled() {
        return called;
    }

    public void setCalled(String called) {
        this.called = called;
    }

    public String getCalling() {
        return calling;
    }

    public void setCalling(String calling) {
        this.calling = calling;
    }

    public Integer getInputoctets() {
        return inputoctets;
    }

    public void setInputoctets(Integer inputoctets) {
        this.inputoctets = inputoctets;
    }

    public Integer getOutputoctets() {
        return outputoctets;
    }

    public void setOutputoctets(Integer outputoctets) {
        this.outputoctets = outputoctets;
    }

    public Integer getSessiontime() {
        return sessiontime;
    }

    public void setSessiontime(Integer sessiontime) {
        this.sessiontime = sessiontime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNasip() {
        return nasip;
    }

    public void setNasip(String nasip) {
        this.nasip = nasip;
    }

    public Date getSetupdate() {
        return setupdate;
    }

    public void setSetupdate(Date setupdate) {
        this.setupdate = setupdate;
    }

    public Date getSetuptime() {
        return setuptime;
    }

    public void setSetuptime(Date setuptime) {
        this.setuptime = setuptime;
    }

    public String getCallorigin() {
        return callorigin;
    }

    public void setCallorigin(String callorigin) {
        this.callorigin = callorigin;
    }

    public String getCalltype() {
        return calltype;
    }

    public void setCalltype(String calltype) {
        this.calltype = calltype;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public Date getConnectdate() {
        return connectdate;
    }

    public void setConnectdate(Date connectdate) {
        this.connectdate = connectdate;
    }

    public Date getConnecttime() {
        return connecttime;
    }

    public void setConnecttime(Date connecttime) {
        this.connecttime = connecttime;
    }

    public Date getDisconnectdate() {
        return disconnectdate;
    }

    public void setDisconnectdate(Date disconnectdate) {
        this.disconnectdate = disconnectdate;
    }

    public Date getDisconnecttime() {
        return disconnecttime;
    }

    public void setDisconnecttime(Date disconnecttime) {
        this.disconnecttime = disconnecttime;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getFramedip() {
        return framedip;
    }

    public void setFramedip(String framedip) {
        this.framedip = framedip;
    }

    public String getRelsource() {
        return relsource;
    }

    public void setRelsource(String relsource) {
        this.relsource = relsource;
    }

    public String getMediaip() {
        return mediaip;
    }

    public void setMediaip(String mediaip) {
        this.mediaip = mediaip;
    }

    public String getNasport() {
        return nasport;
    }

    public void setNasport(String nasport) {
        this.nasport = nasport;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Radact)) {
            return false;
        }
        Radact other = (Radact) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.radius.entities.Radact[ id=" + id + " ]";
    }
    
}
