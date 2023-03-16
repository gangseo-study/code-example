# Getting Started

## AOP (Aspect Oriented Programming)
AOP란 관점 지향 프로그래밍으로, 기존 객체 지향 프로그래밍의 핵심 단위가 클래스 였다면,
AOP에서는 **관점**이다.

여기서 **관점**이란 프로그램에서 주요 로직과 부가적인 기능을 분리하여 모듈화하고, 이러한 부가적인 기능을 필요로 하는 
여러 모듈에서 공통적으로 사용할 수 있도록 한다.

부가적인 기능을 횡단 관심이라고도 한다.
![](https://itwiki.kr/images/4/48/AOP%EC%9D%98_%ED%95%B5%EC%8B%AC%EA%B4%80%EC%8B%AC%EA%B3%BC_%ED%9A%A1%EB%8B%A8%EA%B4%80%EC%8B%AC.jpg)

이러한 '핵심 관점'과 '부가적인 관점'은 일반적으로 프로그램 코드에서 분리되어 작성되며, 이를 통해 코드의 가독성, 유지보수성, 재사용성 등을 향상시킬 수 있다.

### Proxy
스프링에서는 AOP를 Proxy를 통해 구현하고 있다.

![](https://docs.spring.io/spring-framework/docs/2.5.5/reference/images/aop-proxy-call.png)

코드로 구현하면 다음과 같다.

```java
public class Main {

   public static void main(String[] args) {
   
      ProxyFactory factory = new ProxyFactory(new SimplePojo());
      factory.addInterface(Pojo.class);
      factory.addAdvice(new RetryAdvice());

      Pojo pojo = (Pojo) factory.getProxy();
      
      // this is a method call on the proxy!
      pojo.foo();
   }
}

public class SimplePojo implements Pojo {

    public void foo() {
        // this works, but... gah!
        ((Pojo) AopContext.currentProxy()).bar();
    }

    public void bar() {
        // some logic...
    }
}
```

1. JDK 다이나믹 프록시
   - Reflection을 통해 동적으로 proxy 객체 생성
   - 인터페이스를 기준으로 proxy 생성
   - 따라서 인터페이스를 구현한 클래스가 아니면 프록시를 생성할 수 없다.
   - spring 기본값
   
2. CGLib Proxy
   - 클래스 상속을 통해 proxy 객체 생성
   - interface, class 기준으로 proxy 생성
   - spring boot 기본값
   

-----------

## Transaction
트랜잭션이란 쪼개질 수 없는 최소한의 논리 단위를 말한다.

트랜잭션의 특징은 ACID로 설명할 수 있다.
- Atomicity(원자성)
  - 트랜잭션이 데이터베이스에 모두 반영되던지, 아니면 전혀 반영 되지 않아야 한다. 
  - 트랜잭션 내의 모든 명령은 반드시 완벽히 수행되어야 하며, 모두가 완벽히 수행되지 않고 어느 하나라도 오류가 발생하면 트랜잭션 전부가 취소되어야 한다.

- Consistency(일관성)
  - 트랜잭션의 작업 처리 결과가 항상 일관성이 있어야 한다.
  
- Isolation(독립성)
  - 둘 이상의 트랜잭션이 동시에 실행되고 있을 경우 어떤 하나의 트랜잭션이라도 다른 트랜잭션의 연산에 끼어들수 없다.
  - 수행 중인 트랜잭션은 완전히 완료될 때까지 다른 트랜잭션에서 수행 결과를 참조 할 수 없다.

- Durability(지속성)
  - 트랜잭션이 성공적으로 완료되었을 경우, 결과는 영구적으로 반영되어야 한다.