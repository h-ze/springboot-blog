package com.hz.blog.entity;

public class UserRoles {
    private Integer id;
    private String userId;
    private Integer roleId;

    public UserRoles() {
    }

    public UserRoles(Integer id, String userId, Integer roleId) {
        this.id = id;
        this.userId = userId;
        this.roleId = roleId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "UserRoles{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", roleId=" + roleId +
                '}';
    }
}
