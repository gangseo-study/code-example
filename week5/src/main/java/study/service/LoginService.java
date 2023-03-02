package study.service.login;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.helper.LogonList;
import study.service.user.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final LogonList logonList;
    private final UserService userService;

    public List<String> login(String name) {
        logonList.add(userService.readUserByName(name));
        return logonList.getList();
    }

    public List<String> logout(String name) {
        logonList.subtract(userService.readUserByName(name));
        return logonList.getList();
    }
}
