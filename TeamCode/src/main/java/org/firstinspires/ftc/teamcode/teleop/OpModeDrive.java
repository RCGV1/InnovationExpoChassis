package org.firstinspires.ftc.teamcode.teleop;

import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.command.drive.DefaultRobotCentricDrive;
import org.firstinspires.ftc.teamcode.command.drive.SlowMode;
import org.firstinspires.ftc.teamcode.teleop.baseOpModes.BaseDriveOpMode;

//@Deprecated
@TeleOp(name = "Drive TeleOp")
public class OpModeDrive extends BaseDriveOpMode {

    private GamepadEx driverOp1;

    private DefaultRobotCentricDrive robotCentricDrive;

    private SlowMode slowMode;
    private Button slowtime;

    @Override
    public void initialize() {
        super.initialize();
        /*
        Player1
            Left Stick X -> Strafe
            Right Stick Y -> Forward and Back
            Left Trigger = Turn left
            Right Trigger = Turn Right

            Left bumper -> toggles between slow mode and normal mode\
         */

        driverOp1 = new GamepadEx(gamepad1);

        robotCentricDrive = new DefaultRobotCentricDrive(drive,
                () -> driverOp1.getRightX(),
                () -> driverOp1.getLeftY(),
                () -> driverOp1.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER),
                () -> driverOp1.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER));

        slowMode = new SlowMode(drive,
                () -> driverOp1.getRightX(),
                () -> driverOp1.getLeftY(),
                () -> driverOp1.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER),
                () -> driverOp1.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER));

        slowtime = (new GamepadButton(driverOp1,
                GamepadKeys.Button.LEFT_BUMPER)).toggleWhenPressed(robotCentricDrive,slowMode);

        register(drive);
        drive.setDefaultCommand(robotCentricDrive);
    }
}