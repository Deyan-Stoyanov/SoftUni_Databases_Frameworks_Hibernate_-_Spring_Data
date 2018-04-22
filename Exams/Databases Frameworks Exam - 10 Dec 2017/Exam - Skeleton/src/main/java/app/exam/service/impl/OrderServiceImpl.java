package app.exam.service.impl;

import app.exam.domain.dto.json.EmployeeOrdersJSONExportDTO;
import app.exam.domain.dto.json.ItemJSONExportDTO;
import app.exam.domain.dto.json.OrderJSONExportDTO;
import app.exam.domain.dto.xml.OrderItemXMLImportDTO;
import app.exam.domain.dto.xml.OrderXMLImportDTO;
import app.exam.domain.entities.*;
import app.exam.parser.ValidationUtil;
import app.exam.repository.EmployeeRepository;
import app.exam.repository.ItemsRepository;
import app.exam.repository.OrderItemRepository;
import app.exam.repository.OrderRepository;
import app.exam.service.api.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ItemsRepository itemsRepository;
    private final EmployeeRepository employeeRepository;
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ItemsRepository itemsRepository, EmployeeRepository employeeRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.itemsRepository = itemsRepository;
        this.employeeRepository = employeeRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public void create(OrderXMLImportDTO dto) throws ParseException {
        if (ValidationUtil.isValid(dto)) {
            Order order = new Order();
            order.setCustomer(dto.getCustomer());
            Employee employee = new Employee();
            boolean employeeExists = false;
            if (this.employeeRepository.findByName(dto.getEmployee()) != null) {
                employeeExists = true;
                employee = this.employeeRepository.findByName(dto.getEmployee());
                order.setEmployee(employee);
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            order.setDate(simpleDateFormat.parse(dto.getDate()));
            order.setType(OrderType.valueOf(dto.getType()));
            boolean itemsExist = true;
            List<OrderItem> orderItems = new ArrayList<>();
            for (OrderItemXMLImportDTO i:dto.getItems()) {
                if(this.itemsRepository.findByName(i.getName()) != null){
                    OrderItem orderItem = new OrderItem();
                    Item item = this.itemsRepository.findByName(i.getName());
                    this.itemsRepository.save(item);
                    orderItem.setItem(item);
                    orderItem.setQuantity(i.getQuantity());
                    orderItem.setOrder(order);
                    orderItems.add(orderItem);
                } else {
                    itemsExist = false;
                }
            }
            if(employeeExists && itemsExist){
                this.employeeRepository.save(employee);
                this.orderRepository.save(order);
                this.orderItemRepository.save(orderItems);
            }

        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public EmployeeOrdersJSONExportDTO exportOrdersByEmployeeAndOrderType(String employeeName, String orderType) {
        EmployeeOrdersJSONExportDTO dto = new EmployeeOrdersJSONExportDTO();
        dto.setEmployeeName(employeeName);
        List<Order> orders = this.orderRepository.exportOrdersByEmployeeAndOrderType(employeeName, OrderType.valueOf(orderType));
        List<OrderJSONExportDTO> orderDtos = new ArrayList<>();
        for (Order o:orders) {
            OrderJSONExportDTO orderDto = new OrderJSONExportDTO();
            orderDto.setCustomer(o.getCustomer());
            List<ItemJSONExportDTO> itemDtos = this.orderItemRepository.getItemDtosByOrderNumber(o.getId());
            orderDto.setItems(itemDtos);
            orderDtos.add(orderDto);
        }
        orderDtos = orderDtos.stream().sorted((x, y) -> {
                BigDecimal totalPriceOfx = BigDecimal.ZERO;
            for (ItemJSONExportDTO o:x.getItems()) {
                totalPriceOfx = totalPriceOfx.add(o.getPrice().multiply(BigDecimal.valueOf(o.getQuantity())));
            }
                BigDecimal totalPriceOfY = BigDecimal.ZERO;
                for (ItemJSONExportDTO e:x.getItems()) {
                    totalPriceOfY = totalPriceOfY.add(e.getPrice().multiply(BigDecimal.valueOf(e.getQuantity())));
            }
            if(totalPriceOfY.compareTo(totalPriceOfx) == 0){
                return y.getItems().size() - x.getItems().size();
            }
            return totalPriceOfY.compareTo(totalPriceOfx);
        }).collect(Collectors.toList());
        dto.setOrders(orderDtos);
        return dto;
    }
}
