package eu.w4.contrib.tools.benchmark.tests;

import eu.w4.contrib.tools.benchmark.Test;

public class CPU32 extends Test
{

  private int l1 = 123456789;
  private int l2 = 987654321;
  public int r = 7;

  @Override
  public void runTest()
  {
    for(int i = 0; i<1000000000 ; i++)
    {
      r += (l1 * l2 + 7)^2;
      l1 += i;
      l2 -= i;
    }
  }
}
