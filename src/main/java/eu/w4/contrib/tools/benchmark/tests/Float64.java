package eu.w4.contrib.tools.benchmark.tests;

import eu.w4.contrib.tools.benchmark.Test;

public class Float64 extends Test
{

  private double l1 = Math.PI;
  private double l2 = Math.E;
  public double r = 7;

  @Override
  public void runTest()
  {
    for(long i = 0; i<20000000 ; i++)
    {
      r += l1 * l2 + 7;
      l1 *= 1.1111;
      l2 /= 1.1111;
    }
  }
}
