package com.thomazcollet.demo.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Columns;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.scheduling.config.Task;

@Entity
@Table(name = Tasks.TABLE_NAME)
public class Tasks {
    public static final String TABLE_NAME = "tasks";
    
    // Atrubutos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "users_id", nullable = false, updatable = false)
    private Users users;

    @Column(name = "description", length = 250, nullable = false)
    @NotNull
    @NotEmpty
    private String description;

    // Métodos construtores

    public Tasks() {
    }


    public Tasks(long id, Users users, String description) {
        this.id = id;
        this.users = users;
        this.description = description;
    }

    // Getters e Setters

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUsers() {
        return this.users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

@Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Task))
            return false;
        Tasks other = (Tasks) obj;
        if (this.id == null)
            if (other.id != null)
                return false;
            else if (!this.id.equals(other.id))
                return false;
        return Objects.equals(this.id, other.id) && Objects.equals(this.users, other.users)
                && Objects.equals(this.description, other.description);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        return result;
    }
        

}
