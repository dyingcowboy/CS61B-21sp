package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        AList<Integer> Ns1 = new AList<>();
        Ns1.addLast(1000);
        Ns1.addLast(2000);
        Ns1.addLast(4000);
        Ns1.addLast(8000);
        Ns1.addLast(16000);
        Ns1.addLast(32000);
        Ns1.addLast(64000);
        Ns1.addLast(128000);
        AList<Integer> ops = new AList<>();
        for(int i = 0; i < 8;i++){
            ops.addLast(10000);
        }
        AList<Double> times = new AList<>();
        SLList<Integer> Ns = new SLList<>();
        for(int i = 0; i < 8; i ++) {
            int num = Ns1.get(i);
            for(int j = 0; j <= num; j++){
                Ns.addLast(j);
            }
            Stopwatch sw = new Stopwatch();
            for(int j = 0;j <= 10000;j++){
                Ns.getLast();
            }
            double timeinseconds = sw.elapsedTime();
            times.addLast(timeinseconds);
        }
        printTimingTable(Ns1, times, ops);
    }

}
