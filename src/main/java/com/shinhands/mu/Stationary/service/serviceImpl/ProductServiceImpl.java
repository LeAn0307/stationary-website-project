package com.shinhands.mu.Stationary.service.serviceImpl;

import com.shinhands.mu.Stationary.dto.AccountDTO;
import com.shinhands.mu.Stationary.dto.CategoryDTO;
import com.shinhands.mu.Stationary.dto.CouponDTO;
import com.shinhands.mu.Stationary.dto.ProductDTO;
import com.shinhands.mu.Stationary.entity.Coupon;
import com.shinhands.mu.Stationary.entity.Product;
import com.shinhands.mu.Stationary.repository.ProductRepository;
import com.shinhands.mu.Stationary.service.ProductService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<ProductDTO> getAllProducts() {
        return mapper.map(productRepository.findAllByDeletedEquals(0L), new TypeToken<List<ProductDTO>>(){}.getType());
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

        Product oldProduct=productRepository.findByIdEqualsAndDeletedEquals(id,0L);
        if (oldProduct!=null)
            return mapper.map(oldProduct, ProductDTO.class);
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
}
