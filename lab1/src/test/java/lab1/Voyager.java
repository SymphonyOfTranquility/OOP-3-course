package lab1;

import com.jme3.math.*;
import java.io.*;
import java.util.Scanner;
import java.lang.Math;

public class Voyager extends SpaceObject{
    
    public Voyager()
    {
        objectRadius = 1f;
        object = new Sphere(128, 128, objectRadius);
        object.setTextureMode(Sphere.TextureMode.Projected);
    }
    
    
}
