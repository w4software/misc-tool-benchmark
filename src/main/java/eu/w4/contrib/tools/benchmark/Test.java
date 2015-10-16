package eu.w4.contrib.tools.benchmark;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public abstract class Test extends Thread
{

  private CyclicBarrier _cyclicBarrier;
  private long _duration;

  public Test()
  {
  }

  public abstract void runTest() throws Exception;

  public void start(final CyclicBarrier cyclicBarrier)
  {
    _cyclicBarrier = cyclicBarrier;
    start();
  }

  public long getDuration()
  {
    return _duration;
  }

  @Override
  public void run()
  {
    if (_cyclicBarrier != null)
    {
      try
      {
        _cyclicBarrier.await();
      }
      catch (final InterruptedException e)
      {
        throw new RuntimeException(e);
      }
      catch (final BrokenBarrierException e)
      {
        throw new RuntimeException(e);
      }
    }
    final long start = System.currentTimeMillis();
    try
    {
      runTest();
    }
    catch (final Exception e)
    {
      throw new RuntimeException(e);
    }
    final long end = System.currentTimeMillis();
    _duration = end-start;
  }
}
