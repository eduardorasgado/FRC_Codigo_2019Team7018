/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj.AnalogInput;

public class ProximitySensorCommand extends Command {
  AnalogInput sharp_input;

  public ProximitySensorCommand() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    // aqui se incluyen los subsistemas de los que depende
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    // aqui se inicializan las variables del sensor
    // se ejecuta al inicio del programa
    sharp_input = new AnalogInput(3);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    int averageVolts = sharp_input.getAverageValue();
    
    // esto corre durante todo el modo autonomo
    // de 5 a 0 V // el sensor mide de 0 a 80cm
    SmartDashboard.putNumber("SENSOR SHARP", averageVolts);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    // verificamos si ha terminado el comando
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    // llamado una vez el modo autonomo ha terminado
    // y cuando isFInished regresa true
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    // en caso de ser interrumpido
  }
}
