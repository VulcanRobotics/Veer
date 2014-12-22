package Subsystem.Swerve;

import MathObject.O_Point;
import Robot.CommandBase;
import MathObject.O_Vector;
import Robot.OI;
import com.sun.squawk.util.MathUtils;
/**
 *
 * @author 1218
 */
public class C_Swerve extends CommandBase {

    public C_Swerve() {
        requires(swerve);
    }

    protected void initialize() {
        
    }

    protected void execute() {
        double trigger = OI.joystick1.getRawAxis(3);
        double rightX = OI.joystick1.getRawAxis(4);
        
        if(!OI.leftThumb.get()) {
            boolean shouldRunTwist = true;
            
            if(shouldRunTwist) {
                //System.out.println("swerve twisting");
                //swerve.swerve(new O_Vector(-OI.joystick1.getY(), OI.joystick1.getX()), new O_Point(0.0,0.0), -1 * rightX);
                int heading = (int) Math.toDegrees(MathUtils.atan2(OI.joystick1.getX(), -OI.joystick1.getY()));
                double power = Math.sqrt(OI.joystick1.getY() * OI.joystick1.getY() + OI.joystick1.getX()* OI.joystick1.getX());
                System.out.println("heading: "+ heading);
                swerve.swerve(heading, power, new O_Point(0.0,0.0), -rightX);
                
            }
        } else {
            //snake mode
            System.out.println("snake mode");
            swerve.swerve(new O_Vector(0.0, 0.0), new O_Point(0.0, 1.0/(rightX + 0.02 )), OI.joystick1.getY() * ((rightX >= 0) ? 1.0 : -1.0));
        }
        swerve.syncDashboard();
    }
    
    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
