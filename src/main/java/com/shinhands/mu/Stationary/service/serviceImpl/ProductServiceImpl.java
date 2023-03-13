package com.shinhands.mu.Stationary.service.serviceImpl;

import com.shinhands.mu.Stationary.dto.CartProductApiDTO;
import com.shinhands.mu.Stationary.dto.ProductDTO;
import com.shinhands.mu.Stationary.entity.Product;
import com.shinhands.mu.Stationary.repository.ProductRepoMybatis;
import com.shinhands.mu.Stationary.repository.ProductRepository;
import com.shinhands.mu.Stationary.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Resource
    ProductRepoMybatis productRepoMybatis;
    @Autowired
    private ModelMapper mapper;

    @Override
    public List<ProductDTO> getAllProducts() {
//        return mapper.map(productRepository.findAllByDeletedEquals(0L), new TypeToken<List<ProductDTO>>(){}.getType());
        return productRepository.findAllProducts();
    }

    @Override
    public ProductDTO addProduct(ProductDTO productDTO) {
        Product product = mapper.map(productDTO, Product.class);
        product.setDeleted(0L);
        return mapper.map(productRepository.save(product), ProductDTO.class);
    }

    @Override
    public Boolean deleteProduct(long id) {

        Product oldProduct = productRepository.findByIdEqualsAndDeletedEquals(id,0L);
        if (oldProduct!=null){
            oldProduct.setDeleted(1L);
            productRepository.save(oldProduct);
            return true;
        } else return false;
    }

    @Override
    public ProductDTO getProductById(long id) {

        ProductDTO oldProduct = productRepository.findProductById(id);
        if (oldProduct!=null)
            return oldProduct;
        else return null;
    }

    @Override
    public Boolean updateProduct(long id, ProductDTO productDTO) {
        Product oldProduct=productRepository.findByIdEqualsAndDeletedEquals(id,0L);
        if(oldProduct==null)
        {
            return false;
        }
        else
        {
            productRepository.save(mapper.map(productDTO,Product.class));
        }
        return true;
    }

    @Override
    public List<CartProductApiDTO> getApiCartProduct(long id) {
        return productRepoMybatis.getAPI(id);
    }

    @Override
    public List<CartProductApiDTO> getProductFetch(long offset) {
        return productRepoMybatis.getProductFetch((offset-1)*3);
    }

    @Override
    public int countProduct() {
        return productRepoMybatis.countProduct();
    }
}
