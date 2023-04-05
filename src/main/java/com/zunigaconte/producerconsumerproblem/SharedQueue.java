package com.zunigaconte.producerconsumerproblem;

import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SharedQueue {

    private Queue<String> queue;
    private int capacity;
    private Lock queueLock = new ReentrantLock();
    private Condition notFull = queueLock.newCondition();
    private Condition notEmpty = queueLock.newCondition();

    public SharedQueue(Queue<String> queue, int capacity) {
        this.queue = queue;
        this.capacity = capacity;
    }


    public Lock getQueueLock() {
        return queueLock;
    }

    public void setQueueLock(Lock queueLock) {
        this.queueLock = queueLock;
    }

    public Condition getNotFull() {
        return notFull;
    }

    public void setNotFull(Condition notFull) {
        this.notFull = notFull;
    }

    public Condition getNotEmpty() {
        return notEmpty;
    }

    public void setNotEmpty(Condition notEmpty) {
        this.notEmpty = notEmpty;
    }

    public Queue<String> getQueue() {
        return queue;
    }

    public void setQueue(Queue<String> queue) {
        this.queue = queue;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
