package com.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class UserModel implements Serializable {

    private static final long serialVersionUID = -2266673129840350430L;

    private Map<String, User> users = new LinkedHashMap<>();

    public Map<String, User> getUsers() {
        return users;
    }

    public void setUsers(Map<String, User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        UserModel userModel = (UserModel) o;

        return new EqualsBuilder()
                .append(users, userModel.users)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(users)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("users", users)
                .toString();
    }

    public static class User implements Serializable{

        private static final long serialVersionUID = 8551995824751415974L;
        private String userId;
        private String firstName;
        private String lastName;
        private int age;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;

            if (o == null || getClass() != o.getClass()) return false;

            User user = (User) o;

            return new EqualsBuilder()
                    .append(age, user.age)
                    .append(userId, user.userId)
                    .append(firstName, user.firstName)
                    .append(lastName, user.lastName)
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37)
                    .append(userId)
                    .append(firstName)
                    .append(lastName)
                    .append(age)
                    .toHashCode();
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("userId", userId)
                    .append("firstName", firstName)
                    .append("lastName", lastName)
                    .append("age", age)
                    .toString();
        }

    }

}
