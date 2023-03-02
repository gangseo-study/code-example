package study.service.user;

import org.springframework.stereotype.Service;
import study.dao.user.UserDao;
import study.vo.user.UserVO;

import java.util.List;

@Service
public class UserService {

    UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserVO readUserByName(String name) {
        UserVO result = userDao.findByName(name);
        System.out.println(result);
        return result;
    }

    public List<UserVO> readUserByAge(int age) {
        List<UserVO> result = userDao.findByAge(age);
        result.forEach(r -> System.out.println(r));
        return result;
    }

    public List<UserVO> readUserAll() {
        return userDao.findAll();
    }

    public Object sumAgeByEmailContains(String email) {
        int result = userDao.findByEmailContains(email).stream().mapToInt(u -> u.getAge()).sum();
        System.out.println("sum: " + result);
        return result;
    }
}
