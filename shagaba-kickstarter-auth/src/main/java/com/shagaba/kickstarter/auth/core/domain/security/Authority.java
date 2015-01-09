package com.shagaba.kickstarter.auth.core.domain.security;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.shagaba.kickstarter.auth.core.common.audit.AuditingDomain;

@Document
public class Authority extends AuditingDomain {

    @Id
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

}
