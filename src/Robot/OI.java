
package Robot;

import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    public static Joystick joystick1;
    public static Joystick joystick2;
    
    public static Button leftThumb;
    public static Button rightThumb;
    public static Button Button_L1;
    public static Button Button_R1;
    public static Button Button_A;
    public static Button Button_B;
    public static Button Button_X;
    public static Button Button_Y;
    public static leftBumper Button_L2;
    public static rightBumper Button_R2;
    
    public OI() {
        joystick1 = new Joystick(RobotMap.J1);
        joystick2 = new Joystick(RobotMap.J2);
        
        leftThumb = new JoystickButton(joystick1, 9);
        rightThumb = new JoystickButton(joystick1, 10);
        Button_L1 = new JoystickButton(joystick1, 5);
        Button_R1 = new JoystickButton(joystick1, 6);
        Button_L2 = new leftBumper(joystick1, 3);
        Button_R2 = new rightBumper(joystick1, 3);
        
        Button_A = new JoystickButton(joystick1, 1);
        Button_B = new JoystickButton(joystick1, 2);
        Button_X = new JoystickButton(joystick1, 3);
        Button_Y = new JoystickButton(joystick1, 4);
        
        Button_L1.whileHeld(new Subsystem.Swerve.C_Pivot());
        Button_L2.whileHeld(new Subsystem.Swerve.C_Pivot());
        Button_R1.whileHeld(new Subsystem.Swerve.C_Pivot());
        Button_R2.whileHeld(new Subsystem.Swerve.C_Pivot());
       
        leftThumb.whileHeld(new Subsystem.Swerve.C_Snake());
        
        rightThumb.whileHeld(new Subsystem.Swerve.C_GoToHeading());
        
        Button_A.whenPressed(new Subsystem.Swerve.C_ZeroModules());
        Button_B.whenPressed(new Subsystem.Swerve.C_ResetGyro());
    }
    
    public double leftX() {
        return joystick1.getRawAxis(1);
    }
    
    public double leftY() {
        return -joystick1.getRawAxis(1);
    }
    
    public int leftAngle() {
        return (int)Math.toDegrees( MathUtils.atan2(leftY(), leftX()));
    }
    
    public double leftMagnitude() {
        return Math.sqrt(leftX() * leftX() + leftY() * leftY());
    }
    
    public double rightX() {
        return joystick1.getRawAxis(4);
    }
    
    public double rightY() {
        return -joystick1.getRawAxis(5);
    }
    
    public int rightAngle() {
        return (int)Math.toDegrees( MathUtils.atan2(rightY(), rightX()));
    }
    
    public double rightMagnitude() {
        return Math.sqrt(rightX() * rightX() + rightY() * rightY());
    }
    
    // Another type of button you can create is a DigitalIOButton, which is
    // a button or switch hooked up to the cypress module. These are useful if
    // you want to build a customized operator interface.
    // Button button = new DigitalIOButton(1);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
}

