package study.dao;

import study.vo.UserVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserFileDao implements UserDao{
    private Map<String, UserVO> dataMap;

    public UserFileDao(Map<String, UserVO> dataMap) {
        this.dataMap = dataMap;
    }

    @Override
    public List<UserVO> findAll() {
        return new ArrayList<>(dataMap.values());
    }

    @Override
    public UserVO findByName(String name) {
        if (this.dataMap.containsKey(name)) return dataMap.get(name);
        else return null;
    }

    @Override
    public List<UserVO> findByAge(int age) {
        return this.dataMap.values().stream().filter(d -> d.getAge() == age).collect(Collectors.toList());
    }

    @Override
    public List<UserVO> findByEmailContains(String email) {
        return this.dataMap.values().stream().filter(d -> d.getEmail().contains(email)).collect(Collectors.toList());
    }
}
