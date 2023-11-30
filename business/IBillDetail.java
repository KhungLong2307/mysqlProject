package business;

import entity.Bill;
import entity.BillDetail;

import java.util.List;

public interface IBillDetail {
    boolean createBillDetail(BillDetail billDetail);
    int takeBillId(Bill bill);
    List<BillDetail> getBillDetailToUpdate(int offSet,int billId);
    int getBillDetailToUpdateLength(int billId);
    boolean updateBillDetail(BillDetail billDetail);
}
