package src.source;

import IA.Red.CentrosDatos;
import IA.Red.Sensores;

import java.util.*;

public class EstatSensor {

    /*
    ATRIBUTOS
     */

    static Sensores sens; //Conjunto de sensores
    static CentrosDatos cd; //Conjunto de centros de datos

    ArrayList<Integer> transmissionesSC; //conexiones que hace un sensor a otro sensor o centro

    //del 0 al 99 nos referimos a sensores, del 100 al 10X nos referimos a centros (podrían ser caracteres tmb)

    ArrayList<Integer> numConectadosSC; //num de sensores o centros que tiene conectados 1 sensor o centro


    ArrayList<Double> info_Capturada_SC; //agafar size sensor o centro i ver la info capturada de cada uno

    /* INFO

     //cada centro de datos puede recibir hasta 150 Mb/s

     //un centro de datos puede recibir hasta 25 conexiones

     //cada sensor puede recibir el doble de su capacidad (si cap = 2,
    //puede recibir 4
    */

    final double capacitat_Centre = 150; //capacitat centre es de 150 Mb/s


    private double cost_transmissio; //MINIMIZARLA

    private double quantitat_informacio; //MAXIMIZARLA

    /*
     CONSTRUCTORA ESTADO INICIAL
     */
    public EstatSensor (int numSensores, int seedSens, int numCent, int seedCent) {

        sens = new Sensores(numSensores, seedSens);
        cd = new CentrosDatos(numCent, seedCent);

        transmissionesSC = new ArrayList<Integer>();
        for (int i = 0; i < numSensores + numCent; ++i) {
            transmissionesSC.add(-1);
        }

        numConectadosSC = new ArrayList<Integer>();
        for (int j = 0; j < numSensores+numCent; ++j) {
            numConectadosSC.add(0);
        }

        //ArrayList<Integer> info_Capturada_Sensor; //agafar size sensor i per cada sensor veure infoCapturada

        info_Capturada_SC = new ArrayList<Double>();

        for (int i = 0; i < numSensores; ++i) {
            info_Capturada_SC.add((double) 0);
        }
    }

    public EstatSensor(EstatSensor state) {

        sens = state.sens;
        cd = state.cd;

        transmissionesSC = new ArrayList<Integer>(state.transmissionesSC);
        numConectadosSC = new ArrayList<Integer>(state.numConectadosSC);

        info_Capturada_SC = new ArrayList<Double>(state.info_Capturada_SC);


    }


    /*
    OPERADORES
     */

    public void envia_info() {} //este operador envia info a un sensor o a un centro
    //hemos de tener en cuenta cual puede ser el más optimo


    /*
    GOAL TEST
     */
    public boolean isGoalState() {
        return false;
    }


    /*
    GETTERS
     */

    public Integer getTransmissionSC(int id) { //numero conexiones sensor

        System.out.println("Sensor " + id + ": " + transmissionesSC.get(id));
        return transmissionesSC.get(id);

    }

    public Integer getNConexionesCD(int id) { //numero conexiones CentroDatos

        System.out.println("Sensor " + id + ": " + numConectadosSC.get(id));
        return numConectadosSC.get(id);
    }

    public Double getInfoEmmagatzemadaSC(int num_sensor) {
        return info_Capturada_SC.get(num_sensor);
    }

     /*
    SETTERS
     */

    public void setTransmissionSC(int idO, int idD) { //idO es id origen, idD es destino
        //este operador envia info a un sensor o a un centro
        //hemos de tener en cuenta cual puede ser el más optimo

        if ((getNConexionesCD(idD) <= 3) && (getInfoEmmagatzemadaSC(idD) <= sens.get(idD).getCapacidad()*2)) {
                transmissionesSC.set(idO, idD);
                int count = getNConexionesCD(idD);
                numConectadosSC.set(idD, count++);
                double info = getInfoEmmagatzemadaSC(idD);
                info_Capturada_SC.set(idD, info+sens.get(idO).getCapacidad());
        }





    }



    public void setInfoEmmagatzemadaSC(int num_sensor) {

    }








}
