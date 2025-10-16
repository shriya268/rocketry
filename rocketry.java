public class rocketry {
    public static double Force(double mass) {
        double accel=9.8;
        double force = (mass)*(accel);
        return force;
    }

    public static double Velocity(double time){
        
    }

    public static double Apogee(double impulse, double thrustAvg, double diameter, double mass, double propMass, double currentTime){
        double airDensity=1.225;
        double burnTime=impulse/thrustAvg;
        double frontalArea=(Math.PI*diameter)/4;
        double massPerTime=mass+propMass*(1-(currentTime/burnTime));
        double Drag=(1/2)*airDensity*0.4*frontalArea*Math.pow(velocity,2);
        return apogee;



    }


}
