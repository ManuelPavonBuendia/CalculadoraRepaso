package com.example.gs.dam.psp.ejeciciopsp.util.logs;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogsUtil {

    private static boolean inicializado = false;

    public static Logger getLogger(String nombre) {
        Logger logger = Logger.getLogger(nombre);

        if (!inicializado) {
            try {
                FileHandler fh = new FileHandler("demo/app.log", true);
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
