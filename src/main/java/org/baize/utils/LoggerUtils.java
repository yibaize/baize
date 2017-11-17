package org.baize.utils;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.baize.worktask.IDailyTimer;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 作者： 白泽
 * 时间： 2017/11/15.
 * 描述：
 */
public class LoggerUtils{
    private static Logger platformLog = null;
    private static Logger logicLog = null;
    private static final ConcurrentHashMap<String, Logger> logMap = new ConcurrentHashMap();
    private static final String LOG4J_CONFIG_XML_PATH;

    public LoggerUtils() {
    }

    public static Logger getPlatformLog() {
        return platformLog;
    }

    public static Logger getLogicLog() {
        return logicLog;
    }

    public static Logger getLogByName(String logName) {
        Logger _logger = (Logger)logMap.get(logName);
        if (_logger == null) {
            _logger = LogManager.getLogger(logName);
            logMap.put(logName, _logger);
        }

        return _logger;
    }

    public static void openLog(Logger log) {
        log.setAdditivity(true);
    }

    public static void closeLog(Logger log) {
        log.setAdditivity(false);
    }

    public static void setDebugStatus(Logger log) {
        log.setLevel(Level.DEBUG);
    }

    public static void setOperateStatus(Logger log) {
        log.setLevel(Level.ERROR);
    }


    static {
        LOG4J_CONFIG_XML_PATH = "/log4j.properties";
        File filename = new File(LoggerUtils.class.getResource(LOG4J_CONFIG_XML_PATH).getFile());
        PropertyConfigurator.configure(filename.getPath());
        platformLog = LogManager.getLogger("platformlogs");
        logicLog = LogManager.getLogger("logiclogs");
    }
}
