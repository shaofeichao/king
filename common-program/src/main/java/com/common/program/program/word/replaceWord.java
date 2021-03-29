package com.common.program.program.word;

import com.deepoove.poi.XWPFTemplate;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class replaceWord {

    public static void main(String[] args) {
        try {
            XWPFTemplate template = XWPFTemplate.compile("D:\\pdf\\11.docx").render(new HashMap<String, Object>(){{
                put("jiafang", "poi-tl Word模板引擎");
            }});
            FileOutputStream out = new FileOutputStream("D:\\pdf\\21.docx");
            template.write(out);
            out.flush();
            out.close();
            template.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
