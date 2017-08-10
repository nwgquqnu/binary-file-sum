package com.example.file_summer;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class BinaryFileAdder {
	private static final int BIGSIZE = 64 * 1024;
	private static final int SIZE = 8 * 1024;
	
	private String fileToSum;
	private byte currentBytePosition = 3;
	private long totalSum = 0l;
	private boolean calculated = false;
	
	public BinaryFileAdder(String fileToSum) {
		this.fileToSum = fileToSum;
	}
	
	public BigInteger calculateSum() throws IOException {
		if (!calculated) {
			doCalculate();
		}
		return convertToUnsigned(totalSum);
	}
	
	private void doCalculate() throws IOException {
		try (FileInputStream fis = new FileInputStream(fileToSum)) {
			FileChannel channel = fis.getChannel();
			ByteBuffer buffer = ByteBuffer.allocateDirect(BIGSIZE);
			byte[] bufferDumpArray = new byte[SIZE];
			int nRead;
			while ((nRead = channel.read(buffer)) != -1) {
				if (nRead == 0) {
					continue;
				}
				buffer.position(0);
				buffer.limit(nRead);
				sumBuffer(buffer, bufferDumpArray);
				buffer.clear();
			}
		}
		calculated = true;
	}

	private void sumBuffer(ByteBuffer buffer, byte[] bufferDumpArray) {
		int nGet;
		while (buffer.hasRemaining()) {
			nGet = Math.min(buffer.remaining(), SIZE);
			buffer.get(bufferDumpArray, 0, nGet);
			sumBufferPortion(bufferDumpArray, nGet);
		}
	}

	private void sumBufferPortion(byte[] bufferDumpArray, int nGet) {
		long value;
		for (int i = 0; i < nGet; i++) {
			value = Byte.toUnsignedLong(bufferDumpArray[i]);
			totalSum += value << (calculateNextBytePosition() * 8);
		}
	}
	
	private byte calculateNextBytePosition() {
		currentBytePosition += 1;
		if (currentBytePosition > 3) {
			currentBytePosition = 0;
		}
		return currentBytePosition;
	}
	
	
	private BigInteger convertToUnsigned(long sum) {
		return new BigInteger(new byte[] {
				0,
				(byte) (sum >>> 56),
				(byte) (sum >>> 48),
				(byte) (sum >>> 40),
				(byte) (sum >>> 32),
				(byte) (sum >>> 24),
				(byte) (sum >>> 16),
				(byte) (sum >>>  8),
				(byte) (sum >>>  0)
		});
	}
	

}
