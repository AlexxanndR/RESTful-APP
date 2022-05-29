package com.project.web.services;

import org.springframework.stereotype.Service;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class RequestCounter {
    static private ReentrantLock mutex = new ReentrantLock();

    private static int counter;

    public RequestCounter() {
        counter = 0;
    }

    public static void increase() {
        mutex.lock();
        counter++;
        mutex.unlock();
    }

    public static Integer getCounter() { return counter; }
}
