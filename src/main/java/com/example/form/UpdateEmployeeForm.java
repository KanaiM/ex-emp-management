package com.example.form;

import jakarta.validation.constraints.NotBlank;

/**
 * 従業員情報更新時に使⽤するフォーム 
 * @author kanaiminami
 */
public class UpdateEmployeeForm {

    private String id;
    @NotBlank(message = "扶養家族がいない場合は0と入力してください")
    private String dependentsCount;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getDependentsCount() {
        return dependentsCount;
    }
    public void setDependentsCount(String dependentsCount) {
        this.dependentsCount = dependentsCount;
    }
    @Override
    public String toString() {
        return "UpdateEmployeeForm [id=" + id + ", dependentsCount=" + dependentsCount + "]";
    }
    
}
