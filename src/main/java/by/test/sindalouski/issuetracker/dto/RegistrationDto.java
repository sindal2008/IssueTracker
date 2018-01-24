package by.test.sindalouski.issuetracker.dto;

import javax.validation.constraints.Size;

public class RegistrationDto {
    @Size(min = 2, max = 32)
    private String login;

    @Size(min = 8)
    private String password;

    private Integer role = 2;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }
}
