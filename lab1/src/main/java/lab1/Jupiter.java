package lab1;

import com.jme3.asset.AssetManager;
import com.jme3.app.SimpleApplication;
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
import java.io.*;
import java.util.Scanner;
import java.lang.Math;

public class Jupiter extends SpaceObject{
       
    private final static float jupiterWeight = 30000f;
    
    public Jupiter()
    {
        objectName = "Jupiter";
        objectRadius = 4.4f;
        objectMesh = new Sphere(128, 128, objectRadius);
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
        Quaternion roll = new Quaternion();
        roll.fromAngleAxis( FastMath.PI , new Vector3f(-1,0.5f,1) );
        objectGeo.setLocalRotation(roll);
    }
    
}
