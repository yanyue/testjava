import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Test {
    public static void test() {
        LinkedList<Integer> list = new LinkedList<>();
        list.push(1);
        list.push(2);
        list.push(3);
        list.push(4);
        list.push(5);

//        for (int i = 0; i < list.size(); ++i) {
//            System.out.println(list.get(i));
//        }

        Map<String, Integer> map = new HashMap<>();
        Map<String, Integer> cmap = new ConcurrentHashMap<>();

        // https://www.cnblogs.com/jpfss/p/9952354.html
//        Gson gson = new Gson();
//        String jsonArray = "[Android,Java,PHP]";
//        String[] strings = gson.fromJson(jsonArray, String[].class);
//        System.out.println("result:" + Arrays.toString(strings) + " size=" + strings.length);
//
//        List<String> t = gson.fromJson(jsonArray, new TypeToken<List<String>>(){}.getType());
//        System.out.println("result:" + Arrays.toString(t.toArray()) + " size=" + t.size());

        Deque<Integer> path = new LinkedList<Integer>();
        for (int i = 0; i < 5; ++i) {
            path.offer(i);
        }

        while (!path.isEmpty()) {
            System.out.println("num: " + path.remove());
        }
    }
}
