package study.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import study.service.LoginService;

import java.util.List;


/**
 * - @Controller vs @RestController
 * - @Controller 어노테이션의 주 목적은 View를 반환하기 위해 사용한다. HandlerMapping을 통해 요청 URI에 맞는
 * - Controller를 찾고, ViewResolver를 통해 어떠한 View를 내보낼지 결정한다.
 * - 뷰가 아니라 데이터를 반환하고 싶을 때는 @ResponseBody 어노테이션을 통해 JSON 데이터를 반환할 수 있다.
 * ----------------
 * - @RestController는 @Controller 어노테이션에 @ResponseBody가 붙은 어노테이션이다. JSON 데이터를 반환할 때 사용한다.
 */

@Controller("/")
@Api(tags = {"로그인, 로그아웃"})
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    // /login 요청이 들어올 경우 static/login.html 뷰를 찾아 보여준다
    @GetMapping("login")
    @ApiOperation(value = "/login.html", notes = "로그인 페이지로 이동")
    public String index() {
        return "login";
    }

    @GetMapping("login/{name}")
    @ApiOperation(value = "사용자 이름", notes = "사용자 이름으로 로그인")
    public @ResponseBody ResponseEntity<List<String>> login(@PathVariable String name) {
        List<String> result = loginService.login(name);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("logout/{name}")
    @ApiOperation(value = "사용자 이름", notes = "사용자 이름으로 로그아웃")
    public @ResponseBody ResponseEntity<List<String>> logout(@PathVariable String name) {
        List<String> result = loginService.logout(name);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
