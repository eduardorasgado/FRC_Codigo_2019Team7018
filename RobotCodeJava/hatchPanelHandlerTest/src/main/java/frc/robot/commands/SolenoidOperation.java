/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.HatchPanelHandler;
import frc.robot.Robot;

public class SolenoidOperation extends Command {

  // usando el objeto subsystem creado en el Robot
  HatchPanelHandler subsystem = Robot.solenoidSubsystem;

  public Direction direction;

  public static enum Direction {
    FORWARD,
    REVERSE,
    STOP
  }

  public SolenoidOperation(Direction direction) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.solenoidSubsystem);
    this.direction = direction;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // durante el llamado de esta operacion se solicitan tres estados
    switch(direction) {
      case FORWARD:
        subsystem.explodeHatch();
        break;
      case REVERSE:
        subsystem.implodeHatch();
        break;
      case STOP:
        subsystem.stopSolenoid();
      default:
        break;
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
