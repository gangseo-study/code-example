package study.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.helper.LogonList;
import study.model.UserVO;

import java.util.List;


/**
 * 1. @RequiredArgsConstructor: @Notnull이나 final이 붙은 필드의 생성자를 자동으로 생성한다.
 */
@Service
@RequiredArgsConstructor
public class LoginService {

    private final LogonList logonList;
    private final UserService userService;

    public List<String> login(String name) {
        UserVO user = userService.readUserByName(name);
        if (user == null) throw new NullPointerException("user: " + name + " is not found");
        logonList.add(user);
        return logonList.getList();
    }

    public List<String> logout(String name) {
        UserVO user = userService.readUserByName(name);
        if (user == null) throw new NullPointerException("user: " + name + " is not found");
        logonList.subtract(user);
        return logonList.getList();
    }
}
