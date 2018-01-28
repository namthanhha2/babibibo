/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.havm.babibibo.frontend.BO;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Vu Manh Ha
 */
@Entity
@Table(name = "role_module")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RoleModule.findAll", query = "SELECT r FROM RoleModule r"),
    @NamedQuery(name = "RoleModule.findByRoleId", query = "SELECT r FROM RoleModule r WHERE r.roleModulePK.roleId = :roleId"),
    @NamedQuery(name = "RoleModule.findByModuleId", query = "SELECT r FROM RoleModule r WHERE r.roleModulePK.moduleId = :moduleId"),
    @NamedQuery(name = "RoleModule.findByStatus", query = "SELECT r FROM RoleModule r WHERE r.status = :status")})
public class RoleModule implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RoleModulePK roleModulePK;
    @Column(name = "status")
    private Integer status;

    public RoleModule() {
    }

    public RoleModule(RoleModulePK roleModulePK) {
        this.roleModulePK = roleModulePK;
    }

    public RoleModule(int roleId, int moduleId) {
        this.roleModulePK = new RoleModulePK(roleId, moduleId);
    }

    public RoleModulePK getRoleModulePK() {
        return roleModulePK;
    }

    public void setRoleModulePK(RoleModulePK roleModulePK) {
        this.roleModulePK = roleModulePK;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roleModulePK != null ? roleModulePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RoleModule)) {
            return false;
        }
        RoleModule other = (RoleModule) object;
        if ((this.roleModulePK == null && other.roleModulePK != null) || (this.roleModulePK != null && !this.roleModulePK.equals(other.roleModulePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.havm.babibibo.frontend.BO.RoleModule[ roleModulePK=" + roleModulePK + " ]";
    }
    
}
