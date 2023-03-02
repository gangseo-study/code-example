package study.dao;

import org.springframework.stereotype.Component;
import study.Main;
import study.model.UserDTO;
import study.model.UserVO;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 동일한 유형의 Bean이 2개 이상 존재할시 이름을 명시적으로 작성해준다.
 * 여기서는 UserDao를 구현하는 클래스가 2개이기 때문에 명시적으로 작성해준다.
 */
@Component("userFileDao")
// @Repository @Mapper
public class UserFileDao implements UserDao{
    private final Map<String, UserVO> dataMap = new HashMap<>();


    /**
     * - @PostConstruct: 의존성 주입이 일어난 후에 발생해야 하는 수행되어야 하는 메서드에 사용된다.
     * 해당 클래스가 호출되지 않더라도 발생한다 하나의 클래스에서 하나의 메서드에만 적용할 수 있다.
     *
     */
    @PostConstruct
    public void initData() {
        System.out.println("user dao init");
        // 파일을 먼저 읽은 뒤 리스트 생성
        try (InputStream inputStream = Main.class.getResourceAsStream("/data/user.csv")) {
            Scanner readScanner = new Scanner(inputStream);

            // Map을 db라고 생각
            while (readScanner.hasNext()) {
                String[] line = readScanner.nextLine().split(",");
                this.dataMap.put(line[0], new UserVO(line[0], Integer.parseInt(line[1]), line[2], line[3]));
            }
            readScanner.close();
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }

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

    @Override
    public UserDTO insertUser(UserVO vo) {
        if (this.findByName(vo.getName()) == null) {
            try (FileWriter fw = new FileWriter(Main.class.getResource("/data/user.csv").toString())) {
                StringJoiner joiner = new StringJoiner(",");
                joiner.add(vo.getName());
                joiner.add(String.valueOf(vo.getAge()));
                joiner.add(vo.getPhone());
                joiner.add(vo.getEmail());
                fw.write(joiner.toString());
                this.dataMap.put(vo.getName(), vo);
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }

            return vo.toDTO();
        } else {
            throw new RuntimeException("duplicated key");
        }
    }
}
