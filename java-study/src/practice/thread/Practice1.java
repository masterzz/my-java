package practice.thread;

import java.util.ArrayList;
import java.util.List;

/*
* 	1.熊吃蜂蜜问题
    100只蜜蜂.
    每只蜜蜂一次生产蜂蜜量为1.
    蜜罐的容量是20.
    熊在蜜罐满了的时候一次性吃掉所有蜂蜜。
    提示:蜜蜂生产蜂蜜时，如果蜜罐已满则等待，否则+1，notifyAll.
         熊吃蜂蜜时，如果蜜罐已满则吃掉再notifyAll，否则，notifyAll.
* */
public class Practice1 {
    public static void main(String[] args) {
//        初始化2个熊，100个蜜蜂，用list实现
        List<Bee> beeList = new ArrayList<Bee>();
        List<Bear> bearList = new ArrayList<Bear>();
        HoneyJar honeyJar = new HoneyJar();

        for (int i = 0; i < 100; i++)
            beeList.add(new Bee("bee-" + i, honeyJar));

        for (int i = 0; i < 2; i++)
            bearList.add(new Bear("bear-" + i, honeyJar));

//        跑起来
        for (Bee bee : beeList)
            new Thread(bee).start();

        for (Bear bear : bearList)
            new Thread(bear).start();
    }
}

//蜂蜜和熊共享蜜罐
class Bee implements Runnable {
    private HoneyJar honeyJar;
    private String name;

    Bee(String name, HoneyJar honeyJar) {
        this.name = name;
        this.honeyJar = honeyJar;
    }

    @Override
    public void run() {
        Thread.currentThread().setName(name);
        while (true)
            produce();
    }

    private void produce() {
        this.honeyJar.add();
    }
}

class Bear implements Runnable {
    private HoneyJar honeyJar;
    private String name;

    Bear(String name, HoneyJar honeyJar) {
        this.name = name;
        this.honeyJar = honeyJar;
    }

    @Override
    public void run() {
        Thread.currentThread().setName(name);
        while (true)
            eat();
    }

    private void eat() {
        this.honeyJar.remove();
    }
}

class HoneyJar {
    // 蜜罐的容量设置为20
    private static final int MAX = 20;
    private int amount = 0;

    public void add() {
        synchronized (this) {
            try {
                if (amount == 20) {
//                    System.out.println(Thread.currentThread().getName() + " wait ---- " + amount);
//                    this.notifyAll();
                    this.wait();
                    return;
                }
                amount++;
                System.out.println(Thread.currentThread().getName() + " add ---- " + amount);
                this.notifyAll();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void remove() {
        synchronized (this) {
            try {
                if (this.amount < 20) {
                    System.out.println(Thread.currentThread().getName() + " wait");
                    this.wait();
                    this.notifyAll();
                }
                if (this.amount == 20) {
                    this.amount = 0;
                    System.out.println(Thread.currentThread().getName() + " eat -----" + amount);
                    this.notifyAll();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
