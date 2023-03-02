package study.model;

import lombok.*;

import java.util.Objects;


/**
 * Lombok
 * - @Data 어노테이션은 아래의 5가지 어노테이션이 포함되어 있다.
 * 1. @Getter: 클래스 필드의 getter 함수를 만들어 준다. 클래스 위가 아닌 필드 위에 적용하면 해당 필드에 대한 getter 함수만 생성 된다.
 * 2. @Setter: 클래스 필드의 setter 함수를 만들어 준다. 클래스 위가 아닌 필드 위에 적용하면 해당 필드에 대한 setter 함수만 생성 된다.
 * 3. @ToString: 클래스의 필드를 기반으로 toString 함수를 자동으로 만들어 준다.
 * 4. @NoArgsConstructor: 매개변수가 없는 생성자를 만들어 준다.
 * 5. @AllArgsConstructor: 모든 필드에 대한 매개변수가 있는 생성자를 만들어준다.
 */
// @Data
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {
    // Unique Id
    private String name;
    private int age;
    private String phone;
    private String email;

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

    public UserDTO toDTO() {
        return new UserDTO(this.getName(), this.getAge(), this.getPhone(), this.getEmail());
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
}
