package lab1;

import com.jme3.asset.AssetManager;
import com.jme3.scene.Geometry;
import com.jme3.math.*;
import java.io.*;
import java.util.Scanner;
import java.lang.Math;

public class Jupiter extends SpaceObject{
    
    private final static float G = 0.05f;      
    private final static float jupiterWeight = 30000f;
    
    public Jupiter()
    {
        objectRadius = 4.4f;
        object = new Sphere(128, 128, jupiterRadius);
        object.setTextureMode(Sphere.TextureMode.Projected);        
    }
    
    @Override
    public void setStartupParametrs(Node rootNode, BulletAppState bulletAppState, AssetManager assetManager)
    {
        //texture and material of jupiter
        Texture jupiterTexture = assetManager.loadTexture("img/Jupiter.jpg");
        jupiterTexture.setWrap(Texture.WrapMode.Repeat);
        objectMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        objectMaterial.setTexture("ColorMap", jupiterTexture);
        
        
        //jupiter geometry
        objectGeo = new Geometry("Jupiter", objectMesh);
        objectGeo.setMaterial(objectMaterial);
        
        //jupiter rotate
        Quaternion roll = new Quaternion();
        roll.fromAngleAxis( FastMath.PI , new Vector3f(-1,0.5f,1) );
        objectGeo.setLocalRotation(roll);
        
        //jupiter position
        
        objectGeo.setLocalTranslation();
        
        //jupiter physics
        objectPhysics = new RigidBodyControl(0.0f);
        objectGeo.addControl(objectPhysics);
        
        bulletAppState.getPhysicsSpace().add(objectPhysics);
            
        
        //attach to main scene
        rootNode.attachChild(objectGeo);    
    }
    
}
