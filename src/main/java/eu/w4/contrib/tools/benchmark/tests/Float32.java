package eu.w4.contrib.tools.benchmark.tests;

import eu.w4.contrib.tools.benchmark.Test;

public class Float32 extends Test
{

  private float l1 = (float) Math.PI;
  private float l2 = (float) Math.E;
  public float r = 7;

  @Override
  public void runTest()
  {
    for(int i = 0; i<20000000 ; i++)
    {
      r += l1 * l2 + 7;
      l1 *= 1.1111;
      l2 /= 1.1111;
    }
  }
}
