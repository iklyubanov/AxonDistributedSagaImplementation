package ru.iklyubanov.diploma.saga.jpa.util;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ivan on 11/24/2015.
 */
@MappedSuperclass
public class ParentEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Basic
    @Column(length = 36)
    private String identifier;

    @Version
    protected Integer version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + (this.getId() != null ? this.getId().hashCode() : 0);
        return hash;
    }
}
