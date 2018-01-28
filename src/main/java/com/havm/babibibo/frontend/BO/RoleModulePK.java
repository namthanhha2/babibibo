/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.havm.babibibo.frontend.BO;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Vu Manh Ha
 */
@Embeddable
public class RoleModulePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "role_id")
    private int roleId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "module_id")
    private int moduleId;

    public RoleModulePK() {
    }

    public RoleModulePK(int roleId, int moduleId) {
        this.roleId = roleId;
        this.moduleId = moduleId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) roleId;
        hash += (int) moduleId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RoleModulePK)) {
            return false;
        }
        RoleModulePK other = (RoleModulePK) object;
        if (this.roleId != other.roleId) {
            return false;
        }
        if (this.moduleId != other.moduleId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.havm.babibibo.frontend.BO.RoleModulePK[ roleId=" + roleId + ", moduleId=" + moduleId + " ]";
    }
    
}
