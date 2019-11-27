package lab1;

import com.jme3.math.Vector3f;

import org.junit.*;
import static org.junit.Assert.*;

public class PhysicsTest{ 
	
	@Test
	public void checkPhysics()
	{
		Vector3f voyagerPosition = new Vector3f(3f, 0f, 0f);
		Vector3f jupiterPosition = new Vector3f(0f, 0f, 0f);
		float jupiterWeight = 180f;	
		assertEquals(new Vector3f(-1f, 0f, 0f), Physics.getGravity(voyagerPosition, jupiterPosition, jupiterWeight));		
	}
	
}
