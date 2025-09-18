import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinPoolExample {
    static class FactorialTask extends RecursiveTask<Long> {
        private final int n;

        public FactorialTask(int n) {
            this.n = n;
        }

        @Override
        protected Long compute() {
            if (n <= 1) {
                return 1L;
            }
            FactorialTask subTask = new FactorialTask(n - 1);
            subTask.fork();
            return n * subTask.join();
        }
    }

    public static void main(String[] args) {
        int n = 10;

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        FactorialTask factorialTask = new FactorialTask(n);

        long result = forkJoinPool.invoke(factorialTask);

        System.out.println("Факториал " + n + "! = " + result);
    }
}