package business;

import entity.Bill;

import java.util.List;

public interface IBill {
    boolean createBill(Bill bill);
    List<Bill> getBillIn(int offSet,boolean type,String search);
    int getLengthBillIn(boolean type,String search);
    List<Bill> getBillInToUpdate(int offSet,boolean type);
    int getLengthBillInToUpdate(boolean type);
    boolean updateBillIn(Bill bill);
    Bill findBill(String emp_id,int bill_id);
    List<Bill> getBillByStatus(int offSet,String empId,boolean billType,String search);
    int getBillByStatuslength(String empId,boolean type,String search);
}
