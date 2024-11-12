package com.example.repository;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Administrator;
/**
 * administerators テーブルを操作するリポジトリ(Dao)
 * @author kanaiminami
 */
@Repository
public class AdministratorRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    /**
     * Administratorオブジェクトの⽣成(ラムダ式)
     * @return administrator
     */
    private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER = (rs,i) ->{
        Administrator administrator = new Administrator();
        administrator.setId(rs.getInt("id"));
        administrator.setName(rs.getString("name"));
        administrator.setMailAddress(rs.getString("mail_address"));
        administrator.setPassword(rs.getString("password"));
        return administrator;
    };

    /**
     * 管理者情報を挿⼊する
     * @param administrator
     */
    public void insert(Administrator administrator){
        SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);

        String insertSql = "INSERT INTO administrators(id, name, mail_address, password)"
            + "VALUES(:id, :name, :mailAddress, :password);";
        template.update(insertSql, param);
    }

    /**
     * メールアドレスとパスワードから管理者情報を取得する(1件も存在しない場合はnullを返す※)
     * @param mailAddress
     * @param password
     * @return List<Administrator>
     */
    public Administrator findByMailAddressAndPassword(String mailAddress, String password){
        String sql = "SELECT id, name, mail_address, password FROM administrators"
            +"WHERE mail_address=:mailAddress AND password=:password;";

        SqlParameterSource param = new MapSqlParameterSource();

        List<Administrator> administratorList 
            = template.query(sql, param, ADMINISTRATOR_ROW_MAPPER); 
        if (administratorList.size() == 0) { 
            return null; 
        } return administratorList.get(0);
    }
}