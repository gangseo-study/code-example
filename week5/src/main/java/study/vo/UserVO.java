package study.vo;

import java.util.Objects;

public class UserVO {
    // Unique Id
    private String name;
    private int age;
    private String phone;
    private String email;

    public UserVO(String name, int age, String phone, String email) {
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserVO userVO = (UserVO) o;
        return age == userVO.age && name.equals(userVO.name) && phone.equals(userVO.phone) && email.equals(userVO.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, phone, email);
    }

    @Override
    public String toString() {
        return "User {" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
