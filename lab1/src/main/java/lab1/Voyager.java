package lab1;

import java.util.NoSuchElementException;
import com.jme3.asset.AssetManager;
import com.jme3.math.Vector3f;
import com.jme3.scene.shape.Sphere;
import com.jme3.bullet.BulletAppState;
import com.jme3.scene.Node;
import java.io.*;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Voyager extends SpaceObject{
    
    public Voyager(){
        objectName = "Voyager";
        objectRadius = 1f;
        objectMesh = new Sphere(16, 16, objectRadius);
    }
    
    public Vector3f inputVelocityVector(String path) throws NoSuchElementException, FileNotFoundException {
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
        catch (NoSuchElementException | FileNotFoundException ex) {
        	throw ex;
        }
    }
        
    public void startMoving(String pathToFile) throws NoSuchElementException, FileNotFoundException {
        String path = pathToFile+objectName+".txt";
        try {
        	objectPhysics.setLinearVelocity(inputVelocityVector(path));
        }
        catch (NoSuchElementException | FileNotFoundException ex)
        {
        	throw ex;
        }
    }
    
}
