/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class OI {
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

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

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());

  // el joystick esta en el puerto cero(USB)
  // solo leemos las pocisiones x y "y" del mando
  private final int ACTUAL_USB_PORT = 0;
  public Joystick m_stick;
  private int LAUNCHER_NUMBER = 2;
  private int SUCKER_NUMBER = 1;

  // constructor de la clase
  public OI()
  {
    // inicializador
    m_stick = new Joystick(ACTUAL_USB_PORT);
  }

  public void sendPositionToDBoard()
  {
    SmartDashboard.putNumber("Joystick X", m_stick.getX());
    SmartDashboard.putNumber("Joytick Y", m_stick.getY());
  }
  
  public double getSliderData()
  {
    // conseguir datos del slider
    return m_stick.getRawAxis(3);
  }

  public boolean LauncherButtonPressed()
  {
    // si el boton 0 es presionado hacer esto
    // el motor es encendido a maxima potencia
    SmartDashboard.putBoolean("Button Launcher Pressed", true);
    SmartDashboard.putBoolean("Button Launcher Released", false);
    return m_stick.getRawButtonPressed(LAUNCHER_NUMBER);
  }

  public boolean LauncherButtonRelease()
  {
    //
    // si el boton 0 es librerado hacer esto otro
    // el motor para lentamente
    SmartDashboard.putBoolean("Button Launcher Released", true);
    SmartDashboard.putBoolean("Button Launcher Pressed", false);
    return m_stick.getRawButtonReleased(LAUNCHER_NUMBER);
  }

  public boolean SuckerButtonPressed()
  {
    SmartDashboard.putBoolean("Button Sucker Pressed", true);
    SmartDashboard.putBoolean("Button Sucker Release", false);
    return m_stick.getRawButtonPressed(SUCKER_NUMBER);
  }

  public boolean SuckerButtonRelease()
  {
    SmartDashboard.putBoolean("Button Sucker Release", true);
    SmartDashboard.putBoolean("Button Sucker Pressed", false);
    return m_stick.getRawButtonReleased(SUCKER_NUMBER);
  }

  public double getZAxis()
  {
    // conseguir datos del slider
    return m_stick.getRawAxis(2);
  }
}
