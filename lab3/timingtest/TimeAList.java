package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
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
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        AList<Integer> Ns1 = new AList<>();
        // 添加要测试的不同大小
        Ns1.addLast(1000);
        Ns1.addLast(2000);
        Ns1.addLast(4000);
        Ns1.addLast(8000);
        Ns1.addLast(16000);
        Ns1.addLast(32000);
        Ns1.addLast(64000);
        Ns1.addLast(128000);

        AList<Double> times = new AList<>();

        for(int i = 0; i < Ns1.size(); i ++) {
            int num = Ns1.get(i);
            Stopwatch sw = new Stopwatch(); // 创建计时器

            AList<Integer> testList = new AList<>();
            for(int j = 0; j < num; j++){ // 修正循环条件，确保添加num个元素
                testList.addLast(j);
            }

            double timeinseconds = sw.elapsedTime(); // 记录耗时
            times.addLast(timeinseconds);
        }

        printTimingTable(Ns1, times, Ns1);
    }
}
