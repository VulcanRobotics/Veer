
package Robot;

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
    public static Button leftThumbClick;
    public static Button Button_L1;
    public static Button Button_R1;
    public static Button Button_A;
    
    public OI() {
        joystick1 = new Joystick(RobotMap.J1);
        joystick2 = new Joystick(RobotMap.J2);
        
        leftThumbClick = new JoystickButton(joystick1, 9);
        Button_L1 = new JoystickButton(joystick1, 5);
        Button_R1 = new JoystickButton(joystick1, 6);
        Button_A = new JoystickButton(joystick1, 1);
        Button_A.whenPressed(new Subsystem.Swerve.C_ZeroModules());
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

