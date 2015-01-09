package com.shagaba.kickstarter.auth.core.domain.security.account;

import java.util.Date;

public class UserAccountRole {
    
    protected String roleId;
    
    protected Date experationTime;
    
    /**
     * 
     */
    public UserAccountRole() {
        super();
    }

    /**
     * @param roleId
     * @param experationTime
     */
    public UserAccountRole(String roleId, Date experationTime) {
        super();
        this.roleId = roleId;
        this.experationTime = experationTime;
    }

    /**
     * @return the roleId
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * @param roleId the roleId to set
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    /**
     * @return the experationTime
     */
    public Date getExperationTime() {
        return experationTime;
    }

    /**
     * @param experationTime the experationTime to set
     */
    public void setExperationTime(Date experationTime) {
        this.experationTime = experationTime;
    }

  }
