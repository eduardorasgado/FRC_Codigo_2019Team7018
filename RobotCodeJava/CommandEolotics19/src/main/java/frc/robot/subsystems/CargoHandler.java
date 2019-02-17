/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Spark;
import frc.robot.RobotMap;

public class CargoHandler extends Subsystem {
  
  private Spark canonUpperMotor; // motor arriba
  private Spark canonBottomMotor; // moyot de abajo
  // definiendo el motor para el canon arm
  private Spark canonArmMotor;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    canonUpperMotor = new Spark(RobotMap.MotorCanonUpper);
    canonBottomMotor = new Spark(RobotMap.MotorCanonBottom);
    canonArmMotor = new Spark(RobotMap.MotorCanonArm);
  }

  public void takeCargo(){
    // mecanismo de sustraccion
    // baja el brazo y succiona
  }

  public void homePosition(){
    // se puede mandar a llamar despues de soltar
    // el boton de succion, para el motor
  }

  public void launchCargo(){
    // mecanismo de lanzamiento
    // ubica el brazo y lanza
  }

  public void moveCargoUpperMotor(){
    // toma una velocidad y activa el motor superior
  }

  public void moveCargoBottomMotor(){
    // toma una velocidad y activa el motor inferior
  }

  public void stopCargoUpperMotor(){
    // para los motores del sucker/launcher superior
  }
  public void stopCargoBottomMotor(){
    // para el motor del sucker/launcher inferior
  }
  public void stopArmMotor(){
    // para el motor del brazo
  }
}
