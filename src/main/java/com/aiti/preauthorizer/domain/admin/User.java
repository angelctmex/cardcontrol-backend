package com.aiti.preauthorizer.domain.admin;

import com.aiti.preauthorizer.domain.app.UserAuthorityEntity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by zeus on 9/30/16.
 */
@Entity
@Table(name = "app_users", schema = "public", catalog = "preauthjava")
public class User {
    private String username;
    private String password;
    private Boolean activated;
    private Timestamp dateCreated;
    private Timestamp dateUpdated;
    private Collection<UserAuthorityEntity> userAuthoritiesByUsername;

    @Id
    @Column(name = "username", nullable = false, length = -1)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password", nullable = false, length = -1)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "activated", nullable = true)
    public Boolean isActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    @Basic
    @Column(name = "date_created")
    public Timestamp getDateCreated() { return dateCreated; }

    public void setDateCreated(Timestamp dateCreated) { this.dateCreated = dateCreated; }

    @Basic
    @Column(name = "date_updated")
    public Timestamp getDateUpdated() { return dateUpdated; }

    public void setDateUpdated(Timestamp dateUpdated) { this.dateUpdated = dateUpdated; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User that = (User) o;

        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (activated != null ? !activated.equals(that.activated) : that.activated != null) return false;
        if (dateCreated != null ? !dateCreated.equals(that.dateCreated) : that.dateCreated != null) return false;
        if (dateUpdated != null ? !dateUpdated.equals(that.dateUpdated) : that.dateUpdated != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (activated != null ? activated.hashCode() : 0);
        result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0);
        result = 31 * result + (dateUpdated != null ? dateUpdated.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "usersByUsername")
    public Collection<UserAuthorityEntity> getUserAuthoritiesByUsername() {
        return userAuthoritiesByUsername;
    }

    public void setUserAuthoritiesByUsername(Collection<UserAuthorityEntity> userAuthoritiesByUsername) {
        this.userAuthoritiesByUsername = userAuthoritiesByUsername;
    }
}
