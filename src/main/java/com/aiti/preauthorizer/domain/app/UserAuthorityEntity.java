package com.aiti.preauthorizer.domain.app;

import com.aiti.preauthorizer.domain.admin.Authority;
import com.aiti.preauthorizer.domain.admin.User;

import javax.persistence.*;

/**
 * Created by zeus on 9/30/16.
 */
@Entity
@Table(name = "user_authority", schema = "public", catalog = "preauthjava")
@IdClass(UserAuthorityEntityPK.class)
public class UserAuthorityEntity {
    private String username;
    private String authority;
    private User usersByUsername;
    private Authority authorityByAuthority;

    @Id
    @Column(name = "username", nullable = false, length = -1)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Id
    @Column(name = "authority", nullable = false, length = -1)
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

        UserAuthorityEntity that = (UserAuthorityEntity) o;

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

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username", nullable = false, insertable = false, updatable = false)
    public User getUsersByUsername() {
        return usersByUsername;
    }

    public void setUsersByUsername(User usersByUsername) {
        this.usersByUsername = usersByUsername;
    }

    @ManyToOne
    @JoinColumn(name = "authority", referencedColumnName = "name", nullable = false, insertable = false, updatable = false)
    public Authority getAuthorityByAuthority() {
        return authorityByAuthority;
    }

    public void setAuthorityByAuthority(Authority authorityByAuthority) {
        this.authorityByAuthority = authorityByAuthority;
    }
}
