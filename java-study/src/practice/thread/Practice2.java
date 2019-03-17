package practice.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * 2.和尚吃馒头问题
 * 100馒头
 * 50个和尚，每个和尚一次只能吃一个馒头，但是最多只允许吃三个馒头。
 * 看每个和尚各吃了多少馒头。
 */
public class Practice2 {
    public static void main(String[] args) {
        List<Monk> monkList = new ArrayList<Monk>();
        BunPool bunPool = new BunPool();

        for (int i = 0; i < 50; i++) {
            monkList.add(new Monk("monk" + i, bunPool));
        }

        for (Monk monk : monkList)
            new Thread(monk).start();

    }
}

class Monk implements Runnable {
    private BunPool bunPool;
    private String name;
    private List<Bun> eatList;

    Monk(String name, BunPool bunPool) {
        this.name = name;
        this.bunPool = bunPool;
        this.eatList = new ArrayList<Bun>();
    }

    @Override
    public void run() {
        while (this.eatList.size() < 3) {
            eat();
        }
    }

    private void eat() {
        Bun bun = this.bunPool.decrease();

        if (bun != null) {
            this.eatList.add(bun);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Bun {
    private String name;

    Bun(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class BunPool {
    private List<Bun> bunList;

    BunPool() {
        bunList = new ArrayList<Bun>();
        for (int i = 0; i < 100; i++) {
            bunList.add(new Bun("bun " + i));
        }
    }

    public Bun decrease() {
        synchronized (this) {
//            如果还有馒头
            if(bunList.size() > 0) {
                try {
//                    和尚吃馒头
                    Bun bun = this.bunList.remove( 0);
                    System.out.println(Thread.currentThread().getName() + " eat " + bun.getName());
                    return bun;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }
}