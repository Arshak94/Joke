package com.optym.joke.excel.view;

import com.optym.joke.exception.ExcelException;
import com.optym.joke.model.Joke;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Component
public class JokesExcelView {
    public byte[] buildExcelDocument(List<Joke> jokes) {
        try(Workbook workbook = new XSSFWorkbook()) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            Sheet sheet = workbook.createSheet("jokes");

            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Id");
            header.createCell(1).setCellValue("Punchline");
            header.createCell(2).setCellValue("Type");
            header.createCell(3).setCellValue("Setup");

            int rowCount = 1;
            for (Joke joke : jokes) {
                Row courseRow = sheet.createRow(rowCount++);
                courseRow.createCell(0).setCellValue(joke.getId());
                courseRow.createCell(1).setCellValue(joke.getPunchline());
                courseRow.createCell(2).setCellValue(joke.getType());
                courseRow.createCell(3).setCellValue(joke.getSetup());
            }
            workbook.write(out);
            return out.toByteArray();
        } catch (Exception e){
            throw new ExcelException();
        }
    }
}
