package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 *  管理者情報を操作するサービス 
 * @author kanaiminami
 */

import com.example.domain.Administrator;
import com.example.repository.AdministratorRepository;
@Service
@Transactional
public class AdministratorService {

    @Autowired
    private AdministratorRepository administratorRepository;

    /**
     * 管理者情報を登録する業務処理を⾏う
     * @param administrator
     */
    public void insert(Administrator administrator){
        administratorRepository.insert(administrator);
    }
    /**
     * ログインをする業務処理を⾏う
     * @param mailAddress
     * @param password
     * @return メールアドレスとパスワードから取得した管理者情報
     */
    public Administrator login(String mailAddress, String password){
        return administratorRepository.findByMailAddressAndPassword(mailAddress, password);
    }

}
