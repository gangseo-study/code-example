package study.helper;

import org.springframework.stereotype.Component;
import study.model.UserVO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LogonList {
    private final List<UserVO> list = new ArrayList<>();

    public void add(UserVO user) {
        list.add(user);
    }

    public void subtract(UserVO user) {
        list.remove(user);
    }

    public List<String> getList() {
        System.out.println("list: " +list);
        return list.stream().map(UserVO::getName).collect(Collectors.toList());
    }

}
