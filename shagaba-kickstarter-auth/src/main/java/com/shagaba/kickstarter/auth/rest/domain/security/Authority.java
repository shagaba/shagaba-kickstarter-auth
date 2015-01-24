package com.shagaba.kickstarter.auth.rest.domain.security;


public class Authority {

    protected String id;

    protected String description;
    
    /**
     * 
     */
    public Authority() {
        super();
    }

    /**
     * @param id
     * @param description
     */
    public Authority(String id, String description) {
        super();
        this.id = id;
        this.description = description;
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

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Authority [id=" + id + ", description=" + description + "]";
    }

}
