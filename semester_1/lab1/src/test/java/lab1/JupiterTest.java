package lab1;

import org.junit.*;

import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Sphere;
import com.jme3.texture.Texture;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

public class JupiterTest {
	private static Node rootNode = mock(Node.class);
	private static AssetManager assetManager = mock(AssetManager.class);
	private static BulletAppState bulletAppState = mock(BulletAppState.class);
	private static Texture texture = mock(Texture.class);
	private static Material material = mock(Material.class);
	private static PhysicsSpace physicsSpace = mock(PhysicsSpace.class);
	private static RigidBodyControl physics = mock(RigidBodyControl.class);
	private static Geometry geometry = mock(Geometry.class);
	
	@Test
  	public void createjupiter() {
		Jupiter jupiter = new Jupiter();
		System.out.println(jupiter.getMesh().getRadius());
		assertEquals(jupiter.getRadius(), 4.4f, 0.0001);		
	}
	
	@Test
	public void checkWeight()
	{
		Jupiter jupiter = new Jupiter();
		assertEquals(30000f, jupiter.getWeight(), 0.0001f);
	}
	
	@Test
	public void checkExceptionsInSetStartupParametres() {
		Jupiter jupiter = spy(new Jupiter());
		when(assetManager.loadTexture("img/Jupiter.jpg")).thenReturn(texture);
		when(bulletAppState.getPhysicsSpace()).thenReturn(physicsSpace);
		doNothing().when(physicsSpace).add(physics);
		doReturn(material).when(jupiter).makeMaterial(assetManager);
		doReturn(geometry).when(jupiter).makeGeometry(anyString(), isA(Sphere.class));
		doReturn(physics).when(jupiter).makePhysics(anyFloat());
		doNothing().when(jupiter).setPhysics(physics);
		String pathToFile = "src/test/resources/";
		{
			try {
				jupiter.setStartupParametrs(rootNode, bulletAppState, assetManager, pathToFile);
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
				jupiter.setStartupParametrs(rootNode, bulletAppState, assetManager, pathToFile);
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
				jupiter.setStartupParametrs(rootNode, bulletAppState, assetManager, pathToFile);
			}
			catch (NoSuchElementException | FileNotFoundException ex){
				fail("should have no Exceptions");
			}
		}
	}

}
