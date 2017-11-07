package org.baize.utils.excel;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;
@Service
public class ExcelUtils {
    /**
     *
     */
    private static Logger logger;
    static {
        logger = Logger.getLogger(ExcelUtils.class);
    }
    public static Map<String,String> getFileName(String path)
    {
        Map<String,String> fileMap = new HashMap<>();
        File file = new File(path);
        String[] beanNames = file.list();
        File[] files = file.listFiles();
        for (int i = 0;i<files.length;i++){
            String beanName = StringUtils.substringBeforeLast(beanNames[i],".");
            beanName = StringUtils.substringAfter(beanName,"_");
            String s = files[i].toString();
            String fileName = StringUtils.replace(s,"\\","/");
            fileMap.put(beanName,fileName);
        }
        return fileMap;
    }
    @PostConstruct
    public static void init() {
        Map<String,String> fileMap = getFileName("src/main/resources/excel");
        for (Map.Entry<String,String> entry:fileMap.entrySet()){
            ClassPathExcelContext(entry.getValue(),entry.getKey());
        }
    }
    /**
     * 解析ecxel表
     */
    private static void ClassPathExcelContext(String fileName,String beanName) {
        List<List<String>> objs;//用于后面转对象使用
        objs = new ArrayList<>();
        Workbook book = null;
        FileInputStream fis = null;
        try {
            logger.info("开始读取" + fileName + "文件的内容");
            fis = new FileInputStream(fileName);//取到文件
            book = new XSSFWorkbook(fis);
            fis.close();//及时关闭流
            logger.info("开始读取" + fileName + "工作谱的内容");
            Sheet sheet = book.getSheetAt(0);//取到工作谱（每一页是一个工作谱）
            Iterator<Row> rows = sheet.iterator();
            List<String> obj = null;
            logger.info("开始读取" + fileName + "行的内容");
            int i = 0;
            while (rows.hasNext()) {
                if(i == 0 || i == 3) {
                    ++i;
                    continue;
                }
                Row row = rows.next();
                Iterator<Cell> cells = row.iterator();
                obj = new ArrayList<String>();
                logger.info("开始读取" + fileName + "列的内容");
                while (cells.hasNext()) {
                    Cell cell = cells.next();
                    switch (cell.getCellType()) {   //根据cell中的类型来输出数据
                        case HSSFCell.CELL_TYPE_NUMERIC:
                            cell.setCellType(Cell.CELL_TYPE_STRING);//全部转换成string类型
                            break;
                    }
                    obj.add(cell.getStringCellValue());
                }
                objs.add(obj);
                ++i;
            }
            serializerFile(objs, beanName);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                book.close();
            } catch (Exception e) {
                logger.error(e);
            }
        }
    }
    private static void serializerFile(List<List<String>> file, String beanName) {
        List<Map<String, String>> objMap = new ArrayList<>();
        Map<String, String> objs;
        for (int i = 1; i < file.size(); i++) {
            objs = new HashMap<>();
            for (int j = 0; j < file.get(i).size(); j++) {
                objs.put(file.get(0).get(j), file.get(i).get(j));
            }
            objMap.add(objs);
        }
        serializerObj(objMap, beanName);
    }
    private static void serializerObj(List<Map<String, String>> objs, String clazzName) {
        Map<Serializable, Object> beanMap = new HashMap<>();
        Class clazz = null;
        Object beanObj = null;
        try {
            clazz = Class.forName(clazzName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            beanObj = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < objs.size(); i++) {
            for (Map.Entry<String,String> e:objs.get(i).entrySet()){
                try {
                    Field field = beanObj.getClass().getDeclaredField(e.getKey());
                    Field modifiersField = Field.class.getDeclaredField("modifiers");
                    modifiersField.setAccessible(true);
                    modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
                    Object valueObj = CheckType.getTypr(field.getClass(),e.getValue());
                    field.set(beanObj,valueObj);
                    beanMap.put(((DataTableMessage)beanObj).id(),beanObj);
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }
        //添加到所有导表缓存类中
        StaticConfigMessage.getInstance().put(beanObj.getClass(),beanMap);
    }

}  