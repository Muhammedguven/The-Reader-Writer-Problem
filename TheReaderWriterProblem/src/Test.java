package sync;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
public class Test {
    public static void main(String [] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ReadWriteLock RW = new ReadWriteLock();


        executorService.execute(new Writer(RW));
        executorService.execute(new Writer(RW));
        executorService.execute(new Writer(RW));
        executorService.execute(new Writer(RW));

        executorService.execute(new Reader(RW));
        executorService.execute(new Reader(RW));
        executorService.execute(new Reader(RW));
        executorService.execute(new Reader(RW));


    }
}
class ReadWriteLock{
    private Semaphore readLock=new Semaphore(1);
    private Semaphore writeLock=new Semaphore(1);
    private int readCount = 0;

    public void readLock() {
        try{
            readLock.acquire();
        }
        catch (InterruptedException e) {}

        ++readCount;

        if (readCount == 1){
            try{
                writeLock.acquire();
            }
            catch (InterruptedException e) {}
        }

        System.out.println("Thread " + Thread.currentThread().getName() + " is READING.");
        readLock.release();
    }
    public void writeLock() {
        try{
            writeLock.acquire();
        }
        catch (InterruptedException e) {}
        System.out.println("Thread " + Thread.currentThread().getName() + " is WRITING.");

    }
    public void readUnLock() throws InterruptedException {

        try{
            readLock.acquire();
        }
        catch (InterruptedException e) {}

        --readCount;

        if (readCount == 0){
            writeLock.release();
        }

        System.out.println("Thread " + Thread.currentThread().getName() + " is DONE READING.");

        readLock.release();

    }


    public void writeUnLock() {
        System.out.println("Thread " + Thread.currentThread().getName() + " is DONE WRITING.");
        writeLock.release();
    }
}

class Writer implements Runnable
{
    private ReadWriteLock RW_lock;


    public Writer(ReadWriteLock rw) {
        RW_lock = rw;
    }

    public void run() {
        while (true){
            SleepUtilities.nap();
            RW_lock.writeLock();
            SleepUtilities.nap();
            RW_lock.writeUnLock();

        }
    }


}



class Reader implements Runnable
{
    private ReadWriteLock RW_lock;


    public Reader(ReadWriteLock rw) {
        RW_lock = rw;
    }
    public void run() {
        while (true){
            SleepUtilities.nap();
            RW_lock.readLock();


            try {
                SleepUtilities.nap();
                RW_lock.readUnLock();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }


}
class SleepUtilities
{
    public static void nap() {
        nap(NAP_TIME);
    }

    public static void nap(int duration) {
        int sleeptime = (int) (NAP_TIME * Math.random() );
        try { Thread.sleep(sleeptime*1000); }
        catch (InterruptedException e) {}
    }

    private static final int NAP_TIME = 5;
}