/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radius.util;

/**
 *
 * @author gkrosnicki
 */
import org.apache.log4j.*;

public class LoggerFactory {
  public static Logger make() {
    Throwable t = new Throwable();
    StackTraceElement directCaller = t.getStackTrace()[1];
    return Logger.getLogger(directCaller.getClassName());
  }
}