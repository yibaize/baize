package org.baize;

import org.baize.server.GameServer;
import org.baize.worktask.WorkTaskPoolManager;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        GameServer.start(7788);
        WorkTaskPoolManager.getInstance();
        System.out.println( "Hello World!" );
    }
}
