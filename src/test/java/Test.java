import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;


import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class Test {

    private static void reactorTest(){
        User user = new User(Lists.newArrayList("语文","数学"));
        User user2 = new User(Lists.newArrayList("英语","数学"));
        List<User> users = Lists.newArrayList(user,user2);
        List<String> collect = users.stream().flatMap(tmp -> tmp.getBooks().stream()).collect(Collectors.toList());
        System.out.println(collect);
        List<List<String>> strs = Lists.newArrayList(Lists.newArrayList("1", "2"), Lists.newArrayList("3", "4"));
        List<String> collect2 = strs.stream().flatMap(Collection::stream).collect(Collectors.toList());
        System.out.println(collect2.size());
        System.out.println(collect2);
    }
    @AllArgsConstructor
    @Data
    private static class User{
        private List<String> books;
    }

    public static void main(String args[]) {
        reactorTest();
        Test tester = new Test();
        // 类型声明
        MathOperation addition = (int a, int b) -> a + b;
        // 不用类型声明
        MathOperation subtraction = (a, b) -> a - b;
        // 大括号中的返回语句
        MathOperation multiplication = (int a, int b) -> {
            return a * b;
        };
        // 没有大括号及返回语句
        MathOperation division = (int a, int b) -> a / b;
        System.out.println("10 + 5 = " + tester.operate(10, 5, addition));
        System.out.println("10 - 5 = " + tester.operate(10, 5, subtraction));
        System.out.println("10 x 5 = " + tester.operate(10, 5, multiplication));
        System.out.println("10 / 5 = " + tester.operate(10, 5, division));
        // 不用括号
        GreetingService greetService1 = message -> System.out.println("Hello " + message);

        // 用括号
        GreetingService greetService2 = (message) -> System.out.println("Hello " + message);

        greetService1.sayMessage("Runoob");
        greetService2.sayMessage("Google");
    }

    interface MathOperation {
        int operation(int a, int b);
    }

    interface GreetingService {
        void sayMessage(String message);
    }

    private int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }

}
