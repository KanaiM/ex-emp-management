package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 *  管理者情報を登録する業務処理を⾏うメソッドを作成する 
 * @author kanaiminami
 */

import com.example.domain.Administrator;
import com.example.repository.AdministratorRepository;
@Service
@Transactional
public class AdministratorService {

    @Autowired
    private AdministratorRepository administratorRepository;

    public void insert(Administrator administrator){
        administratorRepository.insert(administrator);
    }

    public Administrator findByMailAddressAndPassword(String mailAddress, String password){
        return administratorRepository.findByMailAddressAndPassword(mailAddress, password);
    }
}
