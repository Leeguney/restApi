
package com.app.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 패키지 생성에 필요한 데이터 저장 이너엄!
 * 
 * @author guney
 * @date 2024. 5. 24.
 */
@AllArgsConstructor
public enum TemplatesEnum {

    CONTROLLER  ("controller"  , "templates/vm/controller.vm"),
    SERVICE     ("service"     , "templates/vm/service.vm"),
    IMPLEMENT   ("serviceimpl" , "templates/vm/implement.vm"),
    REPOSITORY  ("repository"  , "templates/vm/repository.vm"),
    DTO         ("dto"         , "templates/vm/dto.vm");

    @Getter
    private String tplName;
    
    @Getter
    private String tplPath;
    
}
