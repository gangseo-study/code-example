package study.service;

import study.dao.UserDao;
import study.vo.UserVO;

import java.util.List;

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
        List<UserVO> result = userDao.findAll();
        result.sort((o1, o2) -> {
            if (o1.getEmail().length() > o2.getEmail().length()) return -1;
            return o1.getEmail().length() < o2.getEmail().length() ? 1 : 0;
        });
        result.forEach(r -> System.out.println(r));
        return result;
    }

    public Object sumAgeByEmailContains(String email) {
        int result = userDao.findByEmailContains(email).stream().mapToInt(u -> u.getAge()).sum();
        System.out.println("sum: " + result);
        return result;
    }
}
