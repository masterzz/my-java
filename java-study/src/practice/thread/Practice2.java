package practice.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * 	2.和尚吃馒头问题
 * 		100馒头
 * 		50个和尚，每个和尚一次只能吃一个馒头，但是最多只允许吃三个馒头。
 * 		看每个和尚各吃了多少馒头。
 */
public class Practice2 {
    public static void main(String[] args) {
        List<Monk> monkList = new ArrayList<Monk>();
        List<Bun> bunList = new ArrayList<Bun>();

        for (int i = 0; i < 100; i ++) {
            bunList.add(new Bun("Bun " + i));
        }

        for (int i = 0; i < 50 ; i++) {
            monkList.add(new Monk("monk" + i, bunList));
        }

        for (Monk monk : monkList)
            new Thread(monk).start();

    }
}

class Monk implements Runnable{
    private  List<Bun> bunList;
    private String name;
    private List<Bun> eatList;

    Monk(String name, List<Bun>  bunList) {
        this.name = name;
        this.bunList = bunList;
        this.eatList = new ArrayList<Bun>();
    }

    @Override
    public void run() {
        while (true)
            eat();
    }

    private void eat() {
        Thread.currentThread().setName(this.name);
        System.out.println(Thread.currentThread().getName() + " eat");
        if(eatList.size() == 3)
            return;

    }
}

class Bun {
    private String name;

    Bun(String name) {
        this.name = name;
    }
}