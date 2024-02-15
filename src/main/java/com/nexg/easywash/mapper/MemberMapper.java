package com.nexg.easywash.mapper;

import com.nexg.easywash.dto.Member;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

@Mapper
public interface MemberMapper {


    Member selectUser(@Param("email") String email);

    void updateLogindttm(@Param("email") String email);

    void insertUser(@Param("email") String email);
}
