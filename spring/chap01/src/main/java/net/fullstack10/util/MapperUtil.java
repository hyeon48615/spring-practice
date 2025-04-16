package net.fullstack10.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public enum MapperUtil {
    INSTANCE;

    private ModelMapper modelMapper;    // DTO <-> Entity 변환에 사용되는 라이브러리의 핵심 클래스

    MapperUtil() {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)                                                  // getter/setter 가 없어도, 필드이름이 같으면 매핑 시도
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)  // setFieldMatchingEnabled(true) 와 함께 사용, private 필드까지 접근 허용
                .setMatchingStrategy(MatchingStrategies.STRICT);                                // 필드명을 기준으로 매핑 시, 매칭 강도 설정
                                                                                                // STRICT: 이름이 완전히 같을 때, STANDARD: 접두사 접미사가 달라도 매핑(기본값), LOOSE: 조금만 비슷해도 매핑
    }

    public ModelMapper get() {
        return modelMapper;
    }
}

/*
    ModelMapper 주요 설정
    - setSkipNullEnabled(true) : null 값인 필드는 매핑 시 제외
    - setAmbiguityIgnored(true) : 필드 이름이 중복될 때 예외 발생 안하고 무시
    - addMappings() : 커스텀 매핑 설정
    - addConverter() : 타입 간 변환 로직 직접 정의
    - validate() : 매핑이 유효한지 검증해서 예외 발생
 */
