package collection;

import java.util.ArrayList;

public class ListTest {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            list.add("" + i);
        }
    }
}
