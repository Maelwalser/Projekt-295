package ch.noser.immobilien.domain.user.dto;

import ch.noser.immobilien.domain.role.Role;

import java.util.UUID;

public class UserDTO {

    private UUID id;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public static class WithAll extends UserDTO{
        private String email;
        private String firstname;

        private String lastname;


        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }
    }




}
