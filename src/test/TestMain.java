package test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class TestMain {
    public static void main(String[] args) throws InterruptedException {
        int numberOfThreads = 1000;
        ExecutorService service = Executors.newFixedThreadPool(1000);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);
        Counter counter = new Counter();
        SalesforceConnector con = SalesforceConnector.getInstance();
        for (int i = 0; i < numberOfThreads; i++) {
            service.submit(() -> {
                try {
                    counter.increment();
                    con.connect();
                } catch (InterruptedException e) {
                    // Handle exception
                }
                latch.countDown();
            });
        }

        latch.await();
        System.out.println(counter.getCount());
        System.out.println(con.count);

    }
}