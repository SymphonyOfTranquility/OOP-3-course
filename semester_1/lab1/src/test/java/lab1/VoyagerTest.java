package lab1;

import java.util.NoSuchElementException;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Sphere;
import com.jme3.material.Material;
import com.jme3.asset.AssetManager;
import com.jme3.math.Vector3f;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.texture.Texture;
import java.io.*;

import static org.mockito.Mockito.*;
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
	
	@Test
  	public void createVoyager() {
		Voyager voyager = spy(new Voyager());
		System.out.println(voyager.getMesh().getRadius());
		assertEquals(voyager.getRadius(), 1f, 0.0001);		
	}
	
	@Test
	public void checkExceptionsInSetStartupParametres() {
		Voyager voyager = spy(new Voyager());
		when(assetManager.loadTexture("img/Voyager.jpg")).thenReturn(texture);
		when(bulletAppState.getPhysicsSpace()).thenReturn(physicsSpace);
		doNothing().when(physicsSpace).add(physics);
		doReturn(material).when(voyager).makeMaterial(assetManager);
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
}
