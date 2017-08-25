/**
 * 
 */
package com.excelsiorsoft.mazewalk;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Simeon
 *
 */
public class Configuration<TraversalRule> implements Iterable <TraversalRule> {
	

	public static abstract class Direction {}
	public static class VERTICAL extends Direction{
		public final static String U_D = "VERTICAL.U_D";
		public final static String D_U = "VERTICAL.D_U";
		public final static int NUM_OF_DIRS=2;
	}
	public static class HORIZONTAL extends Direction{
		public static final String R_L = "HORIZONTAL.R_L";
		public static final String L_R = "HORIZONTAL.L_R";
		public final static int NUM_OF_DIRS=2;
	}
	
	public static class TraversalRule {
		
		final String name;
		final int vertIdxIncrement;
		final int horizIdxIncrement;
		
		TraversalRule(String name, int vertIdxIncrement, int horizIdxIncrement){
			this.name = name;
			this.vertIdxIncrement = vertIdxIncrement;
			this.horizIdxIncrement = horizIdxIncrement;
		}

		@Override
		public String toString() {
			return "TraversalRule [name=" + name + ", vertIdxIncrement=" + vertIdxIncrement + ", horizIdxIncrement="
					+ horizIdxIncrement + "]";
		}
		
		
	}
	
		
	private final TraversalRule[] rules;
	private  ConfigIterator iterator;
	public int[][] matrix;
	
	
	public Configuration(TraversalRule [] rules){
		this.rules = rules;
	}

	private int capacity = VERTICAL.NUM_OF_DIRS+HORIZONTAL.NUM_OF_DIRS;
	
	@Override
	public String toString() {
		StringBuffer result = new StringBuffer("Configuration [size = ").append(this.capacity);
		result.append("]");
		return result.toString();
	}


	 class ConfigIterator implements Iterator<TraversalRule>{
		
		 private int capacity = Configuration.this.capacity;
		 private int cursorPosition = 0;
		 

		@Override
		public boolean hasNext() {
			return true;
		}

		@Override
		public TraversalRule next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			return rules[cursorPosition++%capacity];
		}
		
		public TraversalRule peek() {
			int nextPosition = cursorPosition==0?cursorPosition+1:cursorPosition%capacity;
			return rules[nextPosition];
		}
	
	 }


	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Iterator iterator() {
		ConfigIterator result = (this.iterator == null)?new ConfigIterator():this.iterator;
		this.iterator = result;
		return result;
	}
	
	


}
