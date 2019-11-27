package lab1;

import java.util.NoSuchElementException;
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
    
    protected Sphere spaceObjectMesh;
    protected Geometry spaceObjectGeo;
    protected Material spaceObjectMaterial;
    protected RigidBodyControl spaceObjectPhysics;
    protected String spaceObjectName;
    protected float spaceObjectRadius;
    public boolean isInit = false;
        
    public Vector3f inputPosition(String path) throws NoSuchElementException, FileNotFoundException {
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
        catch (NoSuchElementException | FileNotFoundException ex) {
        	throw ex;
        }
    }
    
    public float inputPhysicsMass(String path) throws NoSuchElementException, FileNotFoundException {
        try{
            File file = new File(path);
            Scanner in = new Scanner(file);
            float mass = 0;
            for (int i = 0;i < 4;++i)
                mass = (float) in.nextFloat();
            in.close();
            return mass;
        }
        catch (NoSuchElementException | FileNotFoundException ex) {
        	throw ex;
        }
    }
    
    public void setStartupParametrs(Node rootNode, BulletAppState bulletAppState, 
    		AssetManager assetManager, String pathToFile) throws NoSuchElementException, FileNotFoundException {
        spaceObjectMesh.setTextureMode(Sphere.TextureMode.Projected);        
        //texture and material of object
        Texture jupiterTexture = assetManager.loadTexture("img/"+spaceObjectName+".jpg");
        jupiterTexture.setWrap(Texture.WrapMode.Repeat);
        
        spaceObjectMaterial = makeMaterial(assetManager);
        spaceObjectMaterial.setTexture("ColorMap", jupiterTexture);
                
        //spaceObject geometry
        spaceObjectGeo = makeGeometry(spaceObjectName, spaceObjectMesh);
        spaceObjectGeo.setMaterial(spaceObjectMaterial);
                
        //spaceObject position
        String path = pathToFile+spaceObjectName+".txt";
        try {
        	spaceObjectGeo.setLocalTranslation(inputPosition(path));
        }
        catch (NoSuchElementException | FileNotFoundException ex) {
        	throw ex;
        }
        
        float physicsMass = 0f;
        try {
        	physicsMass = inputPhysicsMass(path);
        }
        catch (NoSuchElementException | FileNotFoundException ex) {
        	throw ex;
        }
        //spaceObject physics
        spaceObjectPhysics = makePhysics(physicsMass);
        setPhysics(spaceObjectPhysics);
        
        bulletAppState.getPhysicsSpace().add(spaceObjectPhysics);
        
        //attach to main scene
        rootNode.attachChild(spaceObjectGeo);  
    	isInit = true;
    }
    
    public Material makeMaterial(AssetManager assetManager)
    {
    	return new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
    }
    
    public Geometry makeGeometry(String nameGeometry, Sphere meshGeometry)
    {
    	return new Geometry(nameGeometry, meshGeometry);
    }
    
    public RigidBodyControl makePhysics(float mass)
    {
    	return new RigidBodyControl(mass);
    }
    
    public void setGeometry(Geometry geometry)
    {
        spaceObjectGeo = geometry;
    }
    
    public Geometry getGeometry()
    {
        return spaceObjectGeo;
    }
        
    public void setMaterial(Material material)
    {
        spaceObjectMaterial = material;
    }
    
    public Material getMaterial()
    {
        return spaceObjectMaterial;
    }
    
    public void setPhysics(RigidBodyControl physics)
    {
        spaceObjectPhysics = physics;
        spaceObjectGeo.addControl(spaceObjectPhysics);
    }
    
    public RigidBodyControl getPhysics()
    {
        return spaceObjectPhysics;
    }
    
    public void setMesh(Sphere mesh)
    {
        spaceObjectMesh = mesh;
    }
    public Sphere getMesh()
    {
        return spaceObjectMesh;
    }
    
    public Vector3f getPosition()
    {
        return spaceObjectGeo.getLocalTranslation();
    }
    
    public float getRadius() {
    	return spaceObjectRadius;
    }
}
