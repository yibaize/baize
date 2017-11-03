package org.baize.server.message;

import org.baize.logic.Test;
import org.springframework.stereotype.Component;

/**
 * 作者： 白泽
 * 时间： 2017/11/3.
 * 描述：
 */
@Component
public class MessageRecieve {
    public MessageAb recieve(int id,String[] m){
        switch (id){
            case 1:
                return test(m);
        }
        return null;
    }
    private Test test(String[] m){
        int i = Integer.parseInt(m[1]);
        String a = m[2];
        return new Test(a,i);
    }
}
