import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class rocketry {
    public static double Force(double mass) {
        double accel=9.8;
        double force = (mass)*(accel);
        return force;
    }

    public static double updateVelocity(double thrust, double radius, double mass, double fuelMass, double impulse){
        double altitude=0.0;
        double time=0.0;
        double timeChange=0.01;
        double burnTime=impulse/thrustAvg;
        double massPerTime=mass-(mass-(fuelMass))*(time/(burnTime));
        double vOld=0.0;
        double vNew=0.0;
        double drag=0;
        double accel;

        if (time<=burnTime){
            thrust=0;
        }
        while (vNew>=0.0){
            //find current veloicty at each time and add to graph
            drag=(0.5*1.225*0.8*Math.pow(radius,2)*Math.PI*vOld);
            accel=((thrust-drag-(massPerTime*9.8)))/massPerTime;
            vNew=vOld+(accel)*(timeChange);


  

        }

        return velocity;


    }

    public static double Apogee(double impulse, double thrustAvg, double diameter, double mass, double propMass, double currentTime){
        double airDensity=1.225;
        double frontalArea=(Math.PI*diameter)/4;
        double massPerTime=mass+propMass*(1-(currentTime/burnTime));
        double Drag=(1/2)*airDensity*0.4*frontalArea*Math.pow(velocity,2);
        return apogee;



    }


}
