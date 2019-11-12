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

public class Voyager extends SpaceObject{
    
    public Voyager()
    {
        objectName = "Voyager";
        objectRadius = 1f;
        objectMesh = new Sphere(16, 16, objectRadius);
    }
    
    public Vector3f inputVelocityVector(String path)
    {
         try{
            File file = new File(path);
            Scanner in = new Scanner(file);
            Vector3f velocityVector = new Vector3f(0,0,0);
            for (int i = 0;i < 5;++i)
                velocityVector.x = (float) in.nextFloat();
            velocityVector.y = (float) in.nextFloat();
            velocityVector.z = (float) in.nextFloat();
            return velocityVector;
        }
        catch (Exception ex){
            return new Vector3f(0,0,0);
        }
    }
    
    @Override
    public void setStartupParametrs(Node rootNode, BulletAppState bulletAppState, AssetManager assetManager)
    {
        super.setStartupParametrs(rootNode, bulletAppState, assetManager);
        String path = "src/main/resources/"+objectName+".txt";
        objectPhysics.setLinearVelocity(inputVelocityVector(path));        
    }
    
}
