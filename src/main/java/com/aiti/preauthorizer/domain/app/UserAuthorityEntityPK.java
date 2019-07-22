package com.aiti.preauthorizer.domain.app;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by zeus on 9/30/16.
 */
public class UserAuthorityEntityPK implements Serializable {
    private String username;
    private String authority;

    @Column(name = "username", nullable = false, length = -1)
    @Id
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "authority", nullable = false, length = -1)
    @Id
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserAuthorityEntityPK that = (UserAuthorityEntityPK) o;

        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (authority != null ? !authority.equals(that.authority) : that.authority != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (authority != null ? authority.hashCode() : 0);
        return result;
    }
}
