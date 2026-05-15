package com.learning.jvm.heap;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;
import java.lang.management.MemoryMXBean;
import java.util.List;

/**
 * JVM GC 信息分析工具
 * 运行: java -XX:+UseG1GC org.example.heap.GCAnalysis
 */
public class GCAnalysis {

    public static void main(String[] args) {
        System.out.println("=== JVM Memory & GC Analysis ===");
        System.out.println();

        // 基本内存信息
        Runtime rt = Runtime.getRuntime();
        long maxMem = rt.maxMemory() / 1024 / 1024;
        long totalMem = rt.totalMemory() / 1024 / 1024;
        long freeMem = rt.freeMemory() / 1024 / 1024;
        long usedMem = totalMem - freeMem;

        System.out.println("Heap Memory:");
        System.out.printf("  Max:     %4d MB%n", maxMem);
        System.out.printf("  Total:   %4d MB%n", totalMem);
        System.out.printf("  Used:    %4d MB%n", usedMem);
        System.out.printf("  Free:    %4d MB%n", freeMem);
        System.out.println();

        // GC 信息
        List<GarbageCollectorMXBean> gcBeans = ManagementFactory.getGarbageCollectorMXBeans();
        System.out.println("Garbage Collectors:");
        for (GarbageCollectorMXBean gc : gcBeans) {
            System.out.printf("  %-20s  count=%d  time=%dms%n",
                    gc.getName(), gc.getCollectionCount(), gc.getCollectionTime());
        }
        System.out.println();

        // 通过分配内存触发 GC，观察变化
        System.out.println("--- Allocating 256MB to trigger GC ---");
        byte[] block = new byte[256 * 1024 * 1024];
        for (int i = 0; i < block.length; i += 4096) {
            block[i] = 1;
        }

        System.out.println("After allocation:");
        System.out.printf("  Used:    %4d MB%n", (rt.totalMemory() - rt.freeMemory()) / 1024 / 1024);

        block = null;
        System.gc();

        System.out.println("After System.gc():");
        System.out.printf("  Used:    %4d MB%n", (rt.totalMemory() - rt.freeMemory()) / 1024 / 1024);

        System.out.println();
        System.out.println("GC after trigger:");
        for (GarbageCollectorMXBean gc : gcBeans) {
            System.out.printf("  %-20s  count=%d  time=%dms%n",
                    gc.getName(), gc.getCollectionCount(), gc.getCollectionTime());
        }

        System.out.println();
        System.out.println("=== Done ===");
    }
}
