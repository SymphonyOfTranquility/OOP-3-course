package lab1;

import com.jme3.math.*;
import java.io.*;
import java.util.Scanner;
import java.lang.Math;

public class Physics{
    
    
    
    public void inputVals(String path)
    {
        Vector3f tempJupiterPos = new Vector3f();
        Vector3f tempVoyagerPos = new Vector3f();
        Vector3f tempDirVector = new Vector3f();
        try{
            File file = new File(path);
            Scanner in = new Scanner(file);
            jupiterPosition.x = (float) in.nextFloat();
            jupiterPosition.y = (float) in.nextFloat();
            jupiterPosition.z = (float) in.nextFloat();
            voyagerPosition.x = (float) in.nextFloat();
            voyagerPosition.y = (float) in.nextFloat();
            voyagerPosition.z = (float) in.nextFloat();
            directVector.x = (float) in.nextFloat(); 
            directVector.y = (float) in.nextFloat(); 
            directVector.z = (float) in.nextFloat();
            in.close();
        }
        catch (FileNotFoundException ex)  
        {
            System.out.println("File doesn't exists");
        }
    }
    
    public Physics(){
        this.jupiterPosition = new Vector3f(0, 0, 0);
        this.voyagerPosition = new Vector3f(jupiterRadius*2f, jupiterRadius*2f, jupiterRadius*2f);
        this.directVector = new Vector3f(0,0,0);
    }
    
    public Vector3f getGravity(Vector3f voyagerPosition)
    {
        voyagerPosition = newVoyagerPosition;
        Vector3f delta = new Vector3f();
        delta.x = jupiterPosition.x - voyagerPosition.x;
        delta.y = jupiterPosition.y - voyagerPosition.y;
        delta.z = jupiterPosition.z - voyagerPosition.z;
    
        float len = (float)Math.sqrt((double) (delta.x*delta.x + delta.y*delta.y + delta.z*delta.z));
        float accel = G*jupiterWeight/(len*len);
        Vector3f gravity = new Vector3f(accel*delta.x/len, accel*delta.y/len, accel*delta.z/len);
        return gravity;
    }
}
