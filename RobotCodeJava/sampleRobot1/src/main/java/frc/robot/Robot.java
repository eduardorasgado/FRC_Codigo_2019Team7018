/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

/*----------------------------------------------------------------------------*/
/*                        EOLOTICS 7018                                       */
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
// controlar 2 motores izquierdos y dos derecho
import edu.wpi.first.wpilibj.SpeedControllerGroup;
// Libreria para los victor spx
import edu.wpi.first.wpilibj.VictorSP;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

 public class Robot extends TimedRobot {
   // Stop
   private final double STOP_MOTOR = 0.0;
   // Maximo Poder Del Motor
   private final double MAX_POWER_MOTOR = 1.0;
   //Maximo Poder Del Motor Negativo
   private final double MAX_POWER_MOTOR_NEGATIVE = -1.0;
   // el joystick esta en el puerto cero(USB)
  // solo leemos las pocisiones x y "y" del mando
  private final Joystick m_stick = new Joystick(0);
  // controla los tiempos de los subsistemas
  private final Timer m_timer = new Timer();

  // puertos para el spark
  private final int SPARK_PORT_LEFT_REAR = 0;
  private final int SPARK_PORT_LEFT_FRONT = 1;

  private final int SPARK_PORT_RIGHT_REAR = 2;
  private final int SPARK_PORT_RIGHT_FRONT = 6;

  // Puertos para los victor del ball Launcher
  private final int VICTOR_SP1_PORT = 4;
  private final int VICTOR_SP2_PORT = 5;
  
  //Puerto para los voctor del ball Sucker
  private final int MECANNO_MOTOR_PORT = 7;
  private final int ARM_MOTOR_PORT = 8;

  // Creando los motores para el robot diferencial
  private Spark motorRearRight;  // (LF)(LR)----(*RR)(RF)
  private Spark motorFrontRight; // (LF)(LR)----(RR)(*RF)

  private Spark motorRearLeft; // (LF)(*LR)----(RR)(RF)
  private Spark motorFrontLeft; // (*LF)(LR)----(RR)(RF)

  // creando el sistema diferencial para el robot
  private DifferentialDrive mecanismoPrincipal;

  // los dos motores del Ball Launcher
  private VictorSP MLauncher1;
  private VictorSP MLauncher2;
  
  // los dos motores del Ball Sucker
  private VictorSP MecSuckerMotor;
  private VictorSP ArmSuckerMotor;

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    // definiendo los controladores de los motores derechos
    motorRearRight = new Spark(SPARK_PORT_RIGHT_REAR);
    motorFrontRight = new Spark(SPARK_PORT_RIGHT_FRONT);

    // definiendo los controladores de los motores izquierdos
    motorRearLeft = new Spark(SPARK_PORT_LEFT_REAR);
    motorFrontLeft = new Spark(SPARK_PORT_LEFT_FRONT);

    // agrupando los motores
    SpeedControllerGroup m_right = new SpeedControllerGroup(motorFrontRight, motorRearRight);
    SpeedControllerGroup m_left = new SpeedControllerGroup(motorFrontLeft, motorRearLeft);
    // controlador principal para el movimiento del robot
    mecanismoPrincipal = new DifferentialDrive(m_left, m_right);

    // Inicializando subsistemas
    // 1) Launcher
    MLauncher1 = new VictorSP(VICTOR_SP1_PORT);
    MLauncher2 = new VictorSP(VICTOR_SP2_PORT);

    // 2) Sucker
    MecSuckerMotor = new VictorSP(MECANNO_MOTOR_PORT);
    ArmSuckerMotor = new VictorSP(ARM_MOTOR_PORT);
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
      mecanismoPrincipal.arcadeDrive(-m_stick.getY(), m_stick.getX());

      //boton 1: launcher: activacion
      if(m_stick.getRawButtonPressed(1))
      {
        // si el boton 0 es presionado hacer esto
        // el motor es encendido a maxima potencia
        SmartDashboard.putBoolean("Button Launcher Pressed", true);
        SmartDashboard.putBoolean("Button Launcher Released", false);
        // encendiendo los motores
        MLauncher1.set(MAX_POWER_MOTOR);
        MLauncher2.set(MAX_POWER_MOTOR_NEGATIVE);
      }
      //Boton 1: launcher, release
      if(m_stick.getRawButtonReleased(1))
      {
        // si el boton 0 es librerado hacer esto otro
        // el motor para lentamente
        SmartDashboard.putBoolean("Button Launcher Released", true);
        SmartDashboard.putBoolean("Button Launcher Pressed", false);
        // desactivamos el launcher
        MLauncher1.set(STOP_MOTOR);
        MLauncher2.set(STOP_MOTOR);
      }
      // Boton2: ball Sucker-> encendiendo
      if(m_stick.getRawButtonPressed(2))
      {
        SmartDashboard.putBoolean("Button Sucker Pressed", true);
        SmartDashboard.putBoolean("Button Sucker Release", false);
        // enciendo el subsistema para sustraer el cargo
        MecSuckerMotor.set(MAX_POWER_MOTOR);
        ArmSuckerMotor.set(0.25);
      }
      if(m_stick.getRawButtonReleased(2))
      {
        SmartDashboard.putBoolean("Button Sucker Release", true);
        SmartDashboard.putBoolean("Button Sucker Pressed", false);
        // Apagando el subsistema para sustraer el cargo
        MecSuckerMotor.set(STOP_MOTOR);
        ArmSuckerMotor.set(STOP_MOTOR);
      }
    }
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
