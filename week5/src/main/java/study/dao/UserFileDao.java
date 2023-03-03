package study.dao;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import study.Main;
import study.model.UserDTO;
import study.model.UserVO;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 동일한 유형의 Bean이 2개 이상 존재할시 이름을 명시적으로 작성해준다.
 * 이 프로젝트에서는 UserDao를 구현하는 클래스가 2개이기 때문에 명시적으로 작성해준다.
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
            ClassPathResource resource = new ClassPathResource("data/user.csv");
            try {
                StringJoiner joiner = new StringJoiner(",");
                joiner.add(vo.getName());
                joiner.add(String.valueOf(vo.getAge()));
                joiner.add(vo.getPhone());
                joiner.add(vo.getEmail());

                File file = resource.getFile();
                Files.write(file.toPath(), ("\n" + joiner).getBytes(), StandardOpenOption.APPEND);
                this.dataMap.put(vo.getName(), vo);
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
            return vo.toDTO();
        } else {
            throw new RuntimeException("duplicated key");
        }
    }

    @Override
    public String deleteUser(String name) throws Exception {
        if (this.dataMap.containsKey(name)) {
            ClassPathResource resource = new ClassPathResource("data/user.csv");
            File file = resource.getFile();
            try {
                this.dataMap.remove(name);
                StringJoiner lineJoiner = new StringJoiner("\n");
                for (var entry : dataMap.entrySet()) {
                    StringJoiner joiner = new StringJoiner(",");
                    UserVO value = entry.getValue();
                    joiner.add(value.getName());
                    joiner.add(String.valueOf(value.getAge()));
                    joiner.add(value.getPhone());
                    joiner.add(value.getEmail());
                    lineJoiner.merge(joiner);
                }
                Files.write(file.toPath(), lineJoiner.toString().getBytes(), StandardOpenOption.WRITE);
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
            return name;
        } else {
            throw new RuntimeException("no user to delete");
        }
    }

    @Override
    public UserDTO updateUser(UserVO vo) throws Exception {
        if (this.findByName(vo.getName()) != null) {
            ClassPathResource resource = new ClassPathResource("data/user.csv");
            File file = resource.getFile();
            try {
                this.dataMap.put(vo.getName(), vo);
                StringJoiner lineJoiner = new StringJoiner("\n");
                for (var entry : dataMap.entrySet()) {
                    StringJoiner joiner = new StringJoiner(",");
                    UserVO value = entry.getValue();
                    joiner.add(value.getName());
                    joiner.add(String.valueOf(value.getAge()));
                    joiner.add(value.getPhone());
                    joiner.add(value.getEmail());
                    lineJoiner.merge(joiner);
                }
                Files.write(file.toPath(), lineJoiner.toString().getBytes(), StandardOpenOption.WRITE);
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        } else {
            throw new RuntimeException("duplicated key");
        }
        return vo.toDTO();
    }
}
