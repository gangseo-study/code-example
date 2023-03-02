package study.helper;

import io.swagger.annotations.Scope;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Queue;

@Scope(name = "waitingQueue", description = "waiting line")
@Component
public class WaitingQueue {

    private final Queue<String> userNameQueue = new LinkedList<>();

    public void add(String name) {
        userNameQueue.add(name);
    }

    public void printQueue() {
        userNameQueue.forEach(System.out::println);
    }

    public String getQueue() {
        return userNameQueue.toString();
    }
}
