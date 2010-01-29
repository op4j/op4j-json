package org.op4j.contrib.executables.functions.conversion;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for org.op4j.contrib.operations.evaluation");
		//$JUnit-BEGIN$
		suite.addTestSuite(ToJsonStringTest.class);
		suite.addTestSuite(FromJsonTest.class);
		//$JUnit-END$
		return suite;
	}

}
