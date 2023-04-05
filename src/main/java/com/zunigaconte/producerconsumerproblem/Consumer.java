package com.zunigaconte.producerconsumerproblem;

public class Consumer implements Runnable {

    private SharedQueue sharedQueue;
    private String consumerName;
    private int consumerCapacity;

    public Consumer(SharedQueue sharedQueue, String consumerName, int consumerCapacity) {
        this.sharedQueue = sharedQueue;
        this.consumerName = consumerName;
        this.consumerCapacity = consumerCapacity;
    }

//    public void consume() throws InterruptedException {
//        synchronized (sharedQueue) {
//            if (sharedQueue.getQueue().size() == 0) {
//                System.out.println("Queue is empty. " + consumerName + " is waiting...");
//                sharedQueue.wait();
//                System.out.println(consumerName + " has woken up!");
//            }
//        }
//
//        synchronized (sharedQueue) {
//            String item = sharedQueue.getQueue().remove();
//            System.out.println(consumerName + " has consumed " + item);
//            sharedQueue.notify();
//        }
//    }

    public void consume() throws InterruptedException {
        sharedQueue.getQueueLock().lock();
        if (sharedQueue.getQueue().size() == 0) {
            System.out.println("Queue is empty. " + consumerName + " is waiting...");
            sharedQueue.getNotEmpty().await();
            System.out.println(consumerName + " has woken up!");
        }

        String item = sharedQueue.getQueue().remove();
        System.out.println(consumerName + " has consumed " + item);

        sharedQueue.getNotFull().signal();
        sharedQueue.getQueueLock().unlock();
    }


    @Override
    public void run() {
        for (int i = 0; i < consumerCapacity; i++) {
            try {
                Thread.sleep((long) (Math.random() * 1000) * 5);
                consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(consumerName + " has ran its course.");
    }
}
