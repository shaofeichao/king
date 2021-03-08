package com.common.program.program.pdf;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class PDFTempletTicket {
    private String templatePdfPath;
    private String ttcPath;
    private String targetPdfpath;
    private Ticket ticket;

    public PDFTempletTicket() {
        super();
    }

    public PDFTempletTicket(String templatePdfPath, String ttcPath,
                            String targetPdfpath, Ticket ticket) {
        this.templatePdfPath = templatePdfPath;
        this.ttcPath = ttcPath;
        this.targetPdfpath = targetPdfpath;
        this.ticket = ticket;
    }

    public void templetTicket(File file) throws Exception {

        PdfReader reader = new PdfReader(templatePdfPath);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PdfStamper ps = new PdfStamper(reader, bos);

        /*使用中文字体 */
        /*BaseFont bf = BaseFont.createFont(Ticket.class.getResource("/") + "org/csun/ns/util/simsun.ttc,1",
                BaseFont.IDENTITY_H, BaseFont.EMBEDDED);*/
        //ArrayList<BaseFont> fontList = new ArrayList < BaseFont > ();
        //fontList.add(bf);

        AcroFields s = ps.getAcroFields();
        //s.setSubstitutionFonts(fontList);

        s.setField("Text1", ticket.getTicketId());
        s.setField("Text2", ticket.getTicketCreateTime());
        s.setField("ticketCompany", ticket.getTicketCompany());
        s.setField("sysName", ticket.getSysName());
        s.setField("moneyLittle", ticket.getMoneyLittle());
        s.setField("moneyBig", ticket.getMoneyBig());
        s.setField("accountCompany", ticket.getAccountCompany());
        s.setField("bedNumber", ticket.getBedNumber());
        s.setField("username", ticket.getUsername());
        s.setField("password", ticket.getPassword());

        ps.setFormFlattening(true);
        ps.close();

        FileOutputStream fos = new FileOutputStream(file);
        fos.write(bos.toByteArray());
        fos.close();
    }
    public String getTemplatePdfPath() {
        return templatePdfPath;
    }
    public void setTemplatePdfPath(String templatePdfPath) {
        this.templatePdfPath = templatePdfPath;
    }
    public String getTtcPath() {
        return ttcPath;
    }
    public void setTtcPath(String ttcPath) {
        this.ttcPath = ttcPath;
    }
    public String getTargetPdfpath() {
        return targetPdfpath;
    }
    public void setTargetPdfpath(String targetPdfpath) {
        this.targetPdfpath = targetPdfpath;
    }
    public Ticket getTicket() {
        return ticket;
    }
    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
