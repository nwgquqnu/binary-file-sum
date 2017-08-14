package com.example.file.summer;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BinaryFileGenerator {

	private String path;
	private int step;
	private int count;

	public BinaryFileGenerator(String path, int step, int count) {
		this.path = path;
		this.step = step;
		this.count = count;
	}

	public void generate() throws IOException {
		Files.deleteIfExists(Paths.get(path));
		try (FileOutputStream fos = new FileOutputStream(path);
				BufferedOutputStream bos = new BufferedOutputStream(fos)) {

			long totalSum = 0;
			int value = 0;
			for (int i = 0; i < count; i++, value += step) {
				totalSum += value;
				bos.write((byte) (value >>> 0));
				bos.write((byte) (value >>> 8));
				bos.write((byte) (value >>> 16));
				bos.write((byte) (value >>> 24));
			}
			System.out.println("total sum should be " + totalSum);

		}
	}

}
