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

// Importando los comandos
// para el sensor sharp
import frc.robot.commands.ProximitySensorCommand;

// importando todos los subsistemas
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.DifferentialDriveTrain;
import frc.robot.subsystems.BallSucker;
import frc.robot.subsystems.BallLauncher;
//import frc.robot.OI;
// permite controlar los tiempos de ejecucion
//import edu.wpi.first.wpilibj.Timer;
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
  private OI m_oi;

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();
  
  // Maximo Poder Del Motor
  private final double MAX_POWER_MOTOR = 1.0;
  private final double MIN_SUCKER_MOTOR = 0.25;
  //Maximo Poder Del Motor Negativo
  //private final double MAX_POWER_MOTOR_NEGATIVE = -1.0;
  
  // controla los tiempos de los subsistemas
  //private final Timer m_timer = new Timer();

  // sistema principal
  private DifferentialDriveTrain mainDrive;

  // El sistema para la sustraccion del cargo
  private BallSucker suckerSystem;
  // El sistema eyector de cargo
  private BallLauncher launcherSystem;

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_oi = new OI();

    // configurando el comando para el modo autonomo principal
    //m_autonomousCommand = new ProximitySensorCommand();

    // opcion 1: 
    m_chooser.setDefaultOption("Leer el sensor sharp!", new ProximitySensorCommand());
    // chooser.addOption("My Auto", new MyAutoCommand());
    SmartDashboard.putData("Selecciona el autonomo :U", m_chooser);

    // Iniciando subsistemas
    // el sistema de motores principal
    mainDrive = new DifferentialDriveTrain();
    // definimos el sistema sustractor
    suckerSystem = new BallSucker();
    //
    launcherSystem = new BallLauncher();
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
      // enviar valores x (y) y a la smart dashboard
      m_oi.sendPositionToDBoard();
      // los datos de el slider
      //SmartDashboard.putNumber("SLIDER: ", m_oi.getSliderData());
      
      // obteniendo la velocidad del launcher con el slider
      double sliderData = launcherSystem.getSpeed(m_oi.getSliderData());

      // desde el subsistema de drive
      mainDrive.Drive(m_oi.m_stick);

      //boton 1: launcher: activacion
      if(m_oi.LauncherButtonPressed())
      {
        // encendiendo los motores
        launcherSystem.DrivePressed(sliderData, -sliderData);
      }
      //Boton 1: launcher, release
      if(m_oi.LauncherButtonRelease())
      {
        // desactivamos el launcher
        launcherSystem.DriveRelease();
      }
      // Boton2: ball Sucker-> encendiendo
      if(m_oi.SuckerButtonPressed())
      {
        // enciendo el subsistema para sustraer el cargo
        // Ball Sucker
        suckerSystem.DrivePressed(MAX_POWER_MOTOR, MIN_SUCKER_MOTOR);
      }
      if(m_oi.SuckerButtonRelease())
      {
        // Apagando el subsistema para sustraer el cargo
        suckerSystem.DriveRelease();
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
