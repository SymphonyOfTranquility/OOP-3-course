package lab1;

import com.jme3.math.*;
import java.io.*;
import java.util.Scanner;
import java.lang.Math;

public abstract class SpaceObject{
    
    protected Sphere objectMesh;
    protected Geometry objectGeo;
    protected Material objectMaterial;
    protected RigidBodyControl objectPhysics;
    
    protected final float objectRadius;
    
    public abstract void setStartupParametrs(Node rootNode, BulletAppState bulletAppState, AssetManager assetManager){}
    
    
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
    
    public getMesh()
    {
        return objectMesh;
    }
}
