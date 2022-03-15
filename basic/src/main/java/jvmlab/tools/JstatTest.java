package jvmlab.tools;

/**
 * 测试 jstat 命令
 * jstat [ option vmid [interval[s|ms] [count]] ]
 *
 * jps
 * jstat -gc 2764 250 20：每250 毫秒查询一次进程2764垃圾收集状况，一共查询20次
 *
 * https://blog.csdn.net/zhaozheng7758/article/details/8623549
 */
public class JstatTest {
    public static void main(String[] args) throws InterruptedException {
        int _1MB = 1024 * 1024;
        while (true) {
            Thread.sleep(500);
            byte[] bigSize = new byte[2 * _1MB];
        }
    }
}
