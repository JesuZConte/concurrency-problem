package com.zunigaconte.producerconsumerproblem;

import java.util.LinkedList;

public class ProducerConsumer {

    public static void main(String[] args) {
        SharedQueue sharedQueue = new SharedQueue(new LinkedList<>(), 2);
        Producer producer = new Producer(sharedQueue);
        Consumer consumerOne = new Consumer(sharedQueue, "ConsumerOne", 10);
        Consumer consumerTwo = new Consumer(sharedQueue, "ConsumerTwo", 10);

        Thread p = new Thread(producer, "Producer Thread");
        Thread cOne = new Thread(consumerOne, "Consumer one Thread");
        Thread cTwo = new Thread(consumerTwo, "Consumer two Thread");

        p.start();
        cOne.start();
        cTwo.start();
    }
}
