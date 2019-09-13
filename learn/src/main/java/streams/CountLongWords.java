package streams;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * 流是使用stream 或parallelStream 方法创建，fillter方法对其进行转换，而count方法是终止操作
 */
public class CountLongWords {
    public static void main(String[] args) throws IOException {
        String contents = new String(
                Files.readAllBytes(Paths.get("E:\\learn_project\\javalearn\\learn\\src\\main\\java\\temp\\file\\alice30.txt")), StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("\\PL+"));
        long count = 0;
        for (String w : words) {
            if (w.length() > 12) count++;
//            System.out.print(w +"---");
        }
        System.out.println(count);

        /**
         * java 流库，在java8中引入，用来“做什么而非怎么做”
         * stream 创建顺序流--产生当前集合中所有元素的顺序流
         * fillter
         * count
         */
        count = words.stream().filter(w -> w.length() > 12).count();
        System.out.println(count);

        /**
         * parallelStream 已并行方式执行过滤和计数
         * parallelStream 创建并行流
         * filter 对其进行转换
         * count  终止操作
         */
        count = words.parallelStream().filter(w -> w.length() > 12).count();
        System.out.println(count);
    }
}
