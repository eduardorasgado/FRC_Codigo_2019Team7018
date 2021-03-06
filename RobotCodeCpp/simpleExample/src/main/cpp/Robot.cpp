/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
#pragma once


#include <frc/Joystick.h>
#include <frc/TimedRobot.h>
#include <frc/Timer.h>
#include <SmartDashboard.h>

class Robot : public frc::TimedRobot {
 public:
  Robot(){
    m_timer.Start();
  }

  void AutonomousInit() override {
    // Update the Max Output for the drivetrain.
    m_timer.Reset();
    m_timer.Start();
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

