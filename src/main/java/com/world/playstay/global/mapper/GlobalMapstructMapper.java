package com.world.playstay.global.mapper;

/**
 * Mapstruct : Entity 와 Dto간의 매핑 지원하는 라이브러리. 컴파일 시점에 매핑 클래스를 생성한다.
 * build 하면 @Mapper가 붙은 interface에 대한 구현체가 자동으로 생성된다.
 */

public interface GlobalMapstructMapper<E, D> {

  E toEntity(D dto);

  D toDto(E entity);
}
