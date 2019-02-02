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
//import java.lang.Math;

public class ProximitySensorCommand extends Command {
  AnalogInput sharp_input;
  double min_raw = 9999;
  double max_raw = 0;
  double GUIDE_VALUE = 80 / 3;

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
    //int averageVolts = sharp_input.getAverageValue();

    double measurement = this.getSensorData();
    SmartDashboard.putNumber("SENSOR SHARP", measurement);
    if(measurement > max_raw){
      // buscando el numero mas grande
      max_raw = measurement;
      SmartDashboard.putNumber("MAX", max_raw);
    }
    if(measurement < min_raw){
      // buscando el numero mas pequeno
      min_raw = measurement;
      SmartDashboard.putNumber("MIN", min_raw);
    }
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

  protected double getSensorData() {
    double raw = sharp_input.getAverageVoltage();
    // al natural sin calibrar
    //int raw = sharp_input.getValue();
    // esto corre durante todo el modo autonomo
    // de 5 a 0 V // el sensor mide de 0 a 80cm
    //SmartDashboard.putNumber("SENSOR SHARP", averageVolts);
    // raw mide entre 0 -4096
    //int measurement = (int)(28940.1 * Math.pow(raw, -1.16));
    //int measurement = 4800 / (raw - 20);

    // 1 / volts = distancia -> 0 - 3 v

    double inverse_volts = 1 / raw;
    return inverse_volts * GUIDE_VALUE;
  }
}
