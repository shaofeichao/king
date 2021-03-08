package com.common.program.program.pdf;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
public class TestPdf1 {
    // 利用模板生成pdf
    public static void fillTemplate() {
        // 模板路径
        String templatePath = "D:\\pdf\\123.pdf";
        // 生成的新文件路径
        String newPDFPath = "D:\\pdf\\1234.pdf";
        PdfReader reader;
        FileOutputStream out;
        ByteArrayOutputStream bos;
        PdfStamper stamper;
        try {
            out = new FileOutputStream(newPDFPath);
            reader = new PdfReader(templatePath);
            bos = new ByteArrayOutputStream();
            stamper = new PdfStamper(reader, bos);
            AcroFields form = stamper.getAcroFields();

            //方式一
            AcroFields s = stamper.getAcroFields();
            s.setField("Text1", "1");
            s.setField("Text2", "2");
            s.setField("Text3", "3");
            s.setField("Text4", "4");
            s.setField("Text5", "5");
            s.setField("Text6", "6");
            //方式2
            /*
            String[] str = { "123456789", "TOP__ONE", "男", "1991-01-01", "130222111133338888", "河北省保定市" };
            int i = 0;
            java.util.Iterator<String> it = form.getFields().keySet().iterator();
            while (it.hasNext()) {
                String name = it.next().toString();
                System.out.println(name);
                form.setField(name, str[i++]);
            }
            */
            //如果为false那么生成的PDF文件还能编辑，一定要设为true
            stamper.setFormFlattening(true);
            stamper.close();

            Document doc = new Document();
            PdfCopy copy = new PdfCopy(doc, out);
            doc.open();
            PdfImportedPage importPage = null;
            for (int j=1; j<=reader.getNumberOfPages();j++){
                importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), j);
                copy.addPage(importPage);
            }
            doc.close();

        } catch (IOException e) {
            System.out.println(1);
        } catch (DocumentException e) {
            System.out.println(2);
        }

    }

    public static void main(String[] args) {
        fillTemplate();
    }
}
