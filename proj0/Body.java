public class Body {
    double xxPos;
    double yyPos;
    double xxVel;
    double yyVel;
    double mass;
    String imgFileName;
    double G = 6.67e-11;

    public Body(double xP, double yP, double xV,
              double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body(Body b){
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    public double calcDistance(Body b){
        double disX = b.xxPos - this.xxPos;
        double disY = b.yyPos - this.yyPos;

        return Math.sqrt(disX * disX + disY * disY);
    }

    public double calcForceExertedBy(Body b){
        if(this.equals(b)){
            return 0;
        }

        double dis = this.calcDistance(b);
        double force = G * this.mass * b.mass / (dis * dis);

        return force;
    }

    public double calcForceExertedByX(Body b){
        if(this.equals(b)){
            return 0;
        }
        double disX = b.xxPos - this.xxPos;
        double force = this.calcForceExertedBy(b);
        double dis = this.calcDistance(b);
        double forceX = force * disX / dis;

        return forceX;
    }

    public double calcForceExertedByY(Body b){
        if(this.equals(b)){
            return 0;
        }
        double disY = b.yyPos - this.yyPos;
        double force = this.calcForceExertedBy(b);
        double dis = this.calcDistance(b);
        double forceY = force * disY / dis;

        return forceY;
    }

    public double calcNetForceExertedByX(Body[] a){
        double sumX = 0;

        for(Body element:a){
            sumX += this.calcForceExertedByX(element);
        }

        return sumX;
    }

    public double calcNetForceExertedByY(Body[] a){
        double sumY = 0;

        for(Body element:a){
            sumY += this.calcForceExertedByY(element);
        }

        return sumY;
    }

    public void update(double dt, double fX, double fY){
        /* update position */

        //acceleration
        double aX = fX / this.mass;
        double aY = fY / this.mass;

        //new velocity
        this.xxVel = this.xxVel + dt * aX;
        this.yyVel = this.yyVel + dt * aY;

        //new position
        this.xxPos = this.xxPos + dt * xxVel;
        this.yyPos = this.yyPos + dt * yyVel;

    }

    public void draw(){

        StdDraw.picture(xxPos, yyPos, imgFileName);

    }


}
