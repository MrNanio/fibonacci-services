package com.nankiewic.workerapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

@SpringBootApplication
public class WorkerAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkerAppApplication.class, args);

        Jedis jSubscriber = new Jedis("redis", 6379);
        jSubscriber.subscribe(new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                calcFibonacci(message);
                System.out.println("message received index: " + message);
            }
        }, "channel");
    }

    private static void calcFibonacci(String index) {
        long val = Long.parseLong(index);
        long result = Math.round((Math.pow(1.618033988749895, val) - Math.pow(-0.6180339887498949, val)) / 2.23606797749979);
        Jedis jedis = new Jedis("redis", 6379);
        jedis.hset("value", index, Long.toString(result));
    }
}
