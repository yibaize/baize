package org.baize.utils.assemblybean.service;

import java.util.List;

/**
 * 作者： 白泽
 * 时间： 2017/11/3.
 * 描述：
 */
public class AssemblyBean {
    /**
     * 拼装对象
     */
    public static void assemblyBean(String beanName, List<String> s, SelectAnnotationClass.AnnonEnum ae){
        StringBuffer sb = new StringBuffer();
        if(ae == SelectAnnotationClass.AnnonEnum.DataTable){
            sb.append("using System;\n");
            sb.append("using System.Collections.Generic;\n");
            for (String str:s){
                sb.append(str);
            }
        }else if(ae == SelectAnnotationClass.AnnonEnum.Protocol){
            sb.append("using System;\n");
            sb.append("using System.Collections.Generic;\n");
            for (String str:s){
                sb.append(str);
            }
        }else if(ae == SelectAnnotationClass.AnnonEnum.Protostuff){
            sb.append("using System;\n");
            sb.append("using System.Collections.Generic;\n");
            sb.append("using ProtoBuf;\n");
            sb.append("[ProtoContract]\n");
            for (int i = 0;i<s.size();i++){
                sb.append(s.get(i));
                if(i<s.size()-2)
                    sb.append("\t[ProtoMember("+(i+1)+")]"+"\n");
            }
        }
        WriteFile.writeText(beanName+".cs",sb.toString(),"E:\\cs");
    }
}
