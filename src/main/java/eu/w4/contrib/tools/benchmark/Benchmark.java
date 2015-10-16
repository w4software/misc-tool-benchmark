package eu.w4.contrib.tools.benchmark;

import java.util.concurrent.CyclicBarrier;

import eu.w4.contrib.tools.benchmark.tests.CPU32;
import eu.w4.contrib.tools.benchmark.tests.CPU64;
import eu.w4.contrib.tools.benchmark.tests.FastCPU32;
import eu.w4.contrib.tools.benchmark.tests.Float32;
import eu.w4.contrib.tools.benchmark.tests.Float64;
import eu.w4.contrib.tools.benchmark.tests.RAM;
import eu.w4.contrib.tools.benchmark.tests.SmallFile;

public class Benchmark
{

  public long test(final Class<? extends Test> testClass, final int count)
  {
    final CyclicBarrier cyclicBarrier;
    if (count > 1)
    {
      cyclicBarrier = new CyclicBarrier(count);
    }
    else
    {
      cyclicBarrier = null;
    }
    final Test[] threads = new Test[count];
    for (int i = 0; i < count; i++)
    {
      Test test;
      try
      {
        test = testClass.newInstance();
      }
      catch (final InstantiationException e)
      {
        throw new RuntimeException(e);
      }
      catch (final IllegalAccessException e)
      {
        throw new RuntimeException(e);
      }
      test.start(cyclicBarrier);
      threads[i] = test;
    }
    long totalDuration = 0;
    for (int i = 0; i < count; i++)
    {
      try
      {
        threads[i].join();
        totalDuration += threads[i].getDuration();
      }
      catch (final InterruptedException e)
      {
        throw new RuntimeException(e);
      }
    }
    final long average = totalDuration / count;
    return average;
  }

  public void testLog(final Class<? extends Test> testClass, final int count)
  {
    final long average = test(testClass, count);
    System.out.println(testClass.getSimpleName() + ": " + average + " ms");
  }

  public static void main(final String[] args)
    throws Exception
  {
    final Benchmark benchmark = new Benchmark();
    final Runtime runtime = Runtime.getRuntime();

    System.out.println("Benchmarking tool");
    System.out.println();
    System.out.println(String.format("CPU: %s logical processors available",
                                     runtime.availableProcessors()));
    System.out.println(String.format("OS: %s %s (%s)",
                                     System.getProperty("os.name"),
                                     System.getProperty("os.version"),
                                     System.getProperty("os.arch")));
    System.out.println(String.format("Java: %s %s",
                                     System.getProperty("java.vendor"),
                                     System.getProperty("java.version")));
    System.out.println();

    int n;
    if (args.length >= 1)
    {
      n = Integer.parseInt(args[0]);
    }
    else
    {
      n = runtime.availableProcessors();
      final long durationAll = benchmark.test(FastCPU32.class, n);
      final long durationHalf = benchmark.test(FastCPU32.class, n/2);
      final long ratio = 100*(durationAll - durationHalf) / durationHalf;
      if (ratio >= 10)
      {
        n /= 2;
        System.out.println("Hyperthreading detected.");
      }
      else
      {
        System.out.println("Hyperthreading not detected.");
      }
      System.out.println("If this is not correct, you can provide manually the number of threads to use on command line");
    }

    System.out.println();
    System.out.println("Test using " + n + " threads");
    benchmark.testLog(CPU32.class, n);
    benchmark.testLog(CPU64.class, n);
    benchmark.testLog(Float32.class, n);
    benchmark.testLog(Float64.class, n);
    benchmark.testLog(RAM.class, 1);
    benchmark.testLog(SmallFile.class, 1);
    System.out.println("");
    System.out.println("The results are expressed in ms (the lower, the better)");
  }
}
