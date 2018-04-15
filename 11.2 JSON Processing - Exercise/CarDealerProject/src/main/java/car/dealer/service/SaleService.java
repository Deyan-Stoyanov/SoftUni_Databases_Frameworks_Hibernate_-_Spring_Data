package car.dealer.service;

import car.dealer.model.dto.CustomerViewModel;
import car.dealer.model.dto.SaleViewModel;
import car.dealer.model.entity.Sale;

import java.util.List;

public interface SaleService {
    void save(Sale sale);
    void save(List<Sale> sales);
    List<CustomerViewModel> allCustomersAndSpentMoney();
    List<SaleViewModel> salesWithCarAndCustomerData();
}
