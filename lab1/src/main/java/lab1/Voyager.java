package lab1;

import com.jme3.asset.AssetManager;
import com.jme3.math.Vector3f;
import com.jme3.scene.shape.Sphere;
import com.jme3.bullet.BulletAppState;
import com.jme3.scene.Node;
import java.io.*;
import java.util.Scanner;

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
            in.close();
            return velocityVector;
        }
        catch (Exception ex){
            return new Vector3f(0,0,0);
        }
    }
        
    public void startMoving()
    {
        String path = "src/main/resources/"+objectName+".txt";
        objectPhysics.setLinearVelocity(inputVelocityVector(path));        
    }
    
}
