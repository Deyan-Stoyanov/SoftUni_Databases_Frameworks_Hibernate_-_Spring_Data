package car.dealer.service;

import car.dealer.model.dto.CarForSaleDto;
import car.dealer.model.dto.CustomerViewModel;
import car.dealer.model.dto.SaleViewModel;
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
        Converter<Sale, SaleViewModel> con = new AbstractConverter<Sale, SaleViewModel>() {
            @Override
            protected SaleViewModel convert(Sale sale) {
                SaleViewModel s = new SaleViewModel();
                s.setCar(modelMapper.map(sale.getCar(), CarForSaleDto.class));
                if(sale.getCustomer() != null){
                    s.setCustomerName(sale.getCustomer().getName());
                }

                BigDecimal price = new BigDecimal(0);
                for (Part p:sale.getCar().getParts()) {
                    price = price.add(p.getPrice());
                }
                s.setPrice(price);
                s.setPriceWithDiscount(price.multiply(BigDecimal.valueOf(sale.getDiscount())));
                return s;
            }
        };
        this.modelMapper.addConverter(con);
        List<SaleViewModel> models = new ArrayList<>();
        for (Sale s:sales) {
            models.add(modelMapper.map(s, SaleViewModel.class));
        }
        return models;
    }
}
