package lab1;

import com.jme3.asset.AssetManager;
import com.jme3.app.state.AbstractAppState;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Sphere;
import com.jme3.texture.Texture;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.BulletAppState;
import com.jme3.scene.Node;
import java.io.*;
import java.util.Scanner;

public abstract class SpaceObject extends AbstractAppState{
    
    protected Sphere objectMesh;
    protected Geometry objectGeo;
    protected Material objectMaterial;
    protected RigidBodyControl objectPhysics;
    protected String objectName;
    protected float objectRadius;
    public boolean isInit = false;
        
    public Vector3f inputPosition(String path)
    {
        try{
            File file = new File(path);
            Scanner in = new Scanner(file);
            Vector3f position = new Vector3f();
            position.x = (float) in.nextFloat();
            position.y = (float) in.nextFloat();
            position.z = (float) in.nextFloat();
            in.close();
            return position;
        }
        catch (Exception ex){
            return new Vector3f(0,0,0);
        }
    }
    
    public float inputPhysicsMass(String path)
    {
        try{
            File file = new File(path);
            Scanner in = new Scanner(file);
            float mass = 0;
            for (int i = 0;i < 4;++i)
                mass = (float) in.nextFloat();
            in.close();
            return mass;
        }
        catch (Exception ex){
            return 0;
        }
    }
    
    public void setStartupParametrs(Node rootNode, BulletAppState bulletAppState, AssetManager assetManager)
    {
        objectMesh.setTextureMode(Sphere.TextureMode.Projected);        
        //texture and material of object
        Texture jupiterTexture = assetManager.loadTexture("img/"+objectName+".jpg");
        jupiterTexture.setWrap(Texture.WrapMode.Repeat);
        objectMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        objectMaterial.setTexture("ColorMap", jupiterTexture);
                
        //object geometry
        objectGeo = new Geometry(objectName, objectMesh);
        objectGeo.setMaterial(objectMaterial);
                
        //object position
        String path = "src/main/resources/"+objectName+".txt";
        objectGeo.setLocalTranslation(inputPosition(path));
        
        float physicsMass = inputPhysicsMass(path);
        //object physics
        objectPhysics = new RigidBodyControl(physicsMass);
        objectGeo.addControl(objectPhysics);
        
        bulletAppState.getPhysicsSpace().add(objectPhysics);
            
        
        
        //attach to main scene
        rootNode.attachChild(objectGeo);  
    	isInit = true;
    }
    
    
    public void setGeometry(Geometry geometry)
    {
        objectGeo = geometry;
    }
    
    public Geometry getGeometry()
    {
        return objectGeo;
    }
        
    public void setMaterial(Material material)
    {
        objectMaterial = material;
    }
    
    public Material getMaterial()
    {
        return objectMaterial;
    }
    
    public void setPhysics(RigidBodyControl physics)
    {
        objectPhysics = physics;
    }
    
    public RigidBodyControl getPhysics()
    {
        return objectPhysics;
    }
    
    public Sphere getMesh()
    {
        return objectMesh;
    }
    
    public Vector3f getPosition()
    {
        return objectGeo.getLocalTranslation();
    }
    
}
