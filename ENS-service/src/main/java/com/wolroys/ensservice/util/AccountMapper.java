package com.wolroys.ensservice.util;

import com.wolroys.ensservice.entity.Account;
import com.wolroys.ensservice.entity.AccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    AccountDto toDto(Account account);
}
