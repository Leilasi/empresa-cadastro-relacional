package com.cadastro.relacional.empresa.mapper.impl;

import com.cadastro.relacional.empresa.mapper.GenericMapper;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public <T> Page<T> entidadeParaDTO(Page<?> entidades, Class<T> dto, Pageable pageable) {
        List<T> dtos = entidades.getContent()
                .stream()
                .map(entidade -> modelMapper.map(entidade, dto))
                .collect(Collectors.toList());

        return new PageImpl<>(dtos, pageable, entidades.getTotalElements());
    }
}
