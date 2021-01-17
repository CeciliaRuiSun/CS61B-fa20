
public class TestBody {
    /* create two bodies to test Body class*/

    public static void main(String[] args){
    double xxPos = 1.0,
        yyPos = 2.0,
        xxVel = 3.0,
        yyVel = 4.0,
        mass = 5.0;
    String imgFileName = "jupiter.gif";

        Body test1 = new Body (xxPos,yyPos,xxVel,yyVel,mass,imgFileName);
        Body test2 = new Body (xxPos + 2,yyPos + 2,xxVel + 2,yyVel + 2,mass + 2,imgFileName);

        double force = test1.calcForceExertedBy(test2);


        System.out.println(force);
    }
}
