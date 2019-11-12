package lab1;

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

public class MainApp extends SimpleApplication {

    //jupiter object
    private Jupiter jupiter;
    //voyager object
    private Voyager voyager;
    //physics application state
    private BulletAppState bulletAppState;

    @Override
    public void simpleInitApp() {
        // configure cam to look at scene
        flyCam.setMoveSpeed(15f);
        cam.setLocation(new Vector3f(0.0f, 0.0f, 80.0f));
        
        //set up physics game
        bulletAppState = new BulletAppState();
        stateManager.attach(bulletAppState);        
        //bulletAppState.getPhysicsSpace().setGravity(new Vector3f(0,0,0));
        
        //set up physics/create objects
        jupiter = new Jupiter();
        voyager = new Voyager();
        jupiter.setStartupParametrs(rootNode, bulletAppState, assetManager);
        voyager.setStartupParametrs(rootNode, bulletAppState, assetManager);
    }    
    
    
    @Override 
    public void simpleUpdate(float tpf)
    {
        //jupiter rotation
        jupiter.getGeometry().rotate(0.0f, 0.0f, 0.01f);
        //voyager new positioning  
        voyager.getPhysics().setGravity(Physics.getGravity(voyager, jupiter));        
    }
}
