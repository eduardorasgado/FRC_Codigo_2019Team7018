/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
//import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// importando todos los subsistemas
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.DifferentialDriveTrain;

// Libreria para los victor spx
import edu.wpi.first.wpilibj.VictorSP;

import edu.wpi.first.wpilibj.Joystick;
// permite controlar los tiempos de ejecucion
import edu.wpi.first.wpilibj.Timer;
// pasamos datos a la smartDashbard
import edu.wpi.first.wpilibj.smartdashboard.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static ExampleSubsystem m_subsystem = new ExampleSubsystem();
  public static OI m_oi;

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

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
  //private final Timer m_timer = new Timer();

  // Puertos para los victor del ball Launcher
  private final int VICTOR_SP1_PORT = 4;
  private final int VICTOR_SP2_PORT = 5;
  
  //Puerto para los voctor del ball Sucker
  private final int MECANNO_MOTOR_PORT = 7;
  private final int ARM_MOTOR_PORT = 8;

  // sistema principal
  private DifferentialDriveTrain mainDrive;

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
    m_oi = new OI();
    m_chooser.setDefaultOption("Default Auto", new ExampleCommand());
    // chooser.addOption("My Auto", new MyAutoCommand());
    SmartDashboard.putData("Auto mode", m_chooser);

    // el sistema de motores principal
    mainDrive = new DifferentialDriveTrain();

    // Inicializando subsistemas
    // 1) Launcher
    MLauncher1 = new VictorSP(VICTOR_SP1_PORT);
    MLauncher2 = new VictorSP(VICTOR_SP2_PORT);

    // 2) Sucker
    MecSuckerMotor = new VictorSP(MECANNO_MOTOR_PORT);
    ArmSuckerMotor = new VictorSP(ARM_MOTOR_PORT);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();

    while(isEnabled() && isOperatorControl())
    {
      SmartDashboard.putNumber("Joystick X", m_stick.getX());
      SmartDashboard.putNumber("Joytick Y", m_stick.getY());
      
      // desde el subsistema de drive
      mainDrive.Drive(m_stick);

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