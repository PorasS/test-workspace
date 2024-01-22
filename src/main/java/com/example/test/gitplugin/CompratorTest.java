package com.example.test.gitplugin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CompratorTest {
    static class Field{
        String fieldName;
        int rowNumber;
        int columnNumber;

        public Field(){

        }
        public Field(String fieldName, int rowNumber, int columnNumber) {
            this.fieldName = fieldName;
            this.rowNumber = rowNumber;
            this.columnNumber = columnNumber;
        }

        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        public int getRowNumber() {
            return rowNumber;
        }

        public void setRowNumber(int rowNumber) {
            this.rowNumber = rowNumber;
        }

        public int getColumnNumber() {
            return columnNumber;
        }

        public void setColumnNumber(int columnNumber) {
            this.columnNumber = columnNumber;
        }

        @Override
        public String toString() {
            return "Field{" +
                    "fieldName='" + fieldName + '\'' +
                    ", rowNumber=" + rowNumber +
                    ", columnNumber=" + columnNumber +
                    '}';
        }
    }

    public static void main(String[] args) {
        Field field = new Field("SHRtbFRleHRfMjAyMy0wMS0wM1QxMTo1MjoyOS4wMjha", 0, 1);
        Field field2 = new Field("LastName", 3, 0);
        Field field3 = new Field("LastName", 1, 1);
        Field field4 = new Field("LastName", 2, 0);
        Field field5 = new Field("LastName", 1, 0);

        List<Field> fields = new ArrayList<>();
        fields.add(field);
        fields.add(field3);
        fields.add(field4);
        fields.add(field5);

        Collections.sort(fields, Comparator.comparing(Field::getRowNumber).thenComparing(Field::getColumnNumber));
//        Collections.sort(fields, Comparator.comparing(Field::getColumnNumber));

        for(Field f:fields){
            System.out.println(f.toString());
        }

    }



}
