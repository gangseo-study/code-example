package study.model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @NonNull private String name;
    private int age;
    private String phone;
    private String email;

    public UserVO toVO() {
        return new UserVO(this.getName(), this.getAge(), this.getPhone(), this.getEmail());
    }
}
