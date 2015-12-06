package ru.iklyubanov.diploma.saga.core.axon.util;

import org.axonframework.common.Assert;
import org.axonframework.domain.IdentifierFactory;

import java.io.Serializable;

/**
 * Created by kliubanov on 27.11.2015.
 */
public class TransactionId implements Serializable {
    private static final long serialVersionUID = -5267104328616955617L;

    private String identifier;

    public TransactionId() {
        this.identifier = IdentifierFactory.getInstance().generateIdentifier();
    }

    public TransactionId(String identifier) {
        Assert.notNull(identifier, "Identifier may not be null");
        this.identifier = identifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransactionId that = (TransactionId) o;

        return identifier.equals(that.identifier);

    }

    @Override
    public int hashCode() {
        return identifier.hashCode();
    }

    @Override
    public String toString() {
        return identifier;
    }
}
