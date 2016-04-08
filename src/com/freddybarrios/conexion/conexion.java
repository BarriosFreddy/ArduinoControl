package com.freddybarrios.conexion;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;

/**
 *
 * @author Freddy Barrios
 */
public class conexion {

    private OutputStream Output = null;
    SerialPort serialPort;
    private final String PORT_NAME = "COM3";
    private final int ERROR = 0;
    private static final int TIME_OUT = 2000;
    private static final int DATA_RATE = 9600;

    public void ArduinoConnection() {

        CommPortIdentifier portId = null;
        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

        while (portEnum.hasMoreElements()) {
            CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();

            if (PORT_NAME.equals(currPortId.getName())) {
                portId = currPortId;
                break;
            }
        }

        if (portId == null) {
            System.exit(ERROR);
            return;
        }
        try {

            serialPort = (SerialPort) portId.open(this.getClass().getName(), TIME_OUT);

            serialPort.setSerialPortParams(DATA_RATE,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);

            Output = serialPort.getOutputStream();

        } catch (Exception e) {
            System.exit(ERROR);
        }

    }

    private void EnviarDatos(String data) {

        try {
            Output.write(data.getBytes());

        } catch (IOException e) {

            System.exit(ERROR);
        }
    }
}
