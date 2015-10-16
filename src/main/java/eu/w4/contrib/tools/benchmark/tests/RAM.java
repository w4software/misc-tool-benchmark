package eu.w4.contrib.tools.benchmark.tests;

import java.util.Random;

import eu.w4.contrib.tools.benchmark.Test;

public class RAM extends Test
{

  private byte[] _buffer;
  public byte[] _target;

  public RAM()
  {
    final int size = 50*1024*1024;
    final Random random = new Random(0);
    _buffer = new byte[size];
    random.nextBytes(_buffer);
  }

  @Override
  public void runTest() throws Exception
  {
    for(long i = 0; i<100 ; i++)
    {
      _target = new byte[_buffer.length];
      System.arraycopy(_buffer, 0, _target, 0, _buffer.length);
    }
  }
}
