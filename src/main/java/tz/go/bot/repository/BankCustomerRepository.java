package tz.go.bot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tz.go.bot.model.BankCustomer;

@Repository
public interface BankCustomerRepository extends CrudRepository<BankCustomer,Integer> {

    public BankCustomer findBankCustomerByEmail(String email);
}
