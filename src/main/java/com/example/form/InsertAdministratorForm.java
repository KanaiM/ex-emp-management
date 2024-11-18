package com.example.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * 管理者情報登録時に使⽤するフォーム 
 * @author kanaiminami
 */
public class InsertAdministratorForm {
    @NotBlank(message = "必須入力です")
    private String name;
    @NotBlank(message = "必須入力です")
    @Email(message = "メールアドレスの形式が不正です")
    private String mailAddress;
    @NotBlank(message = "必須入力です")
    @Size(min=3, max=127, message="3文字以上127文字以内で記載してください")
    private String password;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getMailAddress() {
        return mailAddress;
    }
    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Administrator [name=" + name + ", mailAddress=" + mailAddress + ", password=" + password
                + "]";
    }
}
