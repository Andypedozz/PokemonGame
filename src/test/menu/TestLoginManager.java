package test.menu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;

import interfaces.Model;
import model.ModelImpl;

public class TestLoginManager {

	/**
	 *  1. Account non trovato
	 *  2. Profilo occupato
	 *  3. Account  già in uso
	 */
	
	@Test
    public void testRegister() {
    	System.out.println("---- TEST REGISTER ----");
    	Model model = new ModelImpl();
    	assertTrue(model.getLoginManager().registerNoWrite("c", "3"));
    	assertNotNull(model.getFileManager().getDataMap().get("c"));
    	assertNull(model.getFileManager().getDataMap().get("d"));
    	assertTrue(model.getLoginManager().registerNoWrite("d", "4"));
    	assertFalse(model.getLoginManager().registerNoWrite("andypedoz", "0000"));
    	assertFalse(model.getLoginManager().registerNoWrite("b", "2"));
    }
	
    @Test
    public void testLogin() {
    	System.out.println("---- TEST LOGIN ----");
    	Model model = new ModelImpl();
    	model.getLoginManager().registerNoWrite("a", "1");
    	model.getLoginManager().registerNoWrite("b", "2");
    	model.getLoginManager().registerNoWrite("andypedoz", "0000");
    	assertEquals(model.getLoginManager().login("a", "1", 0), 0);
    	assertEquals(model.getLoginManager().login("b", "2", 0), 2);
    	assertEquals(model.getLoginManager().login("b", "2", 1), 0);
    	assertEquals(model.getLoginManager().login("b", "2", 1), 3);
    	assertEquals(model.getLoginManager().login("c", "3", 0), 1);
    }
    
    @Test
    public void testReady() {
    	System.out.println("---- TEST READY ----");
    	Model model = new ModelImpl();
    	model.getLoginManager().disconnect(0);
    	model.getLoginManager().disconnect(1);
    	assertEquals(model.getLoginManager().ready(),false);
    	model.getLoginManager().login("a", "1", 0);
    	assertEquals(model.getLoginManager().ready(),false);
    	model.getLoginManager().login("b", "2", 1);
    	assertEquals(model.getLoginManager().ready(),true);
    }
    
    @Test
    public void testDisconnect() {
    	System.out.println("---- TEST DISCONNECT ----");
    	Model model = new ModelImpl();
    	assertNotNull(model.getFileManager().getUsedDataArray(0));
    	model.getLoginManager().disconnect(0);
    	assertNull(model.getFileManager().getUsedDataArray(0));
    	assertNotNull(model.getFileManager().getUsedDataArray(1));
    	model.getLoginManager().disconnect(1);
    	assertNull(model.getFileManager().getUsedDataArray(1));
    	assertEquals(model.getLoginManager().disconnect(4), false);
    }
}
