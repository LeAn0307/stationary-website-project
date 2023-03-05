package com.shinhands.mu.Stationary.repository;

import com.shinhands.mu.Stationary.entity.Rating;
import com.shinhands.mu.Stationary.entity.RatingReview;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface RatingRepositoryMybatis {
List<Rating> findAllByDeletedEquals(Long deleted);
Rating findByIdEqualsAndDeletedEquals(Long id,Long deleted);
    List<Rating>findByProductIdEqualsAndDeletedEquals(Long productId,Long deleted);
    @Select("select r.id, r.comment_product, r.ratescore, r.iduser, r.product_id, uw.user_name\n" +
            "from rating r\n" +
            "join user_website uw on r.iduser = uw.id\n" +
            "where r.deleted = 0 and r.product_id = #{productId}\n" )
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "comment", column = "comment_product"),
            @Result(property = "rateScore", column = "ratescore"),
            @Result(property = "productId", column = "product_id"),
            @Result(property = "userId", column = "iduser"),
            @Result(property = "userName", column = "user_name")
    })
   public  List<RatingReview> getAllRatingByProductIdAndUserId(Long productId);
}
