package com.excelsiorsoft.mazewalk;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excelsiorsoft.mazewalk.Configuration.HORIZONTAL;
import com.excelsiorsoft.mazewalk.Configuration.TraversalRule;
import com.excelsiorsoft.mazewalk.Configuration.VERTICAL;

public class MatrixWalkerTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(MatrixWalkerTest.class);
	
	MatrixWalker cut = new MatrixWalker();
	Configuration<TraversalRule> config;

	@Before
	public void init() {
		TraversalRule[] rules = { /*direction*/  /*v*/ /*h*/
				new TraversalRule(HORIZONTAL.L_R,   0,   1), 
				new TraversalRule(VERTICAL.U_D,	    1,   0),
				new TraversalRule(HORIZONTAL.R_L,   0,  -1), 
				new TraversalRule(VERTICAL.D_U,    -1,   0) 
				};
		config = new Configuration<>(rules);
	}
	
	
	
	@Test
	public void test7x5() {
		final int vertDimension = 7;
		final int horizDimension = 5;
		cut.walk(vertDimension, horizDimension, config);
	}
	
	@Test
	public void test2x3() {
		final int vertDimension = 2;
		final int horizDimension = 3;
		cut.walk(vertDimension, horizDimension, config);
	}
	
	@Test
	public void test4x3() {
		final int vertDimension = 4;
		final int horizDimension = 3;
		cut.walk(vertDimension, horizDimension, config);
	}
	
	@Test
	public void test4x1() {
		final int vertDimension = 4;
		final int horizDimension = 1;
		cut.walk(vertDimension, horizDimension, config);
	}
	
	@Test
	public void test1x5() {
		final int vertDimension = 1;
		final int horizDimension = 5;
		cut.walk(vertDimension, horizDimension, config);
	}
	
	@Test
	public void test12x7() {
		final int vertDimension = 12;
		final int horizDimension = 7;
		cut.walk(vertDimension, horizDimension, config);
	}
	
	@Test
	public void test7x5Matrix() {
		
		int[] row0 = {1,2,3,4,5};
		int[] row1 = {6,7,8,9,10};
		int[] row2 = {11,12,13,14,15};
		int[] row3 = {16,17,18,19,20};
		int[] row4 = {21,22,23,24,25};
		int[] row5 = {26,27,28,29,30};
		int[] row6 = {31,32,33,34,35};
		
		int[][] matrix = {row0, row1, row2, row3, row4, row5, row6};
		//System.out.println(matrix.length+":"+matrix[0].length);
		config.matrix = matrix;
		cut.walk(config);
		
		//System.out.println(matrix[1][0]);
	}
	
	@Test
	public void test4x4Matrix() {
		
		int[] row0 = {1,2,3,4};
		int[] row1 = {5,6,7,8};
		int[] row2 = {9, 10, 11, 12};
		int[] row3 = {13, 14, 15, 16};
		
		int[][] matrix = {row0, row1, row2, row3};
		//System.out.println(matrix.length+":"+matrix[0].length);
		config.matrix = matrix;
		cut.walk(config);
		
		//System.out.println(matrix[1][0]);
	}
	
}
