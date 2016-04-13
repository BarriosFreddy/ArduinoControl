package com.freddybarrios.conexion;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;

/**
 *
 * @author Freddy Barrios
 */
public class Conexion {

    private OutputStream Output = null;
    SerialPort serialPort;
    private final String PORT_NAME = "COM6";
    private final int ERROR = 0;
    private static final int TIME_OUT = 2000;
    private static final int DATA_RATE = 9600;

    public void arduinoConnection() {

        CommPortIdentifier portId = null;
        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

        while (portEnum.hasMoreElements()) {
            CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
            
            System.out.println("Nombre: " + currPortId.getName());
            if (PORT_NAME.equals(currPortId.getName())) {
                portId = currPortId;
                break;
            }
        }

        if (portId == null) {
            System.out.println("No se obtuvo el id del puerto");
            return;
        }
        try {
            serialPort = (SerialPort) portId.open(this.getClass().getName(), TIME_OUT);
            serialPort.setSerialPortParams(DATA_RATE,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
            Output = serialPort.getOutputStream();
        } catch (PortInUseException | UnsupportedCommOperationException | IOException e) {
            System.out.println("No se logro abrir el output stream");
        }

    }

    public void sendData(String data) {
//        try {
        System.out.println(data);
//            Output.write(data.getBytes());
//        } catch (IOException e) {
//            System.exit(ERROR);
//        }
    }
}
