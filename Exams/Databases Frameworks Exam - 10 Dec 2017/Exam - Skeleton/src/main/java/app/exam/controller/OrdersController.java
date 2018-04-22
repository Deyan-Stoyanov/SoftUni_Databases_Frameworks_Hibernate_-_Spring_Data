package app.exam.controller;

import app.exam.domain.dto.xml.OrderWrapperXMLImportDTO;
import app.exam.domain.dto.xml.OrderXMLImportDTO;
import app.exam.parser.interfaces.Parser;
import app.exam.service.api.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.text.ParseException;

@Controller
public class OrdersController {

    private final Parser xmlParser;
    private final Parser jsonParser;
    private final OrderService orderService;

    @Autowired
    public OrdersController(@Qualifier(value = "XMLParser") Parser xmlParser, @Qualifier(value = "JSONParser") Parser jsonParser, OrderService orderService) {
        this.xmlParser = xmlParser;
        this.jsonParser = jsonParser;
        this.orderService = orderService;
    }

    public String importDataFromXML(String xmlContent) {
        StringBuilder sb = new StringBuilder();
        try {
            OrderWrapperXMLImportDTO wrapper = this.xmlParser.read(OrderWrapperXMLImportDTO.class, xmlContent);
            for (OrderXMLImportDTO dto:wrapper.getOrders()) {
                try {
                    this.orderService.create(dto);
                    sb.append(String.format("Order for %s on %s added.%n",dto.getCustomer(), dto.getDate()));
                } catch (ParseException | IllegalArgumentException ie) {
                    sb.append("Error: Invalid data.").append(System.lineSeparator());
                }
            }
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public String exportOrdersByEmployeeAndOrderType(String employeeName, String orderType) {
        try {
            return this.jsonParser.write(this.orderService.exportOrdersByEmployeeAndOrderType(employeeName, orderType));
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
