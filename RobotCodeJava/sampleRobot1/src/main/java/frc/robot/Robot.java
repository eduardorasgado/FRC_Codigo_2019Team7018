/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
// permite controlar los tiempos de ejecucion
import edu.wpi.first.wpilibj.Timer;
// pasamos datos a la smartDashbard
import edu.wpi.first.wpilibj.smartdashboard.*;
// Se importa dentro del objeto differencialDrive, dos de ellos
import edu.wpi.first.wpilibj.Spark;
// permite crear un sistema diferencial para controlar los dos motores
// que  le dan la direccion al robot
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

//smuuuuuurrfffff

 public class Robot extends TimedRobot {
  // puertos para el spark
  private final int PUERTO_SPARK_LEFT = 0;
  private final int PUERTO_SPARK_RIGHT = 1;

  private final Joystick m_stick = new Joystick(0);
  
  private final Timer m_timer = new Timer();
  
  // Creando los motores para el robot diferencial
  private Spark motorRight;
  private Spark motorLeft;

  // creando el sistema diferencial para el robot
  private DifferentialDrive mecanismoPrincipal;
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    motorRight = new Spark(PUERTO_SPARK_RIGHT);
    motorLeft = new Spark(PUERTO_SPARK_LEFT);

    mecanismoPrincipal = new DifferentialDrive( motorLeft, motorRight);
  }

  /**
   * This function is run once each time the robot enters autonomous mode.
   */
  @Override
  public void autonomousInit() {
    m_timer.reset();
    m_timer.start();
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    
  }

  /**
   * This function is called once each time the robot enters teleoperated mode.
   */
  @Override
  public void teleopInit() {
  }

  /**
   * This function is called periodically during teleoperated mode.
   */
  @Override
  public void teleopPeriodic() {
    while(isEnabled() && isOperatorControl())
    {
      SmartDashboard.putNumber("Joystick X", m_stick.getX());
      SmartDashboard.putNumber("Joytick Y", m_stick.getY());
      // moviendo todo el tren de manejo
      mecanismoPrincipal.arcadeDrive(m_stick.getY(), m_stick.getX());   
    }
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
