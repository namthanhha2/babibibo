/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.havm.babibibo.backend.BO;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Vu Manh Ha
 */
@Entity
@Table(name = "modules")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Modules.findAll", query = "SELECT m FROM Modules m"),
    @NamedQuery(name = "Modules.findByModuleId", query = "SELECT m FROM Modules m WHERE m.moduleId = :moduleId"),
    @NamedQuery(name = "Modules.findByName", query = "SELECT m FROM Modules m WHERE m.name = :name"),
    @NamedQuery(name = "Modules.findByStyle", query = "SELECT m FROM Modules m WHERE m.style = :style"),
    @NamedQuery(name = "Modules.findByImgPath", query = "SELECT m FROM Modules m WHERE m.imgPath = :imgPath"),
    @NamedQuery(name = "Modules.findByStatus", query = "SELECT m FROM Modules m WHERE m.status = :status")})
public class Modules implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "module_id")
    private Integer moduleId;
    @Size(max = 250)
    @Column(name = "name")
    private String name;
    @Size(max = 250)
    @Column(name = "style")
    private String style;
    @Size(max = 250)
    @Column(name = "img_path")
    private String imgPath;
    @Column(name = "status")
    private Integer status;
    @Column(name = "link")
    private String link;

    public Modules() {
    }

    public Modules(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (moduleId != null ? moduleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Modules)) {
            return false;
        }
        Modules other = (Modules) object;
        if ((this.moduleId == null && other.moduleId != null) || (this.moduleId != null && !this.moduleId.equals(other.moduleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.havm.babibibo.frontend.BO.Modules[ moduleId=" + moduleId + " ]";
    }
   
}
