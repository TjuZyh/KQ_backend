package com.tjulab.checkin;

import com.tjulab.checkin.service.ApplyForVacationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Test0409 {

    @Autowired
    private ApplyForVacationService applyForVacationService;

    @Test
    public void test01(){
        applyForVacationService.queryApplyByEmpId(1).stream().forEach(System.out::println);
    }

    @Test
    public void test02(){
        applyForVacationService.queryApplyByState().stream().forEach(System.out::println);
    }
}
