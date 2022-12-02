package com.esprit.kaddemback.services;

import com.esprit.kaddemback.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.w3c.dom.Document;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextRenderer;

import org.thymeleaf.context.Context;
import java.io.*;
import java.util.List;

@Service
public class PdfService {

    private static  String PDF_RESOURCES = "/pdf-ressources/";

    private SpringTemplateEngine springTemplateEngine;

    private UserServiceImpl userService;

    @Autowired
    public PdfService(SpringTemplateEngine springTemplateEngine, UserServiceImpl userService) {
        this.springTemplateEngine = springTemplateEngine;
        this.userService = userService;
    }


    public File generateUsersPdf() throws Exception{
        Context context = getContextUsersListPdf();
        String html = loadAndFillTemplate(context);
        String xhtml = convertToXhtml(html);
        return renderUserListPdf(xhtml);
    }

    private String convertToXhtml(String html) throws UnsupportedEncodingException {
        Tidy tidy = new Tidy();
        tidy.setXHTML(true);
        tidy.setIndentContent(true);
        tidy.setPrintBodyOnly(true);
        tidy.setInputEncoding("UTF-8");
        tidy.setOutputEncoding("UTF-8");
        tidy.setSmartIndent(true);
        tidy.setShowWarnings(false);
        tidy.setQuiet(true);
        tidy.setTidyMark(false);

        Document htmlDOM = tidy.parseDOM(new ByteArrayInputStream(html.getBytes()), null);

        OutputStream out = new ByteArrayOutputStream();
        tidy.pprint(htmlDOM, out);
        return out.toString();
    }

    private File renderUserListPdf(String html) throws Exception {
        File file = File.createTempFile("users", ".pdf");
        OutputStream outputStream = new FileOutputStream(file);
        ITextRenderer renderer = new ITextRenderer(20f * 4f / 3f, 20);
        renderer.setDocumentFromString(html, new ClassPathResource(PDF_RESOURCES).getURL().toExternalForm());
        renderer.layout();
        renderer.createPDF(outputStream);
        outputStream.close();
        file.deleteOnExit();
        return file;
    }

    private Context getContextUsersListPdf() {
        List<User> userList = this.userService.retrieveAllUsers();
        Context context = new Context();
        context.setVariable("users", userList);
        return context;
    }

    private String loadAndFillTemplate(Context context) {
        return springTemplateEngine.process("usersPDF", context);
    }

}

