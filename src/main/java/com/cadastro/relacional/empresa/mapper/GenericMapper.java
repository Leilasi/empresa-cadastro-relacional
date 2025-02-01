package com.cadastro.relacional.empresa.mapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GenericMapper {
    <T> T entidadeParaDTO(Object entidade, Class<T> dto);
    <T> T dtoParaEntidade(Object dto, Class<T> entidade);
    <T> List<T> entidadeParaDTO(List<?> entidades, Class<T> dto);
    <T> Page<T> entidadeParaDTO(Page<?> entidades, Class<T> dto, Pageable pageable);

}
