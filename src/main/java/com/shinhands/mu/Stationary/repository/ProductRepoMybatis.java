package com.shinhands.mu.Stationary.repository;

import com.shinhands.mu.Stationary.dto.CartProductApiDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductRepoMybatis {
    @Select("select P.NAME,C.QUANTITY, P.PRICE, P.IMAGE FROM PRODUCT P, cart_product C WHERE C.CART_ID=#{id} AND C.PRODUCT_ID=P.ID AND C.DELETED=0 AND P.DELETED=0 ")
    @Results(value ={
            @Result(property = "name", column = "name"),
            @Result(property = "quantity", column = "quantity"),
            @Result(property = "price", column = "price"),
            @Result(property = "image", column = "image")
    })
    List<CartProductApiDTO> getAPI(long id);

    @Select("select name, price, avgrating, discount, image from product\n" +
            "where deleted=0\n" +
            "order by name\n" +
            "offset #{offset} rows\n" +
            "fetch next 3 rows only")
    @Results(value ={
            @Result(property = "name", column = "name"),
            @Result(property = "price", column = "price"),
            @Result(property = "image", column = "image"),
            @Result(property = "discount", column = "discount")
    })
    List<CartProductApiDTO> getProductFetch(long offset);

    @Select("select count(1) from product")
    int countProduct();
}


