
/*
by alex 24.11.2017
 */

        package org.firstinspires.ftc.teamcode;

        import com.qualcomm.robotcore.eventloop.opmode.Disabled;
        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
        import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
        import com.qualcomm.robotcore.hardware.DcMotor;
        import com.qualcomm.robotcore.hardware.Servo;
        import com.qualcomm.robotcore.util.Range;

        import org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot;


@TeleOp(name="FTelOP", group="Pushbot")
//@Disabled
public class MyFTelOP extends LinearOpMode {

    private DcMotor motorcub;
    private Servo leftservo;
    private Servo rightservo;
    private DcMotor motorrelicva;
    //pppprivate Servo blocare;
    private DcMotor motorleftfront;
    private DcMotor motorleftback;
    private DcMotor motorrightfront;
    private DcMotor motorrightback;



    private static final double initialPozright = 0.62;
    private static final double initialPozleft = 0.6;
    private static final double extendedPozleft = 0.15;
    private static final double extendedPozright = 0.8;
    // private static final double blocaredisarm = 0.4;
    // private static final double blcarearm = 0;
    // private static final double motion1 = 0.5;
    // private static final double motion2 = 1;
    private static double viteza = 0.8;
    private static double viteza2 = 0.55;
    @Override
    public void runOpMode() {

        motorcub = hardwareMap.dcMotor.get("motorcub");
        leftservo = hardwareMap.servo.get("leftservo");
        rightservo = hardwareMap.servo.get("rightservo");
        motorrelicva = hardwareMap.dcMotor.get("motorrelicva");
        //blocare = hardwareMap.servo.get("blocare");
        motorrightback = hardwareMap.dcMotor.get("motorrightback");
        motorrightfront = hardwareMap.dcMotor.get("motorrightfront");
        motorleftback = hardwareMap.dcMotor.get("motorleftback");
        motorleftfront = hardwareMap.dcMotor.get("motorleftfront");

        // blocare.setPosition(blocaredisarm);
        leftservo.setPosition(initialPozleft);
        rightservo.setPosition(initialPozright);
        // robot.init(hardwareMap);
        telemetry.update();

        //(driver presses PLAY)
        waitForStart();
        while (opModeIsActive()) {
            //metoda 1 de actionare a glisierie verticale
            motorcub.setPower(gamepad1.left_stick_y);


            //actionare glisiera
            motorrelicva.setPower(gamepad1.right_stick_y);

            if (gamepad2.dpad_down) {

                viteza = 0.3;
            }
            if (gamepad2.dpad_up) {
                viteza = 0.82;
            }
            if (gamepad2.left_stick_y > 0) {
                motorleftback.setPower(viteza);
                motorleftfront.setPower(viteza);
                motorrightback.setPower(-viteza);
                motorrightfront.setPower(-viteza);
            } else if (gamepad2.left_stick_y < 0) {
                motorleftback.setPower(-viteza);
                motorleftfront.setPower(-viteza);
                motorrightback.setPower(viteza);
                motorrightfront.setPower(viteza);
            } else if (gamepad2.left_stick_x < 0) {
                motorleftback.setPower(-viteza);
                motorleftfront.setPower(viteza);
                motorrightback.setPower(-viteza);
                motorrightfront.setPower(viteza);
            } else if (gamepad2.left_stick_x > 0) {
                motorleftback.setPower(viteza);
                motorleftfront.setPower(-viteza);
                motorrightback.setPower(viteza);
                motorrightfront.setPower(-viteza);
            } else if (gamepad2.right_stick_x < 0) {
                motorleftback.setPower(viteza2);
                motorleftfront.setPower(viteza2);
                motorrightback.setPower(viteza2);
                motorrightfront.setPower(viteza2);
            } else if (gamepad2.right_stick_x > 0) {
                motorleftback.setPower(-viteza2);
                motorleftfront.setPower(-viteza2);
                motorrightback.setPower(-viteza2);
                motorrightfront.setPower(-viteza2);
            } else {
                motorleftback.setPower(0);
                motorleftfront.setPower(0);
                motorrightback.setPower(0);
                motorrightfront.setPower(0);
            }


            //first method of driving

            /*
                            motorleftback.setPower(gamepad2.left_stick_y);
                motorleftfront.setPower(gamepad2.left_stick_y);
                motorrightback.setPower(-gamepad2.right_stick_y);
                motorrightfront.setPower(-gamepad2.right_stick_y);
             */
            //second method of diving
          /*  if (gamepad2.left_stick_y > 0 && gamepad2.right_stick_y > 0) {
                motorleftback.setPower(motion2);
                motorleftfront.setPower(motion2);
                motorrightback.setPower(-motion2);
                motorrightfront.setPower(-motion2);
            }
            if (gamepad2.left_stick_y < 0 && gamepad2.right_stick_y < 0) {
                motorleftback.setPower(-motion1);
                motorleftfront.setPower(-motion1);
                motorrightback.setPower(motion1);
                motorrightfront.setPower(motion1);
            }
            if (gamepad2.left_stick_y > 0 && gamepad2.right_stick_y < 0) {
                motorleftback.setPower(motion2);
                motorleftfront.setPower(motion2);
                motorrightback.setPower(-motion1);
                motorrightfront.setPower(-motion1);
            }
            if (gamepad2.left_stick_y < 0 && gamepad2.right_stick_y > 0) {
                motorleftback.setPower(-motion1);
                motorleftfront.setPower(-motion1);
                motorrightback.setPower(motion2);
                motorrightfront.setPower(motion2);
            }
            */


            if (gamepad2.right_trigger == 1) {
                leftservo.setPosition(extendedPozleft);
                rightservo.setPosition(extendedPozright);
            }
            //pozitii servo cuburi
            else {
                leftservo.setPosition(initialPozleft);
                rightservo.setPosition(initialPozleft);
            }
            // if(gamepad1.b)
            //{
            //blocare.setPosition(blcarearm);
            // }

            //metoda 2. de actionare a glisierie verticale
            /*if (gamepad2.dpad_up) {
                motorcub.setPower(motion2);
            } else {
                motorcub.setPower(0);
            }
            if(gamepad2.dpad_down)
            {
                motorcub.setPower(-motion2);
            }
            else {
                motorcub.setPower(0);
            }*/


            telemetry.update();
            //sleep(50);
            idle();
        }
    }}
