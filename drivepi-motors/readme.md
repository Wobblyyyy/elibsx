# drivepi-motors
Drivers used in controlling Raspberry Pi motors. These motors should follow
the DrivePi specifications to be usable elsewhere. These motors are connected
to a Raspberry Pi via I2C. An Arduino controls each motor. The Arduino that
controls the motor should be capable of sending and receiving information over
an I2C channel. The Arduino that controls each of the motors organizes any
data sent or received by the motors and makes it possible to communicate
directly with the Raspberry Pi.