package com.hz.blog.mapper;

import com.hz.blog.entity.Tag;
import com.hz.blog.vo.TagVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TagMapper {
    TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);

    Tag toDTO(TagVo tagVo);
}
