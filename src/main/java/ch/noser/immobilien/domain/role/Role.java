package ch.noser.immobilien.domain.role;


import ch.noser.immobilien.domain.user.User;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "roles")
public class Role{

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;


    @Column(name = "name")
    private String name;


    @OneToMany(mappedBy = "role")
    private List<User> users;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
