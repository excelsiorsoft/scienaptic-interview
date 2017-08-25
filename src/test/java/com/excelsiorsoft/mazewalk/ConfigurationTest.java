package com.excelsiorsoft.mazewalk;

import static org.assertj.core.api.Assertions.*;

import java.util.Iterator;

import static com.excelsiorsoft.mazewalk.Configuration.TraversalRule;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excelsiorsoft.mazewalk.Configuration.HORIZONTAL;

import com.excelsiorsoft.mazewalk.Configuration.VERTICAL;

public class ConfigurationTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationTest.class);

	Configuration<TraversalRule> cut;

	@Before
	public void init() {
		TraversalRule[] rules = { /*direction*/  /*v*/ /*h*/
				new TraversalRule(HORIZONTAL.L_R,   0,   1), 
				new TraversalRule(VERTICAL.U_D,	    1,   0),
				new TraversalRule(HORIZONTAL.R_L,   0,  -1), 
				new TraversalRule(VERTICAL.D_U,    -1,   0) 
				};
		cut = new Configuration<>(rules);
	}

	@Test
	public void test() {
		for(Iterator<TraversalRule> it = cut.iterator();it.hasNext();) {
			System.out.println(it.next());
		}
	}

}
