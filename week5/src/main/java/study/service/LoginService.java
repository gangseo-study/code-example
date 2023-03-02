package study.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.helper.LogonList;
import study.model.UserVO;

import java.util.List;

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
