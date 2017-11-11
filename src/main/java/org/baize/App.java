package org.baize;

import org.baize.server.GameServer;
import org.baize.utils.SpringUtils;
import org.baize.worktask.impl.WorkTaskPoolManager;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        SpringUtils.ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        //WorkTaskPoolManager.getInstance();
        GameServer.start();
        System.out.println( "Hello World!" );
    }
}
