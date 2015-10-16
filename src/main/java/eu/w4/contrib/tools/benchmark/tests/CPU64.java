package eu.w4.contrib.tools.benchmark.tests;

import eu.w4.contrib.tools.benchmark.Test;

public class CPU64 extends Test
{

  private long l1 = 123456789;
  private long l2 = 987654321;
  public long r = 7;

  @Override
  public void runTest()
  {
    for(long i = 0; i<1000000000 ; i++)
    {
      r += (l1 * l2 + 7)^2;
      l1 += i;
      l2 -= i;
    }
  }
}
