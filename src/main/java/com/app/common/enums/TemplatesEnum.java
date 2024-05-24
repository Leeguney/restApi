
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

    CONTROLLER  ("controller"  , "Controller"   , "templates/vm/controller.vm"),
    SERVICE     ("service"     , "Service"      , "templates/vm/service.vm"),
    IMPLEMENT   ("serviceimpl" , "ServiceImpl"  , "templates/vm/implement.vm"),
    REPOSITORY  ("repository"  , "Mapper"       , "templates/vm/repository.vm"),
    DTO         ("dto"         , "DTO"          , "templates/vm/dto.vm");

    @Getter
    private String folderNm;
    
    @Getter
    private String fileNm;
    
    @Getter
    private String vmPath;
    
}
