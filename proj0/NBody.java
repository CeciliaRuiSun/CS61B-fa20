import javax.swing.text.html.parser.DTD;

public class NBody {
    /* readIn text file 
    5  -- num of plantes
    2.50e+11    -- radius of the universe
    1.4960e+11  0.0000e+00  0.0000e+00  2.9800e+04  5.9740e+24    earth.gif */

    public static void main(String[] args) {



        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        double universeRadius = readRadius(filename);
        Body[] bodies = readBodies(filename);
        
        StdDraw.setScale(-universeRadius, universeRadius);
        String background = "images/starfield.jpg";
        StdDraw.picture(0, 0, background);
        StdDraw.show();
        StdDraw.pause(2000);
        
        for(Body element:bodies){
            element.draw();
        }

        StdDraw.enableDoubleBuffering();

        double time = 0;
        int num = bodies.length;

        while(time < T){

            Double[] xForces = new Double[num];
            Double[] yForces = new Double[num];

            for(int i = 0;i < num;i ++){
                xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
            }

            for(int i = 0;i < num;i ++){
                bodies[i].update(dt,xForces[i],yForces[i]);

            }

            StdDraw.picture(0, 0, background);
            
            for(Body element:bodies){
                element.draw();
            }
            StdDraw.show();

            StdDraw.pause(10);

            time += dt;
        }


    }

    public static double readRadius(String s){
        //return radius of universe 
        In in = new In(s);
		int firstItemInFile = in.readInt();
        double secondItemInFile = in.readDouble();
        
        return secondItemInFile;
    }

    public static Body[] readBodies(String s){
        // read in text file and return an array of bodies

        In in = new In(s);
        int numofbodies = in.readInt();
        double secondItemInFile = in.readDouble();

        double xxPos = 1.0,
            yyPos = 2.0,
            xxVel = 3.0,
            yyVel = 4.0,
            mass = 5.0;
        String imgFileName = "jupiter.gif";
        Body[] bodies = new Body[numofbodies];

        for(int i = 0;i < numofbodies;i ++){
            bodies[i] = new Body (xxPos,yyPos,xxVel,yyVel,mass,imgFileName);
        }

        //read numbers starting from 3rd item
        for(int i = 0;i < numofbodies;i ++){
            bodies[i].xxPos = in.readDouble();
            bodies[i].yyPos = in.readDouble();
            bodies[i].xxVel = in.readDouble();
            bodies[i].yyVel = in.readDouble();
            bodies[i].mass = in.readDouble();
            bodies[i].imgFileName = "images/" + in.readString();
 
        }
        return bodies;
    }


}
