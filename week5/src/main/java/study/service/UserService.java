package study.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import study.dao.UserDao;
import study.model.UserDTO;
import study.model.UserVO;

import java.util.List;

/**
 * - @Service: @Component 어노테이션을 포함하며 이 클래스가 서비스 계층이라는 것을 명시적으로 알려준다.
 */
@Service
public class UserService {

    UserDao userDao;

    /**
     * - @Qualifier: 동일한 유형의 Bean이 2개 이상 존재할 때는 자동으로 주입할 수 없기 때문에
     * - 어떤 이름의 빈을 가져올지 명시적으로 작성하여 가져오는 것을 도와주는 어노테이션이다.
     */
    public UserService(@Qualifier("userFileDao") UserDao userDao) {
        this.userDao = userDao;
    }

    public UserVO readUserByName(String name) {
        return userDao.findByName(name);
    }

    public List<UserVO> readUserAll() {
        return userDao.findAll();
    }

    public UserDTO insertUser(UserDTO dto) throws Exception {
        return userDao.insertUser(dto.toVO());
    }

    public String deleteUser(String name) throws Exception {
        return userDao.deleteUser(name);
    }

    public UserDTO updateUser(UserDTO dto) throws Exception {
        return userDao.updateUser(dto.toVO());
    }
}
