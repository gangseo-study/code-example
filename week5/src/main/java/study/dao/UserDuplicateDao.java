package study.dao;

import org.springframework.stereotype.Component;
import study.model.UserDTO;
import study.model.UserVO;

import java.util.List;


/**
 * - @Qualifier 예제를 위해 생성한 dummy class
 */
@Component("userDuplicateDao")
public class UserDuplicateDao implements UserDao{
    @Override
    public List<UserVO> findAll() {
        return null;
    }

    @Override
    public UserVO findByName(String name) {
        return null;
    }

    @Override
    public List<UserVO> findByAge(int age) {
        return null;
    }

    @Override
    public List<UserVO> findByEmailContains(String email) {
        return null;
    }

    @Override
    public UserDTO insertUser(UserVO toVO) {
        return null;
    }

    @Override
    public String deleteUser(String name) throws Exception {
        return null;
    }

    @Override
    public UserDTO updateUser(UserVO dto) throws Exception {
        return null;
    }
}
