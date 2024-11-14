package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.form.InsertAdministratorForm;
import com.example.form.LoginForm;
import com.example.service.AdministratorService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.domain.Administrator;

/**
 * 管理者登録画⾯を表⽰する処理を記述する
 * @author kanaiminami
 */

@Controller
@RequestMapping("/")
public class AdministratorController {

    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private HttpSession session;

    @ModelAttribute
    private InsertAdministratorForm setUpForm(){
        return new InsertAdministratorForm();
    }

    /**
     * 登録フォームにフォワードする
     * @param form
     * @return　登録フォーム
     */
    @GetMapping("/toInsert")
    public String toInsert(InsertAdministratorForm form) {
        return "administrator/insert";
    }

    /**
     * 管理者情報を登録する
     * @param form
     * @return ログイン画⾯
     */
    @PostMapping("/insert")
    public String insert(InsertAdministratorForm form) {
        Administrator administrator = new Administrator();
        BeanUtils.copyProperties(form, administrator);
        administratorService.insert(administrator);
        return "redirect:/";
    }
    
    /**
     * ログイン画⾯にフォワードする
     * @param form
     * @return　ログイン画⾯
     */
    @GetMapping("/")
    public String toLogin(LoginForm form) {
        return "administrator/login";
    }

    /**
     * ログイン時の処理をする。
     * @param form
     * @param model
     * @return
     * 未登録の情報の際「メールアドレスまたはパスワードが不正です。」というエラーメッセージを表示したログイン画⾯にフォワードする。 
     * ログイン成功した際はる従業員情報⼀覧ページにリダイレクトする
     */
    @PostMapping("/login")
    public String login(LoginForm form, Model model) {
        Administrator date = administratorService.login(form.getMailAddress(), form.getPassword());
        if(date == null){
            model.addAttribute("message", "メールアドレスまたはパスワードが不正です。");
            return "administrator/login";
        }
        session.setAttribute("administratorName", date);
        return "redirect:/employee/showList";
    }

    /**
    * ログアウトをする
    * @param form
    * @return ログイン画⾯ 
    */
    @GetMapping("/logout")
    public String logout(LoginForm form) {
        session.invalidate();
        return "redirect:/";
    }
}
