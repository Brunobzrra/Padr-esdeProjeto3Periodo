package model.utilitarios;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class LoggerProjeto {

	private static LoggerProjeto instancia;

	private LoggerProjeto() {
		LogManager.getLogManager().reset();
	}

	public static synchronized LoggerProjeto getInstance() {
		if (instancia == null) {
			instancia = new LoggerProjeto();
		}
		return instancia;
	}

	public Logger getLogger() throws Exception {
		Logger l = LogManager.getLogManager().getLogger("LogProjeto");
		if (l == null) {
			l = Logger.getLogger("LogProjeto");
			FileHandler fh = new FileHandler(System.getProperty("user.dir") + "/debuginfo.log");
			l.addHandler(fh);
			l.setLevel(Level.INFO);
		}
		return l;
	}
}
