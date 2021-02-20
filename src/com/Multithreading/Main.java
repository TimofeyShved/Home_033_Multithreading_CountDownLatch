package com.Multithreading;

import java.util.concurrent.CountDownLatch;

public class Main {

    public static void main(String[] args) throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(3); // создаем счётчик

        new Work(countDownLatch);
        new Work(countDownLatch);
        new Work(countDownLatch);

        countDownLatch.await(); // данный поток, ждёт пока счётчик не зоплнится до 3
        System.out.println("вся работы выполнена");
    }
}

class Work extends Thread{
    CountDownLatch countDownLatch;

    public Work(CountDownLatch countDownLatch){
        this.countDownLatch = countDownLatch; // передаем счётчик
        start();
    }

    @Override
    public void run(){
        try {
            sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("работы выполнена");
        countDownLatch.countDown(); // прибовляем к счётчику +1
    }
}