package kr.poi.study;

import kr.poi.study.api.ExcelAPI;
import kr.poi.study.domain.Member;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import static kr.poi.study.util.ModelUtil.readModelVo;

public class Main {


    public static void main(String [] args) {

        ExcelAPI excelAPI = new ExcelAPI();
        excelAPI.makeFile();
    }
}
