package service;

import model.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountService {
        List<Account> accounts = new ArrayList<>();

        public List<Account> findAll() {
            return new ArrayList<>();
        }

        public void addAccount(Account account) {
            accounts.add(account);
        }

        public Account findById(int id) {
            for (int i = 0; i < accounts.size(); i++) {
                if (accounts.get(i).getId() == id) {
                    return accounts.get(i);
                }
            }
            return null;
        }

        public int findIndexById(int id) {
            for (int i = 0; i < accounts.size(); i++) {
                if (accounts.get(i).getId() == id) {
                    return i;
                }
            }
            return 1;
        }

        public void updateCustomer(int id , Account account){
            accounts.set( findIndexById(id) ,account);
        }

        public void deleteCustomer(int id){
            accounts.remove(findById(id));
        }
}
