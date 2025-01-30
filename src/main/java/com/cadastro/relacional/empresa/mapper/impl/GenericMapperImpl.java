package com.cadastro.relacional.empresa.mapper.impl;

import com.cadastro.relacional.empresa.mapper.GenericMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GenericMapperImpl implements GenericMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public <T> T entidadeParaDTO(Object entidade, Class<T> dto) {
        return modelMapper.map(entidade, dto);
    }

    @Override
    public <T> T dtoParaEntidade(Object dto, Class<T> entidade) {
        return modelMapper.map(dto, entidade);
    }

    @Override
    public <T> List<T> entidadeParaDTO(List<?> entidades, Class<T> dto) {
        List<T> dtos = new ArrayList<>();
        for (Object entidade : entidades) {
            dtos.add(entidadeParaDTO(entidade, dto));
        }
        return dtos;
    }
}
