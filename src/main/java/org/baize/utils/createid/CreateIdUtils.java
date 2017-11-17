package org.baize.utils.createid;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.baize.utils.DateUtils;
import org.baize.utils.LoggerUtils;

import java.io.*;

/**
 * 作者： 白泽
 * 时间： 2017/11/8.
 * 描述：
 */
public class CreateIdUtils {
    public static synchronized int id(){
        IdModule module = readId();
        if(module.getTimer() <= DateUtils.currentTime()) {
            module.setTimer(DateUtils.monthEndMllis());
            module.setId(0);
        }
        int id = module.getId();
        String time = DateUtils.currentDay()+"";
        time = StringUtils.substring(time,2,6);
        id++;
        if(id<10)
            time += "0"+id;
        else
            time += id;
        module.setId(id);
        writeId(module);
        return Integer.parseInt(time);
    }
    private static IdModule readId(){
        try { // 防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw
            File filename = new File(CreateIdUtils.class.getResource("/id.txt").getFile()); // 要读取以上路径的input。txt文件
           // File filename = new File("/log4j.properties");
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(filename)); // 建立一个输入流对象reader
            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
            String line = "";
            line = br.readLine();
            IdModule idModule = JSON.parseObject(line,IdModule.class);
            return idModule;
        } catch (Exception e) {
            LoggerUtils.getLogicLog().error("创建id时读取id.text文件失败",e);
            return null;
        }
    }
    private static void writeId(IdModule module){
        String json = JSON.toJSONString(module);
        try {
            File writename = new File(CreateIdUtils.class.getResource("/id.txt").getFile()); // 相对路径，如果没有则要建立一个新的output。txt文件
            writename.createNewFile(); // 创建新文件
            BufferedWriter out = new BufferedWriter(new FileWriter(writename));
            out.write(json); // \r\n即为换行
            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后记得关闭文件
        }catch (Exception e){
            LoggerUtils.getLogicLog().error("创建id时写入id.text文件失败",e);
        }
    }

}
