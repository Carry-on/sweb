package com.xiao.sweb.learn;

import java.util.concurrent.Semaphore;

public class PrintAbc {

    private static final Semaphore semaphoreA = new Semaphore(1);
    private static final Semaphore semaphoreB = new Semaphore(0);
    private static final Semaphore semaphoreC = new Semaphore(0);

    static class PrintThread implements Runnable{
        private char letter;
        private Semaphore curSemaphore;
        private Semaphore nextSemaphore;

        public static void main(String[] args) {
            Thread threadA = new Thread(new PrintThread('A', semaphoreA, semaphoreB));
            Thread threadB = new Thread(new PrintThread('B', semaphoreB, semaphoreC));
            Thread threadC = new Thread(new PrintThread('C', semaphoreC, semaphoreA));
            threadA.start();
            threadB.start();
            threadC.start();
        }

        public PrintThread(char letter, Semaphore curSemaphore, Semaphore nextSemaphore){
            this.letter = letter;
            this.curSemaphore = curSemaphore;
            this.nextSemaphore = nextSemaphore;
        }

        @Override
        public void run(){
            for (int i=0; i< 10; i++){
                try {
                    curSemaphore.acquire();
                    System.out.print(letter);
                    nextSemaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }

        }
    }
}
