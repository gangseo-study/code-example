package study.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.model.UserDTO;
import study.service.UserService;
import study.model.UserVO;

import java.util.List;

/**
 * 1. @RestController:
 * 2. @RequestMapping: 요청 uri를 현재 컨트롤러와 매핑해주는 어노테이션
 * 3. @Api: Swagger에게 현재 controller의 정보 설정
 * 4. @RequiredArgsConstructor: final 혹은 @NotNull이 붙은 필드를 생성자 주입을 통해 생성 해준다.
 */

@RestController
@RequestMapping("user/v1")
@Api(tags = {"사용자"})
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //    @RequiredArgsConstructor가 설정되어 있기 때문에 필요 없음
    //    @Autowired
    //    public UserController(UserService userService) {
    //        this.userService = userService;
    //    }

    @GetMapping("/all")
    @ApiOperation(value = "none", response = UserVO.class)
    public ResponseEntity<List<UserVO>> readUserAll() {
        return new ResponseEntity<>(userService.readUserAll(), HttpStatus.OK);
    }

    @GetMapping("/read/{name}")
    public ResponseEntity<UserVO> readUserByName(@PathVariable String name) {
        return new ResponseEntity<>(userService.readUserByName(name), HttpStatus.OK);
    }

    @PostMapping("/insert")
    public ResponseEntity<UserDTO> insertUser(@RequestBody UserDTO dto) throws Exception {
        UserDTO user = userService.insertUser(dto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<String> deleteUser(@PathVariable String name) throws Exception {
        String user = userService.deleteUser(name);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO dto) throws Exception {
        UserDTO user = userService.updateUser(dto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
