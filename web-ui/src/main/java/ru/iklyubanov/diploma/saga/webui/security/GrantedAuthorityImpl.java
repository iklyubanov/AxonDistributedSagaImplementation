package ru.iklyubanov.diploma.saga.webui.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

/**
 * Created by ivan on 12/20/2015.
 */
@Deprecated
public class GrantedAuthorityImpl implements GrantedAuthority {
    private static final long serialVersionUID = 320L;
    private final String role;

    public GrantedAuthorityImpl(String role) {
        Assert.hasText(role, "A granted authority textual representation is required");
        this.role = role;
    }

    public boolean equals(Object obj) {
        if(obj instanceof String) {
            return obj.equals(this.role);
        } else if(obj instanceof GrantedAuthority) {
            GrantedAuthority attr = (GrantedAuthority)obj;
            return this.role.equals(attr.getAuthority());
        } else {
            return false;
        }
    }

    public String getAuthority() {
        return this.role;
    }

    public int hashCode() {
        return this.role.hashCode();
    }

    public String toString() {
        return this.role;
    }
}
