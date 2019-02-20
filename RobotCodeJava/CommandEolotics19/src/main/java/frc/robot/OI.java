/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.RobotMap;
import frc.robot.subsystems.DifferentialDriveTrain;
import frc.robot.subsystems.ElevationSubsystem;
import frc.robot.subsystems.CargoHandler;
import frc.robot.subsystems.HatchPanelHandler;
//import edu.wpi.first.wpilibj.Compressor;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  // definimos una instancia de joystick
  public Joystick mStick;
  public XboxController xControl;
  // sistema principal
  private DifferentialDriveTrain mainDrive;
  private CargoHandler cargoHandler;
  private HatchPanelHandler hp_handler;
  private ElevationSubsystem elevator;
  //private Compressor compressor;

  public OI(){
    // constructor
    mStick = new Joystick(RobotMap.ACTUAL_USB_PORT);

    mainDrive = new DifferentialDriveTrain();
    cargoHandler = new CargoHandler();
    hp_handler = new HatchPanelHandler();
    elevator = new ElevationSubsystem();
    xControl = new XboxController(RobotMap.ACTUAL_XBOX_USB_PORT);
    //compressor = new Compressor();
    //compressor.setClosedLoopControl(true);
  }
  
  public double getSliderData(){
    return mStick.getRawAxis(RobotMap.SLIDER_AXIS_PORT);
  }

  public boolean canonLauncherPressed(){
    return mStick.getRawButtonPressed(RobotMap.LAUNCHER_BUTTON_NUMBER);
  }

  public boolean canonLauncherReleased(){
    return mStick.getRawButtonReleased(RobotMap.LAUNCHER_BUTTON_NUMBER);
  }

  public boolean canonSuckerPressed(){
    return mStick.getRawButtonPressed(RobotMap.SUCKER_BUTTON_NUMBER);
  }

  public boolean canonSuckerReleased() {
    return mStick.getRawButtonReleased(RobotMap.SUCKER_BUTTON_NUMBER);
  }

  public boolean button_3_Pressed(){
    return mStick.getRawButtonPressed(RobotMap.BUTTON_PORT_3);
  }

  public boolean button_3_Released() {
    return mStick.getRawButtonReleased(RobotMap.BUTTON_PORT_3);
  }

  public boolean button_5_Pressed(){
    return mStick.getRawButtonPressed(RobotMap.BUTTON_PORT_5);
  }

  public boolean button_5_Released(){
    return mStick.getRawButtonReleased(RobotMap.BUTTON_PORT_5);
  }

  public boolean button_xbox_XPressed(){
    // empujar back elevator
    return xControl.getXButtonPressed();
  }

  public boolean button_xbox_XReleased(){
    // empujar back elevator
    return xControl.getXButtonReleased();
  }

  public boolean button_xbox_APressed(){
    // contraer front elevator
    return xControl.getAButtonPressed();
  }

  public boolean button_xbox_AReleased(){
    // contraer front elevator
    return xControl.getAButtonReleased();
  }

  public boolean button_xbox_YPressed(){
    return xControl.getYButtonPressed();
  }

  public boolean button_xbox_YReleased(){
    return xControl.getYButtonReleased();
  }

  public boolean button_xbox_BPressed(){
    return xControl.getBButtonPressed();
  }

  public boolean button_xbox_BReleased(){
    return xControl.getBButtonReleased();
  }

  public double getZAxis(){
    // conseguir datos del slider
    return mStick.getRawAxis(RobotMap.SLIDER_AXIS_PORT);
  }

  public double getSpeed(){
    double sliderData = getSliderData();

    // los datos de la slider vienen de -1 a 1
    if(sliderData < 0)
    {
      // convirtiendolo a positivo
      sliderData = (1.0 + sliderData);
    }
    else{
      // 1 + 0....
      sliderData = 1.0 + sliderData;
    }
    // mapeando el valor para poder pasarlo como velocidad al motor launcher
    sliderData = sliderData / 2.0;
    
    return sliderData;
  }

  public void readingSolenoidButtons(){
    if(mStick.getRawButtonPressed(RobotMap.explode_button)){
      // si el boton 1 es presionado
      //s_op.direction = SolenoidOperation.Direction.FORWARD;
      hp_handler.explodeHatch();
      //System.out.println("explode");
    }
    if(mStick.getRawButtonReleased(RobotMap.explode_button)){
      // si el boton uno es liberado
      //s_op.direction = SolenoidOperation.Direction.STOP;
      hp_handler.stopSolenoid();
    }
    if(mStick.getRawButtonPressed(RobotMap.implode_button)){
      // si el boton 2 es presionado
      //s_op.direction = SolenoidOperation.Direction.REVERSE;
      hp_handler.implodeHatch();
      //System.out.println("implode");
    }
    if(mStick.getRawButtonReleased(RobotMap.implode_button)){
      // si el boton 2 es liberado
      //s_op.direction = SolenoidOperation.Direction.STOP;
      hp_handler.stopSolenoid();
    }

    // el sistema de elevacion de el solenoide
    if(xControl.getRawButtonPressed(4)){
      hp_handler.hatchUp();
    }
    if(xControl.getRawButtonReleased(4)){
      hp_handler.cascadeStop();
    }
    if(xControl.getRawButtonPressed(5)){
      hp_handler.hatchDown();
    }
    if(xControl.getRawButtonReleased(5)){
      hp_handler.cascadeStop();
    }
  }

  public void readingElevatorButtons(){
    //
    if(button_xbox_XPressed()){
      //
      elevator.StartFrontForward();
    }
    if(button_xbox_XReleased()){
      //
      elevator.StopFront();
    }
    if(button_xbox_APressed()){
      //
      elevator.StartFrontBackwards();
    }
    if(button_xbox_AReleased()){
      //
      elevator.StopFront();
    }
    if(button_xbox_YPressed()){
      //
      elevator.StartRearForward();
    }
    if(button_xbox_YReleased()){
      //
      elevator.StopRear();
    }
    if(button_xbox_BPressed()){
      //
      elevator.StartRearBackwards();
    }
    if(button_xbox_BReleased()){
      //
      elevator.StopRear();
    }
  }

  /*public boolean compresorPressed(){
    return mStick.getRawButtonPressed(RobotMap.compressor_button);
  }

  public boolean compresorReleased(){
    return mStick.getRawButtonReleased(RobotMap.compressor_button);
  }*/

  public void mainEventLoop(){
    // se toman las lecturas de todos los botones para ejecutar
    // las acciones
    double sliderData = getSpeed();

    mainDrive.Drive(mStick.getY(), mStick.getX());

    //double z_axis = getZAxis();

    if(canonLauncherPressed()){
      cargoHandler.launchCargo(sliderData);
    }
    if(canonLauncherReleased()){
      cargoHandler.stopGripper();
    }
    if(canonSuckerPressed()){
      cargoHandler.takeCargo(sliderData);
    }
    if(canonSuckerReleased()){
      cargoHandler.stopGripper();
    }
    if(button_3_Pressed()){
      // elevacion del arm
      boolean neg = false;
      cargoHandler.moverArmMotor(neg);
    }
    if(button_3_Released()){
      cargoHandler.stopArmMotor();
    }
    if(button_5_Pressed()){
      // con esto va hacia abajo el arm
      boolean neg = true;
      cargoHandler.moverArmMotor(neg);
    }
    if(button_5_Released()){
      cargoHandler.stopArmMotor();
    }

    // lecturas y movimiento de los solenoides
    readingSolenoidButtons();
    readingElevatorButtons();
  }
}
