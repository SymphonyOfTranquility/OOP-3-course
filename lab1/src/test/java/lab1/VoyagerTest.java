package lab1;

import static org.mockito.Mockito.*;

import java.util.NoSuchElementException;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.material.Material;
import com.jme3.asset.AssetKey;
import com.jme3.asset.AssetManager;
import com.jme3.math.Vector3f;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.texture.Texture;
import java.io.*;
import java.nio.file.Path;
import java.util.InputMismatchException;
import java.util.Scanner;
import com.jme3.scene.shape.Sphere;


import org.junit.*;
import static org.junit.Assert.*;

public class VoyagerTest {
	private static Node rootNode = mock(Node.class);
	private static AssetManager assetManager = mock(AssetManager.class);
	private static BulletAppState bulletAppState = mock(BulletAppState.class);
	private static Texture texture = mock(Texture.class);
	private static Material material = mock(Material.class);
	private static PhysicsSpace physicsSpace = mock(PhysicsSpace.class);
	private static RigidBodyControl physics = mock(RigidBodyControl.class);
	private static Geometry geometry = mock(Geometry.class);

	@SuppressWarnings("unchecked")
	@BeforeClass
	public static void before() {
		when(assetManager.loadTexture("img/Voyager.jpg")).thenReturn(texture);
		when(assetManager.loadAsset(new AssetKey("Common/MatDefs/Misc/Unshaded.j3md"))).thenReturn(material);
		when(bulletAppState.getPhysicsSpace()).thenReturn(physicsSpace);
		doNothing().when(physicsSpace).add(physics);
	}
	
	@Test
  	public void createVoyager()
	{
		Voyager voyager = spy(new Voyager());
		System.out.println(voyager.getMesh().getRadius());
		assertEquals(voyager.getRadius(), 1f, 0.0001);		
	}
	
	@Test
	public void checkExceptionsInSetStartupParametres() {
		Voyager voyager = spy(new Voyager());
		doReturn(geometry).when(voyager).makeGeometry(anyString(), isA(Sphere.class));
		doReturn(physics).when(voyager).makePhysics(anyFloat());
		doNothing().when(voyager).setPhysics(physics);
		String pathToFile = "src/test/resources/";
		{
			try {
				voyager.setStartupParametrs(rootNode, bulletAppState, assetManager, pathToFile);
				fail("should have NoSuchElementException");
			}
			catch (NoSuchElementException ex){
				fail("should have NoSuchElementException");
			}
			catch (FileNotFoundException ex){
				//expected
			}
		}
		
		{	
			pathToFile = "src/test/resources/CrashedFiles/";
			try {
				voyager.setStartupParametrs(rootNode, bulletAppState, assetManager, pathToFile);
				fail("should have NoSuchElementException");
			}
			catch (NoSuchElementException ex){
				//expected
			}
			catch (FileNotFoundException ex){
				fail("should have NoSuchElementException");
			}
		}	
		{	
			pathToFile = "src/test/resources/NormalFiles/";
			try {
				voyager.setStartupParametrs(rootNode, bulletAppState, assetManager, pathToFile);
			}
			catch (NoSuchElementException | FileNotFoundException ex){
				fail("should have no Exceptions");
			}
		}
	}
	
	
	@Test 
	public void checkStartMoving() {
		Voyager voyager = spy(new Voyager());
		doNothing().when(physics).setLinearVelocity(isA(Vector3f.class));
		voyager.setGeometry(geometry);
		voyager.setPhysics(physics);
		String pathToFile = "src/test/resources/NormalFiles/";
		try {
			voyager.startMoving(pathToFile);
			verify(physics).setLinearVelocity(isA(Vector3f.class));
		}
		catch (Exception ex)
		{
			fail("have to be no exceptions");
		}
	}
	
	
	
	
//	private static MainApp app;
//	private static AssetManager assetManager;
//	private static BulletAppState bulletAppState;
//	private static Node rootNode;
//	private static String path = "src/test/resources/Voyager.txt";
//	
//	@BeforeClass
//	public static void initApp()
//	{
//		app = new MainApp();
//        Runnable task = new Runnable() {
//            public void run() {
//            	app.setShowSettings(false);
//            	app.start();
//            }
//        };
//        Thread thread = new Thread(task);
//        thread.start();
//
//        while (!app.isInit || app.voyager.isInit)
//        {
//            Thread.yield();
//        }
//        
//        assetManager = app.getAssetManager();
//        bulletAppState = app.getStateManager().getState(BulletAppState.class);
//        rootNode = app.getRootNode();
//	}
//
//	@Test
//	public void createVoyager()
//	{
//		Voyager voyager = new Voyager();
//		assertEquals("radius are equals",
//				      app.getStateManager().getState(Voyager.class).getMesh().getRadius(),
//				      voyager.getMesh().getRadius(),
//				      0.0001);
//		
//		app.getStateManager().
//			getState(Voyager.class).
//			getPhysics().
//			setLinearVelocity(new Vector3f(0,0,0));
//
//        Vector3f localPos = app.getStateManager().
//    					   getState(Voyager.class).
//    					   inputPosition(path);
//		
//		app.getStateManager().
//			getState(Voyager.class).
//			getGeometry().setLocalTranslation(localPos);
//		
//		voyager.setStartupParametrs(rootNode, bulletAppState, assetManager);
//		
//		assertEquals("same startup positions", voyager.getGeometry().getLocalTranslation(),
//				app.getStateManager().
//				getState(Voyager.class).
//				getGeometry().getLocalTranslation());
//	}
//	@Test
//	public void checkInputVelocityVector()
//	{ 
//		try{
//	        File file = new File(path);
//	        Scanner in = new Scanner(file);
//	        Vector3f velocityVector = new Vector3f(0,0,0);
//	        for (int i = 0;i < 5;++i)
//	            velocityVector.x = (float) in.nextFloat();
//	        velocityVector.y = (float) in.nextFloat();
//	        velocityVector.z = (float) in.nextFloat();
//	        in.close();
//	        
//	        Vector3f appVelocityVector = app.getStateManager().getState(Voyager.class).inputVelocityVector(path);
//	        
//			assertEquals("Same input linear velocity vector on x",
//					appVelocityVector,
//					velocityVector);
//	    }
//	    catch (Exception ex){
//	    	System.out.println("some problems with input file");
//	    }
//	}
//	@Test
//	public void checkStartMoving()
//	{
//		app.getStateManager().getState(Voyager.class).startMoving();
//
//		assertNotEquals("linear velocity vector is not (0,0,0)",
//				app.getStateManager().
//				getState(Voyager.class).
//				getPhysics().getLinearVelocity(),
//				new Vector3f(0,0,0));
//	}
//	
//	@AfterClass
//	public static void endApp()
//	{
//        app.stop(true); 
//	}

}
