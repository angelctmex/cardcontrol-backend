package com.aiti.preauthorizer.domain.admin;

import com.aiti.preauthorizer.domain.app.UserAuthorityEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

/**
 * Created by zeus on 9/30/16.
 */
@Entity
@Table(name = "authority", schema = "public", catalog = "preauthjava")
public class Authority {
    private String name;
    private Collection<UserAuthorityEntity> userAuthoritiesByName;

    @Id
    @Column(name = "name", nullable = false, length = 50)
    @NotNull
    @Size(min = 0, max = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Authority that = (Authority) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @OneToMany(mappedBy = "authorityByAuthority")
    public Collection<UserAuthorityEntity> getUserAuthoritiesByName() {
        return userAuthoritiesByName;
    }

    public void setUserAuthoritiesByName(Collection<UserAuthorityEntity> userAuthoritiesByName) {
        this.userAuthoritiesByName = userAuthoritiesByName;
    }
}
