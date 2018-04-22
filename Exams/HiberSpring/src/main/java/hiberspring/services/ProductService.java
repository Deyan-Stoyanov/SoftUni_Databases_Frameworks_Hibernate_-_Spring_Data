package hiberspring.services;

import hiberspring.dtos.imports.product.ProductImportFromXmlDto;
import hiberspring.dtos.imports.product.ProductsImportFromXmlDto;
import hiberspring.entities.Product;

public interface ProductService {

    void createOne(ProductsImportFromXmlDto productsImportFromXmlDto);

}
