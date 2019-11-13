package lab1;

import com.jme3.math.*;
import java.io.*;
import java.util.Scanner;
import java.lang.Math;

import org.junit.Test;
import static org.junit.Assert.*;

public class PhysicsTest {
    @Test public void testAppOnDefaultParametrs() {
        Physics physics = new Physics();
        float jupiterRadius = physics.getJupiterRadius();
        Vector3f startPosOfVoyager = new Vector3f(jupiterRadius*2f, jupiterRadius*2f, jupiterRadius*2f);
        assertEquals("app should have same voyagerPosition", physics.getVoyagerPosition(), startPosOfVoyager);
        
        Vector3f startPosOfJupiter = new Vector3f(0, 0, 0);
        assertEquals("app should have same voyagerPosition", physics.getJupiterPosition(), startPosOfJupiter);
        
        Vector3f startDirectVector = new Vector3f(0, 0, 0);
        assertEquals("app should have same voyagerPosition", physics.getDirectVector(), startDirectVector);
    }
    
    @Test public void testAppOnInputParametrs() {
        Physics physics = new Physics();
        String path = "src/test/resources/input.txt";
        physics.inputVals(path);
        Vector3f jupiterPosition = new Vector3f(0,0,0);
        Vector3f voyagerPosition = new Vector3f(0,0,0);
        Vector3f directVector = new Vector3f(0,0,0);
        File file = new File(path);
        try{
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
            
            assertEquals("app should have same voyagerPosition", physics.getJupiterPosition(), jupiterPosition);
            
            assertEquals("app should have same voyagerPosition", physics.getVoyagerPosition(), voyagerPosition);        
        
            assertEquals("app should have same voyagerPosition", physics.getDirectVector(), directVector);
            in.close();
        }
        catch (FileNotFoundException ex)  
        {
        
        }
    }
    
    @Test public void testAppOnGetGravity() {
        Physics physics = new Physics();
        Vector3f myGravity = new Vector3f(-1500f/(4f*4.4f*4.4f), 0f, 0f);
        Vector3f voyagerPosition = new Vector3f(physics.getJupiterRadius()*2f, 0f, 0f);
        assertEquals("should have same gravity vector", physics.getGravity(voyagerPosition), myGravity);
    }
     
}
