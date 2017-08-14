package com.example.file.summer;

import java.io.IOException;

public class App {

	private static final String DEBUG = "--debug";
	private static final String GENERATE = "--generate";

	public static void main(String[] args) throws IOException {
		if (args.length == 0 || "".equals(args[0])) {
			System.out.println("This program allows you to sum values from files saved in little endian");
			System.out.println("Usage:");
			System.out.println("\tprogram /path/to/binary/file [--debug]");
			System.out.println("\t--debug - add it to print calculation time");

			System.out.println("\n\n");
			System.out.println("To generate file use: ");
			System.out.println("\tprogram --generate /path/to/file step count");
			System.out.println("\tstep - integer value to use for increment");
			System.out.println("\tcount - number of steps to perform");
			return;
		}

		if (GENERATE.equals(args[0])) {
			generateFile(args[1], Integer.parseInt(args[2]), Integer.parseInt(args[3]));
			return;
		}

		sumFile(args[0], args.length > 1 && DEBUG.equals(args[1]));

	}

	private static void generateFile(String path, int step, int count) throws IOException {
		BinaryFileGenerator generator = new BinaryFileGenerator(path, step, count);
		generator.generate();
	}

	private static void sumFile(String name, boolean printTime) throws IOException {
		long startTime = System.currentTimeMillis();
		BinaryFileAdder adder = new BinaryFileAdder(name);
		System.out.println("Total sum is " + adder.calculateSum());
		if (printTime) {
			System.out.println("Execution time in milliseconds is " + (System.currentTimeMillis() - startTime));
		}
	}
}
