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

public class MainApp extends SimpleApplication {

    //jupiter object
    private Jupiter jupiter;
    //physics of moving
    private Physics physics;
    //voyager object
    private Voyager voyager;
    //physics application state
    private BulletAppState bulletAppState;
    
    private void jupiterCreation()
    {
        //jupiter mesh
        Sphere sphereMesh = new Sphere(256, 256, physics.getJupiterRadius());
        sphereMesh.setTextureMode(Sphere.TextureMode.Projected);
        
        //texture and material of jupiter
        Texture sphereTexture = assetManager.loadTexture("img/Jupiter.jpg");
        sphereTexture.setWrap(Texture.WrapMode.Repeat);
        Material sphereMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        sphereMat.setTexture("ColorMap", sphereTexture);
        
        //jupiter geometry
        jupiterGeo = new Geometry("Jupiter", sphereMesh);
        jupiterGeo.setMaterial(sphereMat);
        
        //jupiter rotate
        Quaternion roll = new Quaternion();
        roll.fromAngleAxis( FastMath.PI , new Vector3f(-1,0.5f,1) );
        jupiterGeo.setLocalRotation(roll);
        
        //jupiter position
        jupiterGeo.setLocalTranslation(physics.getJupiterPosition());
         
        //jupiter physics
        jupiterGeo.addControl(new RigidBodyControl(0.0f));
        jupiterGeo.getControl(RigidBodyControl.class).setAngularVelocity(new Vector3f(1,-0.5f,-1));
        bulletAppState.getPhysicsSpace().add(jupiterGeo.getControl(RigidBodyControl.class));
        
        //attach to main scene
        rootNode.attachChild(jupiterGeo);
    }
    
    
    private void voyagerCreation()
    {
        //voyager mesh
        Box voyagerMesh = new Box(0.9f, 0.5f, 0.3f);    //size
        //voyager material
        Texture voyagerTexture = assetManager.loadTexture("img/Voyager.png");
        voyagerTexture.setWrap(Texture.WrapMode.Repeat);
        Material voyagerMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        voyagerMat.setTexture("ColorMap",voyagerTexture);
        //voyager geometry
        voyagerGeo = new Geometry("Voyager", voyagerMesh);
        voyagerGeo.setMaterial(voyagerMat);   
        //voyager position
        voyagerGeo.setLocalTranslation(physics.getVoyagerPosition());
        //voyager physics
        RigidBodyControl voyagerPhy = new RigidBodyControl(1.0f);
        voyagerGeo.addControl(voyagerPhy);             
        bulletAppState.getPhysicsSpace().add(voyagerPhy);
        voyagerPhy.setLinearVelocity(physics.getDirectVector());        
        
        
        //attach to main scene
        rootNode.attachChild(voyagerGeo);
    }

    
    
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
        physics = new Physics();
        physics.inputVals("src/main/resources/input.txt");
        jupiterCreation();
        voyagerCreation();
        
    }    
    
    
    @Override 
    public void simpleUpdate(float tpf)
    {
        //jupiter rotation
        jupiterGeo.rotate(0.0f, 0.0f, 0.01f);
        //voyager new positioning  
        voyagerGeo.getControl(RigidBodyControl.class).setGravity(physics.getGravity(voyagerGeo.getLocalTranslation()));
        
    }
}
