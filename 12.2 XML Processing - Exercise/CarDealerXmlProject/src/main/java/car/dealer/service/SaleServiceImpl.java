package car.dealer.service;

import car.dealer.model.dto.exportDtos.CarForSaleDto;
import car.dealer.model.dto.exportDtos.CustomerViewModel;
import car.dealer.model.dto.exportDtos.SaleViewModel;
import car.dealer.model.entity.Part;
import car.dealer.model.entity.Sale;
import car.dealer.repository.SaleRepository;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
public class SaleServiceImpl implements  SaleService {
    private final SaleRepository saleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public void save(Sale sale) {
        this.saleRepository.saveAndFlush(sale);
    }

    @Override
    public void save(List<Sale> sales) {
        this.saleRepository.saveAll(sales);

    }

    @Override
    public List<CustomerViewModel> allCustomersAndSpentMoney() {
        return this.saleRepository.customersAndTotalSpentMoney();
    }

    @Override
    public List<SaleViewModel> salesWithCarAndCustomerData() {
        List<Sale> sales = this.saleRepository.findAll();
        List<SaleViewModel> models = new ArrayList<>();
        for (Sale sale:sales) {
            SaleViewModel s = new SaleViewModel();
            CarForSaleDto car = new CarForSaleDto();
            if(sale.getCar() != null){
                car.setMake(sale.getCar().getMake());
                car.setModel(sale.getCar().getModel());
                car.setDistanceTravelled(sale.getCar().getTravelledDistance());
                s.setCar(car);
            }
            if(sale.getCustomer() != null){
                s.setCustomerName(sale.getCustomer().getName());
            }
            BigDecimal price = new BigDecimal(0);
            if(sale.getCar() != null){
                for (Part p:sale.getCar().getParts()) {
                    price = price.add(p.getPrice());
                }
            }
            s.setPrice(price);
            s.setPriceWithDiscount(price.multiply(BigDecimal.valueOf(sale.getDiscount())));
            models.add(s);
        }
        return models;
    }
}
