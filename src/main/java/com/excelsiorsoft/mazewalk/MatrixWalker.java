/**
 * 
 */
package com.excelsiorsoft.mazewalk;

import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excelsiorsoft.mazewalk.Configuration.ConfigIterator;
import com.excelsiorsoft.mazewalk.Configuration.TraversalRule;

/**
 * Encapsulates the implementation details of the problem as stated for this test
 * 
 * @author Simeon
 *
 *@see README.md
 */
public class MatrixWalker {
		
	private static final Logger LOGGER = LoggerFactory.getLogger(MatrixWalker.class);
	
	private static class ExplorationContext{
		
		Cell startingPoint;
		final Configuration<TraversalRule> explorationConfig; 
		MatrixDimensions unexploredTerritory;
		TraversalRule currentRule;
		TraversalRule nextRule;
		Long stepsLeft;
		int[][] matrix;
		
		
		ExplorationContext(final Cell origin, final Configuration<TraversalRule> explorationConfig, final MatrixDimensions unexploredTerritory, final TraversalRule currentRule, final long stepsLeft){
			this.startingPoint = origin;
			this.explorationConfig = explorationConfig;
			this.unexploredTerritory = unexploredTerritory;
			this.currentRule = currentRule;
			this.stepsLeft = stepsLeft;
		}
		
		@Override
		public String toString() {
			return "ExplorationContext [startingPoint=" + startingPoint + ", explorationConfig=" + explorationConfig
					+ ", unexploredTerritory=" + unexploredTerritory + ", stepsLeft=" + stepsLeft + "]";
		}
		
		

	}
	
	public static final class MatrixDimensions{
		
		int vert;
		int horiz;
		
		
		public MatrixDimensions(final int vertical, final int horizontal) {
			this.vert = vertical;
			this.horiz = horizontal;
		}


		@Override
		public String toString() {
			return "MatrixDimensions [vert=" + vert + ", horiz=" + horiz + "]";
		}
		
		
		 
	}
	
	public static final class Cell{
		
		int V;
		int H;

		public Cell(final int V, final int H) {
			this.V = V;
			this.H = H;
		}

		@Override
		public String toString() {
			return "Cell [V=" + V + ", H=" + H + "]";
		}
	}
	
	static class SpanContext{
		
		Cell startAt;
		int duration;
		int incrementH;
		int incrementV;
		Configuration<TraversalRule> configuration;
		TraversalRule nextRule;
		Long stepsLeft;
		int[][] matrix;
		
	}
	
	static class StepContext{
		int[][] matrix;
		int V;
		int H;
		Long stepsLeft;
	}
	
	
	@SuppressWarnings({ "unchecked", "unused" })
	private void walkTerritory(ExplorationContext context) {

		Cell startAt = context.startingPoint;
		Configuration<TraversalRule> config = context.explorationConfig;
		Iterator<TraversalRule> journeyNavigator =  config.iterator();
		TraversalRule travelPlan = journeyNavigator.next();
		context.currentRule = travelPlan;
		context.nextRule = ((ConfigIterator)journeyNavigator).peek();
		context.matrix = context.explorationConfig.matrix;

			walkSpan(context);
			if(context.stepsLeft>0) {
				walkTerritory(context);
			}
			
	}
	
	
	private void walkSpan(ExplorationContext context) {
		Cell startAt = context.startingPoint;
		MatrixDimensions unexploredTerritory = context.unexploredTerritory;
		TraversalRule currentRule = context.currentRule;

		SpanContext spanContext = new SpanContext();
		spanContext.startAt = startAt;
		spanContext.configuration = context.explorationConfig;
		spanContext.nextRule = context.nextRule;
		spanContext.stepsLeft = context.stepsLeft;
		spanContext.matrix = context.matrix;
		
		switch(currentRule.name) {
		
		case "HORIZONTAL.L_R":
			spanContext.duration = unexploredTerritory.horiz;
			spanContext.incrementH = currentRule.horizIdxIncrement;//1
			spanContext.incrementV = currentRule.vertIdxIncrement;//0
			walkSpan(spanContext);
			context.stepsLeft=spanContext.stepsLeft;
			if(unexploredTerritory.vert>0) unexploredTerritory.vert-=1;
			break;
		case "HORIZONTAL.R_L":
			spanContext.duration = unexploredTerritory.horiz;
			spanContext.incrementH = currentRule.horizIdxIncrement;//-1
			spanContext.incrementV = currentRule.vertIdxIncrement;//0
			walkSpan(spanContext);
			context.stepsLeft=spanContext.stepsLeft;
			if(unexploredTerritory.vert>0) unexploredTerritory.vert-=1;
			break;
		case "VERTICAL.U_D":
			spanContext.duration = unexploredTerritory.vert;
			spanContext.incrementV = currentRule.vertIdxIncrement;//1
			spanContext.incrementH = currentRule.horizIdxIncrement;//0
			walkSpan(spanContext);
			context.stepsLeft=spanContext.stepsLeft;
			if(unexploredTerritory.horiz>0) unexploredTerritory.horiz-=1;
			break;
		case "VERTICAL.D_U":
			spanContext.duration = unexploredTerritory.vert;
			spanContext.incrementV = currentRule.vertIdxIncrement;//-1
			spanContext.incrementH = currentRule.horizIdxIncrement;//0
			walkSpan(spanContext);
			context.stepsLeft=spanContext.stepsLeft;
			if(unexploredTerritory.horiz>0) unexploredTerritory.horiz-=1;
			break;
		
		}
		
	}
	
	
	private void walkSpan(SpanContext spanCtxt) {

		int duration = spanCtxt.duration;
		TraversalRule nextRule = spanCtxt.nextRule;

		for(int cntr = 0; cntr<duration;) {
			StepContext stepContext = new StepContext();
			stepContext.V = spanCtxt.startAt.V;
			stepContext.H = spanCtxt.startAt.H;
			stepContext.matrix = spanCtxt.matrix;
			stepContext.stepsLeft = spanCtxt.stepsLeft;
			makeStep(stepContext);
			spanCtxt.stepsLeft=stepContext.stepsLeft;
			spanCtxt.startAt.H += spanCtxt.incrementH;
			spanCtxt.startAt.V += spanCtxt.incrementV;
			if(cntr==duration-1) {
				spanCtxt.startAt.H=(spanCtxt.incrementV!=0)?spanCtxt.nextRule.horizIdxIncrement+spanCtxt.startAt.H:spanCtxt.startAt.H-spanCtxt.incrementH;
				spanCtxt.startAt.V=(spanCtxt.incrementH!=0)?spanCtxt.nextRule.vertIdxIncrement+spanCtxt.startAt.V:spanCtxt.startAt.V-spanCtxt.incrementV;
			}
			cntr++;
		}
		
	}
	
	private void makeStep(StepContext context) {
		int V = context.V;
		int H = context.H;
		//System.out.print("["+V+","+H+"]"); //for tests
		int[][] matrix = context.matrix;
		context.stepsLeft--;
		System.out.print(matrix[V][H]+" ");
	}
	
	public void walk(Configuration<TraversalRule> conf) {
		
		if(conf == null) throw new IllegalArgumentException("Configuration must be passed in");
		
		int vertDimension = conf.matrix.length;
		int horizDimension = conf.matrix[0].length;
		
		if(vertDimension <= 0) throw new IllegalArgumentException("vertDimension is unknown, cannot proceed.");
		if(horizDimension <= 0) throw new IllegalArgumentException("horizDimension is unknown, cannot proceed.");
		
		
		final long finalDestination = vertDimension * horizDimension;
		LOGGER.info("Walking matrix of size {}x{}={} steps using {}", vertDimension, horizDimension, finalDestination, conf);
		
		Cell startingPoint = new Cell(0,0);
		MatrixDimensions unexplored = new MatrixDimensions(vertDimension, horizDimension);
		
		ExplorationContext context = new ExplorationContext(startingPoint, conf, unexplored, null, finalDestination);
		this.walkTerritory(context);
	}
	
	/** For testing only while populated matrix is not important */
	protected void walk(int vertDimension, int horizDimension, Configuration<TraversalRule> conf) {
		
		if(vertDimension <= 0) throw new IllegalArgumentException("vertDimension is unknown, cannot proceed.");
		if(horizDimension <= 0) throw new IllegalArgumentException("horizDimension is unknown, cannot proceed.");
		if(conf == null) throw new IllegalArgumentException("Configuration must be passed in");
		
		final long finalDestination = vertDimension * horizDimension;
		LOGGER.info("Walking matrix of size {}x{}={} steps using {}", vertDimension, horizDimension, finalDestination, conf);
		
		Cell startingPoint = new Cell(0,0);
		MatrixDimensions unexplored = new MatrixDimensions(vertDimension, horizDimension);
		
		ExplorationContext context = new ExplorationContext(startingPoint, conf, unexplored, null, finalDestination);
		this.walkTerritory(context);
	}
	
		
}
