package study.service;

import study.dao.UserDao;
import study.vo.UserVO;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
        result.forEach(System.out::println);
        return result;
    }

    public List<UserVO> readUserAllOrderByEmailLength() {
        List<UserVO> result = userDao.findAll().stream().sorted(Comparator.comparing(r -> r.getEmail().length())).collect(Collectors.toList());

        result.forEach(r -> System.out.println(r));
        return result;
    }

    public Object sumAgeByEmailContains(String email) {
        int result = userDao.findByEmailContains(email).stream().mapToInt(u -> u.getAge()).sum();
        System.out.println("sum: " + result);
        return result;
    }
}
