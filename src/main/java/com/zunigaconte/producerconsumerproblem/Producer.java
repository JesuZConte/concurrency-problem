package com.zunigaconte.producerconsumerproblem;

public class Producer implements Runnable {

    private SharedQueue sharedQueue;

    public Producer(SharedQueue sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    String[] items = {"ItemOne", "ItemTwo", "ItemThree", "ItemFour", "ItemFive", "ItemSix", "ItemSeven", "ItemEight",
            "ItemNine", "ItemTen"};

//    public void produce(String item) throws InterruptedException {
//        synchronized (sharedQueue) {
//            if (sharedQueue.getQueue().size() >= sharedQueue.getCapacity()) {
//                System.out.println("Queue is full. Producer is waiting...");
//                sharedQueue.wait();
//                System.out.println("Producer has woken up!");
//            }
//        }
//
//        synchronized (sharedQueue) {
//            sharedQueue.getQueue().add(item);
//            System.out.println("Produced: " + item);
//            sharedQueue.notify();
//        }
//    }

    public void produce(String item) throws InterruptedException {
        String threadName = Thread.currentThread().getName();
        sharedQueue.getQueueLock().lock(); //blocking call
        if (sharedQueue.getQueue().size() >= sharedQueue.getCapacity()) {
            System.out.println("Queue is full. " + threadName + " is waiting...");
            sharedQueue.getNotFull().await(); //wait for the not full condition
        }

        sharedQueue.getQueue().add(item);
        System.out.println(threadName + " produced: " + item);
        sharedQueue.getNotEmpty().signal(); //informs to one of the sleeping threads that the queue is not empty
        sharedQueue.getQueueLock().unlock(); //unblock the shared queue
    }

    @Override
    public void run() {
        for (int i = 0; i < items.length; i++) {
            try {
                Thread.sleep((long) (Math.random() * 1000) * 5);
                produce(items[i]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("The producer has ran its course.");
    }
}
