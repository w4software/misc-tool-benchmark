package eu.w4.contrib.tools.benchmark.tests;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

import eu.w4.contrib.tools.benchmark.Test;

public class SmallFile extends Test
{

  private byte[] _chunk;

  public SmallFile()
  {
    final int size = 1024*1024;
    final Random random = new Random(0);
    _chunk = new byte[size];
    random.nextBytes(_chunk);
  }

  @Override
  public void runTest() throws Exception
  {
    for(long i = 0; i<1000 ; i++)
    {
      final File f = File.createTempFile("benchmark", ".bin");
      final FileOutputStream stream = new FileOutputStream(f);
      stream.write(_chunk, 0, _chunk.length);
      stream.flush();
      stream.close();
      f.delete();
    }
  }

}
