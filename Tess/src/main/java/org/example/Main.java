package org.example;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) throws TesseractException {
        String inputDirectoryPath = "src\\main\\resources\\";
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("C:\\Users\\Gokul Chaluvadi\\Downloads\\Tess4J-3.4.8-src\\Tess4J\\tessdata");

        StringBuilder html = new StringBuilder();
        html.append("<html>\n");
        html.append("<head>\n");
        html.append("<title>Text Recognition Results</title>\n");
        html.append("</head>\n");
        html.append("<body>\n");
        html.append("<h1>Text Recognition Results</h1>\n");

        File inputDirectory = new File(inputDirectoryPath);
        File[] files = inputDirectory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".png")) {
                    String inputFilePath = file.getAbsolutePath();
                    String fullText = tesseract.doOCR(file);
                    html.append("<h2>Result for " + file.getName() + "</h2>\n");
                    html.append("<p><img src='" + inputFilePath + "' alt='Input Image' /></p>\n");
                    html.append("<p>" + fullText + "</p>\n");
                }
            }
        }

        html.append("<p><a href='https://github.com/nguyenq/tess4j'>Learn more about Tesseract and Tess4J</a></p>\n");
        html.append("</body>\n");
        html.append("</html>\n");

        File htmlFile = new File("result.html");
        try (PrintWriter writer = new PrintWriter(htmlFile)) {
            writer.println(html);
        } catch (FileNotFoundException e) {
            System.out.println("Error creating HTML file: " + e.getMessage());
        }
    }
}
