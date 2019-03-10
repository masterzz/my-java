package thread;

public class ThreadDemo1 {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        YouThread t2 =new YouThread();
//        注意：start才是启动线程，调用run的话就是正常的方法
        t1.start();
        t2.start();
    }
}

class MyThread extends Thread {
    public void run() {
        while (true) {
            System.out.println("MyThread()");
            yield();           // 放弃cpu资源的抢占权
        }
    }
}

class YouThread extends Thread {
    public void run() {
        while (true) {
            System.out.println("YouThread()");
            yield();
        }
    }
}