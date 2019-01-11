/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

#include <frc/Joystick.h>
#include <frc/TimedRobot.h>
#include <frc/Timer.h>
#include <SmartDashboard.h>

/**
 * This sample program provides an example for ShuffleBoard, an alternative
 * to SmartDashboard for displaying values and properties of different robot
 * parts.
 *
 * ShuffleBoard can use pre-programmed widgets to display various values, such
 * as Boolean Boxes, Sliders, Graphs, and more. In addition, they can display
 * things in various Tabs.
 *
 * For more information on how to create personal layouts and more in
 * ShuffleBoard, feel free to reference the official FIRST WPILib documentation
 * online.
 */
class Robot : public frc::TimedRobot {
 public:
 Robot(){
   m_timer.Start();
 }
  void RobotInit() override {
    // Add a widget titled 'Max Speed' with a number slider.
    m_timer.Reset();
    m_timer.Start();
  }

  void AutonomousInit() override {
    // Update the Max Output for the drivetrain.
  }
  void AutonomousPeriodic() override{}

  void TeleopInit() override{}

  void TeleopPeriodic() override {

    frc::SmartDashboard::PutNumber("Joystick X valor", m_stick.GetX());
    frc::SmartDashboard::PutNumber("JoyStick Y valor", m_stick.GetY());
  }

 private:
  frc::Joystick m_stick{0};
  //nt::NetworkTableEntry m_maxSpeed;
  frc::Timer m_timer;
};

#ifndef RUNNING_FRC_TESTS
int main() { return frc::StartRobot<Robot>(); }
#endif
