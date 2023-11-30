package business;

import entity.Account;

import java.util.List;

public interface IAccount {
    List<Account> getAccount(int offset);
    int getlengthAccount();
    boolean isCheckUserName(String userName);
    boolean isCheckEmpId(String empid);
    boolean createAccount(Account account);
    Account findAccout(int id);
    boolean updateStatus(Account account);

    List<Account> getAccountSearch(int offSet,String search);
    int getlengthSearch(String search);
}
