package com.shinhands.mu.Stationary.service.serviceImpl;

import com.shinhands.mu.Stationary.repository.StatisticRepository;
import com.shinhands.mu.Stationary.service.StatisticService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StatisticServiceImpl implements StatisticService {
    @Resource
    StatisticRepository statisticRepository;
    @Override
    public int countTotalBill() {
        return statisticRepository.countTotalBill();
    }

    @Override
    public int countBill() {
        return statisticRepository.countBill();
    }

    @Override
    public int countOrder() {
        return statisticRepository.countOrder();
    }

    @Override
    public int countUser() {
        return statisticRepository.countUser();
    }
}
