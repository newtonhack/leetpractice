package code.design.thread.sequence;

public class SeqPrinter {
    public static void main(String[] args) {
        Lock evenLock = new Lock();
        Lock oddLock = new Lock();

        Thread evenThread = new Thread(() -> {
            CNumber even = new CNumber(0, 2);
            while (true) {
                evenLock.lock();
                System.out.println("Even Thread ->" + even.getNumber());
                oddLock.unLock();
            }
        });

        Thread oddThread = new Thread(() -> {
            CNumber odd = new CNumber(1, 2);
            while (true) {
                oddLock.lock();
                System.out.println("Odd Thread ->" + odd.getNumber());
                evenLock.unLock();
            }
        });
        oddLock.lock();
        oddThread.start();
        evenThread.start();
    }

}

class Lock {
    private boolean isLock = false;

    public synchronized void lock() {
        while (this.isLock) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.isLock = true;
    }

    public synchronized void unLock() {
        this.isLock = false;
        this.notifyAll();
    }
}

class CNumber {
    private long number;
    private int mod;
    public boolean printing = false;

    public CNumber(long number, int mod) {
        this.number = number;
        this.mod = mod;
    }

    public long getNumber() {
        long temp = number;
        number = number + mod;
        return temp;
    }
}