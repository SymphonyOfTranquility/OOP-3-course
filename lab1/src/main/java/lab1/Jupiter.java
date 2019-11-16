package lab1;

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
        objectName = "Jupiter";
        objectRadius = 4.4f;
        objectMesh = new Sphere(64, 64, objectRadius);
    }
    
    public float getWeight()
    {
        return jupiterWeight;
    }
    
    @Override
    public void setStartupParametrs(Node rootNode, BulletAppState bulletAppState, AssetManager assetManager)
    {
        super.setStartupParametrs(rootNode, bulletAppState, assetManager);
        //object rotate
        bulletAppState.getPhysicsSpace().remove(objectPhysics);
        objectGeo.removeControl(objectPhysics);  
        
        Quaternion roll = new Quaternion();
        roll.fromAngleAxis( FastMath.PI , new Vector3f(-1f,1f,1f) );
        objectGeo.setLocalRotation(roll);
        
        objectGeo.addControl(objectPhysics);
        bulletAppState.getPhysicsSpace().add(objectPhysics);
    }
    
}
