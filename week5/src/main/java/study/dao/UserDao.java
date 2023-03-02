package study.dao.user;

import study.vo.user.UserVO;

import java.util.List;

public interface UserDao {
    List<UserVO> findAll();

    UserVO findByName(String name);
    List<UserVO> findByAge(int age);

    List<UserVO> findByEmailContains(String email);
}
