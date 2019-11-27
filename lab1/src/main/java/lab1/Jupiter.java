package lab1;

import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

import com.jme3.asset.AssetManager;
import com.jme3.math.Vector3f;
import com.jme3.scene.shape.Sphere;
import com.jme3.math.Quaternion;
import com.jme3.math.FastMath;
import com.jme3.bullet.BulletAppState;
import com.jme3.scene.Node;

public class Jupiter extends SpaceObject{
       
    private final static float jupiterWeight = 30000f;
    
    public Jupiter()
    {
        spaceObjectName = "Jupiter";
        spaceObjectRadius = 4.4f;
        spaceObjectMesh = new Sphere(64, 64, spaceObjectRadius);
    }
    
    public float getWeight()
    {
        return jupiterWeight;
    }
    
    @Override
    public void setStartupParametrs(Node rootNode, BulletAppState bulletAppState, 
    		AssetManager assetManager, String pathToFile) throws NoSuchElementException, FileNotFoundException 
    {
        super.setStartupParametrs(rootNode, bulletAppState, assetManager, pathToFile);
        //spaceObject rotate
        bulletAppState.getPhysicsSpace().remove(spaceObjectPhysics);
        spaceObjectGeo.removeControl(spaceObjectPhysics);  
        
        Quaternion roll = new Quaternion();
        roll.fromAngleAxis( FastMath.PI , new Vector3f(-1f,1f,1f) );
        spaceObjectGeo.setLocalRotation(roll);
        
        spaceObjectGeo.addControl(spaceObjectPhysics);
        bulletAppState.getPhysicsSpace().add(spaceObjectPhysics);
    }
    
}
