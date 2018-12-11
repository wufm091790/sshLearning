package com.domain;


import javax.persistence.*;

@Entity
@Table(name="User", catalog="spring")
public class User implements java.io.Serializable {

    private static final long serialVersionUID = -2341818593980450829L;

    private Integer id;
    private String userName;
    private String password;

    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", userName=" + userName + ", password="
                + password + "]";
    }
}
