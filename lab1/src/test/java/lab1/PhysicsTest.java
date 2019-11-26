package lab1;

import com.jme3.math.Vector3f;

import org.junit.*;
import static org.junit.Assert.*;

public class PhysicsTest{ 
	
	
//private static MainApp app2;
//
//	private static String pathVoyager = "src/test/resources/Voyager.txt";
//	private static String pathJupiter = "src/test/resources/Jupiter.txt";
//	@BeforeClass
//	public static void initApp2()
//	{
//		app2 = new MainApp();
//        Runnable task = new Runnable() {
//            public void run() {
//            	app2.setShowSettings(false);
//            	app2.start();
//            }
//        };
//        Thread thread = new Thread(task);
//        thread.start();
//
//        while (!app2.isInit || !app2.jupiter.isInit)
//        {
//            Thread.yield();
//        }	    
//        Vector3f localPosVoyager = app2.getStateManager().
//        								getState(Voyager.class).
//        								inputPosition(pathVoyager);
//
//        app2.getStateManager().
//        	 getState(Voyager.class).
//        	 getGeometry().setLocalTranslation(localPosVoyager);
//	}
//
//	@Test
//	public void checkPhysics()
//	{
//		app2.getStateManager().
//		getState(Voyager.class).
//		getPhysics().
//		setLinearVelocity(new Vector3f(0,0,0));
//	
//		Vector3f localPosVoyager = app2.getStateManager().
//				getState(Voyager.class).
//				inputPosition(pathVoyager);
//		Vector3f localPosJupiter = app2.getStateManager().
//				getState(Jupiter.class).
//				inputPosition(pathJupiter);
//		
//		Vector3f app2gravity = Physics.getGravity(app2.getStateManager().getState(Voyager.class).getGeometry().getLocalTranslation(),
//				app2.getStateManager().getState(Jupiter.class).getGeometry().getLocalTranslation(),
//				app2.getStateManager().getState(Jupiter.class).getWeight());
//		Vector3f myGravity = Physics.getGravity(localPosVoyager,localPosJupiter,30000f);
//		
//		assertEquals("same startup gravity", app2gravity, myGravity);
//	}
//	
//	@AfterClass
//	public static void endApp2()
//	{
//		app2.stop(true); 
//	}
}
