package org.baize.utils.clone;

import java.io.*;

/**
 * 作者：泡泡大湿
 * 时间：*******
 * 描述：java对象深度拷贝
 */
public class CloneUtils {
    public static <T extends Serializable> T clone(T t) {
        T cloneObj = null;
        ByteArrayOutputStream out = null;
        ObjectOutputStream objs = null;
        ByteArrayInputStream ios = null;
        ObjectInputStream ois = null;
        try {
            //写入字节流
            out = new ByteArrayOutputStream();
            objs = new ObjectOutputStream(out);
            objs.writeObject(t);

            //分配内存空间
            ios = new ByteArrayInputStream(out.toByteArray());
            ois = new ObjectInputStream(ios);
            cloneObj = (T) ois.readObject();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois == null)
                    ois.close();
                if (objs == null)
                    objs.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return cloneObj;
    }
}
