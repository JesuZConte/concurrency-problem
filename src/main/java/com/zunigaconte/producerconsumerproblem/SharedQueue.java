package com.zunigaconte.producerconsumerproblem;

import java.util.Queue;
public class SharedQueue {

    private Queue<String> queue;
    private int capacity;

    public SharedQueue(Queue<String> queue, int capacity) {
        this.queue = queue;
        this.capacity = capacity;
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
