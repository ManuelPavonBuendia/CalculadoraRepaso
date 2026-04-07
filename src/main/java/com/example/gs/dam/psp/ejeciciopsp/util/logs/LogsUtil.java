package com.example.gs.dam.psp.ejeciciopsp.util.logs;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogsUtil {

    private static boolean inicializado = false;

    private static final String RUTA_LOG = "demo/src/ficherosLog/app.log";

    public static Logger getLogger(String nombre) {
        Logger logger = Logger.getLogger(nombre);

        if (!inicializado) {
            try {
                FileHandler fh = new FileHandler(RUTA_LOG, true);
                fh.setFormatter(new SimpleFormatter());

                logger.addHandler(fh);
                logger.setUseParentHandlers(false);

                inicializado = true;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return logger;
    }
}
