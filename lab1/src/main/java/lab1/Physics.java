package lab1;

import com.jme3.math.*;
import java.lang.Math;

public class Physics{
        
    private final static float G = 0.05f;   
    
    public static Vector3f getGravity(Voyager voyager, Jupiter jupiter)
    {
        Vector3f voyagerPosition = voyager.getPosition();
        Vector3f jupiterPosition = jupiter.getPosition();
        Vector3f delta = new Vector3f();
        delta.x = jupiterPosition.x - voyagerPosition.x;
        delta.y = jupiterPosition.y - voyagerPosition.y;
        delta.z = jupiterPosition.z - voyagerPosition.z;
    
        float len = (float)Math.sqrt((double) (delta.x*delta.x + delta.y*delta.y + delta.z*delta.z));
        float accel = G*jupiter.getWeight()/(len*len);
        return new Vector3f(accel*delta.x/len, accel*delta.y/len, accel*delta.z/len);
    }
}
