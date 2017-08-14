package com.example.file.summer;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.math.BigInteger;

import org.junit.Test;

import com.example.file.summer.BinaryFileAdder;

public class BinaryFileAdderTest {
	BinaryFileAdder unit;

	@Test
	public void whenSummingFileWith15AsTotalSumThen15IsReturned() throws IOException {
		unit = new BinaryFileAdder("src/test/resources/15sum");
		assertEquals(new BigInteger("15"), unit.calculateSum());
	}

	@Test
	public void whenSummingFileWith999KAsTotalSumThen999KIsReturned() throws IOException {
		unit = new BinaryFileAdder("src/test/resources/999Ksum");
		assertEquals(new BigInteger("999000"), unit.calculateSum());
	}
}
