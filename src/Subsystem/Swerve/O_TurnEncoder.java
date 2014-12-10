package Subsystem.Swerve;

import Robot.RobotMap;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSource;

class O_TurnEncoder implements PIDSource{
    
    Encoder encoder;
    DigitalInput zeroSensor;
    
    double offset; // how far the module has to be adjusted to make the dash zero
    double zeroOffset; //how far the groove is away from "true zero" (wheels facing north)
    
    public O_TurnEncoder(int APort, int BPort, int zeroPort, double zeroOffset, boolean reverseEncoder){       
        zeroSensor = new DigitalInput(RobotMap.turnModule, zeroPort);
        offset = 0;
        this.zeroOffset = zeroOffset;
        encoder = new Encoder(RobotMap.turnModule, APort, RobotMap.turnModule, BPort, reverseEncoder, CounterBase.EncodingType.k4X) {{
            setDistancePerPulse(360.0 / 500.0);
            start();
        }};
    }
    
    /**
     * 
     * @return Wheel angle
     */
    public double pidGet() {
        double angle = (encoder.getDistance() - offset + zeroOffset) % 360.0;
        angle += (angle < 0) ? 360: 0; //Add 360 if less than 0
        return (angle - 180);
    }
    
    public void zero() {
        offset = encoder.getDistance();
    }
}
