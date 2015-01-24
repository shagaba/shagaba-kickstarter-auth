package com.shagaba.kickstarter.auth.rest.domain.security;

import java.util.List;
public class Role {

    protected String id;

    protected String description;
    
    protected List<String> authorities; 
    
    protected Integer duration;

    
    /**
     * 
     */
    public Role() {
        super();
    }
    
    

    /**
     * @param id
     * @param description
     * @param authorities
     * @param duration
     */
    public Role(String id, String description, List<String> authorities, Integer duration) {
        super();
        this.id = id;
        this.description = description;
        this.authorities = authorities;
        this.duration = duration;
    }



    /**
     * @return the id
     */
    public String getId() {
        return id;
    }



    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }



    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }



    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }



    /**
     * @return the authorities
     */
    public List<String> getAuthorities() {
        return authorities;
    }



    /**
     * @param authorityIds the authorityIds to set
     */
    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }



    /**
     * @return the duration
     */
    public Integer getDuration() {
        return duration;
    }



    /**
     * @param duration the duration to set
     */
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

}
