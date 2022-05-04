package com.techedgegroup.accademy.course.exceltorabbit.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.techedgegroup.accademy.course.rabbitimporter.model.Course;
import com.techedgegroup.accademy.course.rabbitimporter.model.Teacher;
import com.techedgegroup.accademy.course.rabbitimporter.model.TeacherData;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ExcelDataParser {
    Logger logger = LoggerFactory.getLogger(ExcelDataParser.class);

    private Workbook getWorkbook(String filename, InputStream is) {
        try {
            return WorkbookFactory.create(is);
        } catch (Exception e) {
            logger.error("Error parsing " + filename);
            return null;
        }
    }

    public List<TeacherData> parse(String filename, InputStream is) throws Exception {
        List<TeacherData> result = new ArrayList<TeacherData>();
        try {
            Workbook workbook = getWorkbook(filename, is);

            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {

                Sheet sheet = workbook.getSheetAt(i);

                TeacherData data = processSheet(sheet);
                result.add(data);

            }
            return result;
        } catch (Exception e) {
            logger.error("Error processData ", e);
        }
        return null;
    }

    private String getCellValue(Cell cell, DataFormatter dataFormatter, FormulaEvaluator formulaEvaluator) {
        return dataFormatter.formatCellValue(cell, formulaEvaluator);
    }

    private TeacherData processSheet(Sheet sheet) throws Exception {
        FormulaEvaluator formulaEvaluator = sheet.getWorkbook().getCreationHelper().createFormulaEvaluator();
        DataFormatter dataFormatter = new DataFormatter();

        TeacherData result = new TeacherData();

        Row header = sheet.getRow(1);
        if (checkIfRowIsEmpty(header)) {
            throw new Exception("Formato non valido");
        }

        Teacher teacher = new Teacher();

        String name = getCellValue(header.getCell(0), dataFormatter, formulaEvaluator);
        teacher.setTeacherName(name);

        String surname = getCellValue(header.getCell(1), dataFormatter, formulaEvaluator);
        teacher.setTeacherSurname(surname);

        String email = getCellValue(header.getCell(2), dataFormatter, formulaEvaluator);
        teacher.setTeacherEmail(email);

        result.setTeacher(teacher);

        List<Course> courses = new ArrayList<Course>();

        result.setCourses(courses);

        for (int i = 4; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);

            if (!checkIfRowIsEmpty(row)) {
                Course course = new Course();

                String courseName = getCellValue(row.getCell(0), dataFormatter, formulaEvaluator);
                course.setCourseName(courseName);

                String courseCategory = getCellValue(row.getCell(1), dataFormatter, formulaEvaluator);
                course.setCourseCategory(courseCategory);

                Date courseDate = row.getCell(2).getDateCellValue();
                course.setCourseDate(courseDate);

                courses.add(course);
            }
        }

        return result;
    }

    private boolean checkIfRowIsEmpty(Row row) {
        if (row == null) {
            return true;
        }
        if (row.getLastCellNum() <= 0) {
            return true;
        }
        for (int cellNum = row.getFirstCellNum(); cellNum < row.getLastCellNum(); cellNum++) {
            Cell cell = row.getCell(cellNum);
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                return false;
            }
        }
        return true;
    }
}
