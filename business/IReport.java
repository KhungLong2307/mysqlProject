package business;

import entity.Report;

import java.util.List;

public interface IReport {
    List<Report> getReport(int offSet,boolean type);
    int getLengthReport(boolean type);
    List<Report> getReportBetween(int offSet,boolean type,String date1,String date2);
    int getLengthReportBetween(boolean type,String date1,String date2);
    int[] getReportEmployee();
    List<Report> getReportBetweenMaxest(int offSet,boolean type,String date1,String date2);
    List<Report> getReportBetweenMinest(int offSet,boolean type,String date1,String date2);

}
