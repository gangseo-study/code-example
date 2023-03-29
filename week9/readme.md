# Spring Interceptor
스프링 인터셉터에 대해 알기 전에 우선 Spring MVC가 어떤 흐름으로 사용자의 요청을 처리하는지 살펴보자.

![workflow](https://docs.spring.io/spring-framework/docs/3.2.x/spring-framework-reference/html/images/mvc.png)

- FrontController => Srping에서의 DispatcherServlet

## DispatcherServlet
![workflow](https://docs.spring.io/spring-framework/docs/3.2.x/spring-framework-reference/html/images/mvc-contexts.gif)

- Spring에서 JAVA EE 표준인 HttpServlet 클래스를 상속받아 만든 서블릿
- 모든 Http 요청을 받음
- HandlerMapping을 통해 올바른 Controller 클래스를 찾음
- ViewResolver를 통해 올바른 View로 데이터 전달


## HandlerMapping
- 어플리케이션이 시작되면 자동으로 RequestMappingHandlerMapping 빈이 생성되면서 @Controller 어노테이션이 붙은 클래스를 찾아
RequestMappingInfo 클래스의 인스턴스를 만들고 등록한다.
- https://pplenty.tistory.com/7

## Interceptor
- Spring Interceptor는 HandlerMapping의 전후로 실행된다.
- 3가지 메서드를 overrrider 할 수 있다.

1. preHandle()
   - HandlerMapping 전 실행
2. postHandle()
   - HandlerMapping 후 실행
3. afterCompletion()
   - 모든 요청이 완료되고 뷰가 렌더링 됐을 때 실행

- Http 요청이 들어옴 -> DispatcherServlet -> **preHandle()** -> HandlerMapping -> Controller 
- -> HandlerMapping -> **postHandle()** -> DispatcherServlet -> View 렌더링 (성공/오류 페이지) -> **afterCompletion()**