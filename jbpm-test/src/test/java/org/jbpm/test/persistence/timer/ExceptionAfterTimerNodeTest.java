package org.jbpm.test.persistence.timer;

import java.util.Arrays;
import java.util.Collection;

import org.jbpm.test.JbpmJUnitBaseTestCase;
import org.jbpm.test.JbpmJUnitBaseTestCase.Strategy;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.process.ProcessInstance;

@RunWith(Parameterized.class)
public class ExceptionAfterTimerNodeTest extends JbpmJUnitBaseTestCase {

	private final boolean useQuartz;
	
	@Parameters(name="Use quertz scheduler={0}")
    public static Collection<Object[]> parameters() {
        Object[][] locking = new Object[][] { 
                { true }, 
                { false }
                };
        return Arrays.asList(locking);
    };    
	
	public ExceptionAfterTimerNodeTest(boolean useQuartz) {
		super(true, true);
		this.useQuartz = useQuartz;
	}
	
	@Before
	public void setup() {
		if (useQuartz) {
			System.setProperty("org.quartz.properties", "quartz-ram.properties");
		}
	}
	
	@After
	public void cleanup() {
		System.clearProperty("org.quartz.properties");
	}
	
	@Test
    public void testExceptionAfterTimer() {
        createRuntimeManager("BPMN2-ExceptionAfterTimer.bpmn2");
        RuntimeEngine runtimeEngine = getRuntimeEngine();
        KieSession ksession = runtimeEngine.getKieSession();
        
        ProcessInstance pi = ksession.startProcess("com.bpms.customer.RuntimeExceptionAfterTimer");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        pi = ksession.getProcessInstance(pi.getId());
        assertNull(pi);
	}
}
