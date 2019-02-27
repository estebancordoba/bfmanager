package com.bfmanager.test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runners.Suite;

import com.bfmanager.test.integration.TestDAO;
import com.bfmanager.test.ui.TestUI;
import com.bfmanager.test.unit.TestBFI;

@RunWith(Suite.class)

@Suite.SuiteClasses({
   TestBFI.class,
   TestDAO.class,
   TestUI.class,
})

public class AllTests {    
	public static void main(String[] args) {
      Result result = JUnitCore.runClasses(AllTests.class);

       for (Failure failure : result.getFailures()) {
         System.out.println(failure.toString());
      }

       System.out.println(result.wasSuccessful());
   }
}