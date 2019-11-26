package lab1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

import com.jme3.app.SimpleApplication;
import com.jme3.math.Vector3f;
import com.jme3.bullet.BulletAppState;

public class MainApp extends SimpleApplication {

    //jupiter object
    public Jupiter jupiter;
    //voyager object
    public Voyager voyager;
    //physics application state
    public BulletAppState bulletAppState;
    //isInit
    public boolean isInit = false;
    @Override
    public void simpleInitApp() {
        // configure cam to look at scene
        flyCam.setMoveSpeed(30f);
        cam.setLocation(new Vector3f(0.0f, 0.0f, 80.0f));
        
        //set up physics game
        bulletAppState = new BulletAppState();
        stateManager.attach(bulletAppState);        
        
        //set up physics/create objects
        jupiter = new Jupiter();
        voyager = new Voyager();
        try {
        	String pathToFile = "src/main/resources/";
	        jupiter.setStartupParametrs(rootNode, bulletAppState, assetManager, pathToFile);
	        voyager.setStartupParametrs(rootNode, bulletAppState, assetManager, pathToFile);
	        voyager.startMoving(pathToFile);
        }
        catch (NoSuchElementException | FileNotFoundException ex) {
        	System.exit(0);
        }        
        
        stateManager.attach(jupiter);
        stateManager.attach(voyager);
        stateManager.attach(bulletAppState);
    	isInit = true;
    }    
    
    
    @Override 
    public void simpleUpdate(float tpf)
    {
        //jupiter rotation
        jupiter.getGeometry().rotate(0.0f, 0.0f, 0.01f);
        //voyager new positioning  
        voyager.getPhysics().setGravity(Physics.getGravity(voyager.getPosition(), jupiter.getPosition(), jupiter.getWeight()));        
    }
}
