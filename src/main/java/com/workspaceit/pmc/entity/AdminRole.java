package com.workspaceit.pmc.entity;

import javax.persistence.*;

/**
 * Created by anik on 12/22/17.
 */


@Entity
@Table(name = "admin_roles")
public class AdminRole {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "role")
    private String role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "AdminRole{" +
                "id=" + id +
                ", role='" + role + '\'' +
                '}';
    }
}
