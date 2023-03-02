package study.dao;

import study.model.UserDTO;
import study.model.UserVO;

import java.util.List;

public interface UserDao {
    List<UserVO> findAll();

    UserVO findByName(String name);
    List<UserVO> findByAge(int age);

    List<UserVO> findByEmailContains(String email);

    UserDTO insertUser(UserVO toVO) throws Exception;
}
