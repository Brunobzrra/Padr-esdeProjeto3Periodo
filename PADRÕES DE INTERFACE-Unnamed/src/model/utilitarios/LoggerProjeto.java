package model.utilitarios;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class LoggerProjeto {

    private static LoggerProjeto instancia;
    private FileHandler fh;

    private LoggerProjeto() {
        LogManager.getLogManager().reset();
        try {
            fh = new FileHandler(System.getProperty("user.dir") + "/debuginfo.log");
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }

    }

    public static synchronized LoggerProjeto getInstance() {
        if (instancia == null) {
            instancia = new LoggerProjeto();
        }
        return instancia;
    }

    public Logger getLogger() throws Exception {
        Logger l = Logger.getGlobal();
        l.addHandler(fh);
        l.setLevel(Level.INFO);
        return l;
    }
}