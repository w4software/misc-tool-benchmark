Java Benchmark Tool
===================

This tool is designed to benchmark the performance of the host it is running on. It will monitor the speed of CPU, RAM and disk.


Download
--------

The binary package is available from the [release page](https://github.com/w4software/misc-tool-benchmark/releases)


The source package can be obtained either as a [zip archive] or using git

    git clone https://github.com/w4software/misc-tool-benchmark


Usage
-----

To start the benchmark, run the following command

    java -jar misc-tool-benchmark-1.0.jar [number of threads]

The parameter _number of threads_ is optional. If not provided, the benchmark
will try to determine the number of real cpu cores instead of relying on the
OS determined value that is a number of cpu threads (that may be different when hyperthreading-like features are in enabled).

As a starter, you may want to begin with a _number of thread_ of __1__ before
trying to test performance in parallel processing. 

The overall process should usually take a couple of minutes on modern platforms.


Tests
-----

Following tests are run during the benchmark


### CPU Tests
- CPU32: Make some mathematical computation using 32-bit integers
- CPU64: Make some mathematical computation using 64-bit integers
- Float32: Make some mathematical computation using 32-bit precision floating point numbers
- Float64: Make some mathematical computation using 64-bit (double) precision floating point numbers


### RAM Tests

- RAM: Make copy from memory to memory zones (total of 5 GB transfer)


### Disk Tests

- SmallFile: write some 1M sized temporary files


Results
-------

The results will show the time taken to complete all tests so, the lower result reflect a better performance.

As a comparison point, following results can be used as references


### Reference 1

    Year: 2012
    CPU: Intel Xeon E5 (v1) 2.2 Ghz 10M cache
    RAM: DDR3 1066 Mhz
    DISK: local SSD SATA3 disk
    OS: Linux 3.2.0-4-amd64 (amd64)
    Java: Oracle Corporation 1.7.0_65

    CPU32: 2307 ms
    CPU64: 1825 ms
    Float32: 2754 ms
    Float64: 3216 ms
    RAM: 4655 ms
    SmallFile: 1077 ms


### Reference 2

    Year: 2014
    CPU: Intel Core i5 4th-gen 2.7 Ghz 2M cache
    RAM: DDR3 1600 Mhz
    DISK: local SSHD (hybrid) SATA3 disk
    OS: Windows 7 6.1 (amd64)
    Java: Oracle Corporation 1.7.0_67

    CPU32: 1340 ms
    CPU64: 1990 ms
    Float32: 950 ms
    Float64: 1100 ms
    RAM: 2660 ms
    SmallFile: 2300 ms


### Reference 3

    Year: 2006
    CPU: Intel Xeon 5300 series 1.6 Ghz 8M cache
    RAM: DDR2 667 MHz
    DISK: local 7200RPM 16M-cache SATA2 disk 
    OS: Linux 2.6.26-2-686 (i386)
    Java: Sun Microsystems Inc. 1.6.0_26

    CPU32: 5616 ms
    CPU64: 18170 ms
    Float32: 5924 ms
    Float64: 4358 ms
    RAM: 11783 ms
    SmallFile: 4523 ms


License
-------

Copyright (c) 2015, W4 S.A. 

This project is licensed under the terms of the MIT License (see LICENSE file)

Ce projet est licencié sous les termes de la licence MIT (voir le fichier LICENSE)