package lab1;

import com.jme3.app.SimpleApplication;
import com.jme3.scene.Node;
import com.jme3.asset.AssetManager;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.material.RenderState.BlendMode;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Sphere;
import com.jme3.texture.Texture;
import com.jme3.asset.TextureKey;
import com.jme3.math.Quaternion;
import com.jme3.math.FastMath;
import com.jme3.util.TangentBinormalGenerator;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.renderer.Camera;
import com.jme3.bullet.BulletAppState;
import com.jme3.scene.Node;
import com.jme3.math.*;
import java.io.*;
import java.util.Scanner;
import java.lang.Math;

import org.junit.*;
import static org.junit.Assert.*;

public class VoyagerTest {
	private static MainApp app;
	private static AssetManager assetManager;
	private static BulletAppState bulletAppState;
	private static Node rootNode;
	private static String path = "src/test/resources/Voyager.txt";
	
	@BeforeClass
	public static void initApp()
	{
		app = new MainApp();
        Runnable task = new Runnable() {
            public void run() {
                app.setShowSettings(false);
                app.start();
            }
        };
        Thread thread = new Thread(task);
        thread.start();

        while (!app.isInit)
        {
            Thread.yield();
        }
        while (!app.voyager.isInit)
        {
            Thread.yield();
        }
        
        assetManager = app.getAssetManager();
        bulletAppState = app.getStateManager().getState(BulletAppState.class);
        rootNode = app.getRootNode();
	}

	@Test
	public void createVoyager()
	{
		Voyager voyager = new Voyager();
		assertEquals("radius are equals",
				      app.getStateManager().getState(Voyager.class).getMesh().getRadius(),
				      voyager.getMesh().getRadius(),
				      0.0001);
		
		app.getStateManager().
			getState(Voyager.class).
			getPhysics().
			setLinearVelocity(new Vector3f(0,0,0));

        Vector3f localPos = app.getStateManager().
    					   getState(Voyager.class).
    					   inputPosition(path);
		
		app.getStateManager().
			getState(Voyager.class).
			getGeometry().setLocalTranslation(localPos);
		
		voyager.setStartupParametrs(rootNode, bulletAppState, assetManager);
		
		assertEquals("same startup positions", voyager.getGeometry().getLocalTranslation(),
				app.getStateManager().
				getState(Voyager.class).
				getGeometry().getLocalTranslation());
	}
	@Test
	public void checkInputVelocityVector()
	{ 
		try{
	        File file = new File(path);
	        Scanner in = new Scanner(file);
	        Vector3f velocityVector = new Vector3f(0,0,0);
	        for (int i = 0;i < 5;++i)
	            velocityVector.x = (float) in.nextFloat();
	        velocityVector.y = (float) in.nextFloat();
	        velocityVector.z = (float) in.nextFloat();
	        in.close();
	        
	        Vector3f appVelocityVector = app.getStateManager().getState(Voyager.class).inputVelocityVector(path);
	        
			assertEquals("Same input linear velocity vector on x",
					appVelocityVector,
					velocityVector);
	    }
	    catch (Exception ex){
	    	System.out.println("some problems with input file");
	    }
	}
	@Test
	public void checkStartMoving()
	{
		app.getStateManager().getState(Voyager.class).startMoving();

		assertNotEquals("linear velocity vector is not (0,0,0)",
				app.getStateManager().
				getState(Voyager.class).
				getPhysics().getLinearVelocity(),
				new Vector3f(0,0,0));
	}
	
	@AfterClass
	public static void endApp()
	{
        app.stop(true); 
	}

}
