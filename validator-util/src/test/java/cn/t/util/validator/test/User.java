package cn.t.util.validator.test;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

/**
 * user
 *
 * @author yj
 * @since 2020-03-05 17:04
 **/
class User {
    @NotNull(message = "id-not-null")
    private Long id;
//    @NotBlank(message = "username-not-blank")
    private String username;
    @Email
    private String email;
    private String telephone;
    private Byte sex;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }
}
