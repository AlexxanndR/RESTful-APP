package com.project.web.stats;

public class ReqCounterThread extends Thread{
    public ReqCounterThread() {
        super();
        start();
    }

//    public void run() {
//        while (Thread.currentThread().getName().equals("Thread-4")) {
//            try {
//                logger.info(Thread.currentThread().getName() + " is waiting for resolution");
//                Synchronization.semaphore.acquire();
//                RequestCounter.increment();
//                logger.info("Counter after increment " + RequestCounter.getCounter());
//            } catch (InterruptedException e) {
//                logger.error("Thread was interrupted");
//            }
//        }
//    }
}
