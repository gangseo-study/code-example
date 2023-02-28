package study;

import study.controller.UserController;
import study.dao.UserFileDao;
import study.service.UserService;
import study.vo.UserVO;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        // 파일을 먼저 읽은 뒤 리스트 생성
        InputStream inputStream = Main.class.getResourceAsStream("/data/user.csv");
        Scanner readScanner = new Scanner(inputStream);

        // Map을 db라고 생각
        Map<String, UserVO> database = new HashMap<>();
        while (readScanner.hasNext()) {
            String[] line = readScanner.nextLine().split(",");
            database.put(line[0], new UserVO(line[0], Integer.parseInt(line[1]), line[2], line[3]));
        }
        readScanner.close();
        inputStream.close();


        // dao, service, controller 인스턴스 생성
        UserFileDao userFileDao = new UserFileDao(database);
        UserService userService = new UserService(userFileDao);
        UserController controller = new UserController(userService);

        Scanner inputScanner = new Scanner(System.in);
        while (true) {
            System.out.println("+--------------------------------------+------------+\n" +
                    "| quit                                 | q or Q     |\n" +
                    "+--------------------------------------+------------+\n" +
                    "| search all order by email length     | all        |\n" +
                    "+--------------------------------------+------------+\n" +
                    "| search by email contains and sum age | s or sum   |\n" +
                    "+--------------------------------------+------------+\n" +
                    "| search by name                       | n or name  |\n" +
                    "+--------------------------------------+------------+\n" +
                    "| search by age                        | a or age   |\n" +
                    "+--------------------------------------+------------+");
            System.out.print("입력 >> : ");
            String inputText = inputScanner.nextLine();

            // dispatcher servlet 이라고 생각
            switch (inputText) {
                case "q":
                case "Q":
                    break;
                case "all":
                    controller.readUserAll();
                    break;
                case "s":
                case "sum":
                    System.out.print("이메일 >> : ");
                    inputText = inputScanner.nextLine();
                    controller.sumAgeByEmailContains(inputText);
                    break;
                case "n":
                case "name":
                    System.out.print("이름 >> : ");
                    inputText = inputScanner.nextLine();
                    controller.readUserByName(inputText);
                    break;
                case "a":
                case "age":
                    System.out.print("나이 >> : ");
                    inputText = inputScanner.nextLine();
                    controller.readUserByAge(inputText);
                    break;
                default:
                    System.out.println(inputText + "는 없는 명령어 입니다. 다시 선택해 주세요.");

            }
            if (inputText.equalsIgnoreCase("q")) break;
        }
        inputScanner.close();

        System.out.println("---------프로그램 종료---------");
    }
}