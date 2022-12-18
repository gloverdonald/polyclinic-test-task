package ru.itis.polyclinictesttask.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import ru.itis.polyclinictesttask.dto.request.AccountRequest;
import ru.itis.polyclinictesttask.dto.request.SignUpRequest;
import ru.itis.polyclinictesttask.dto.response.AccountResponse;
import ru.itis.polyclinictesttask.model.AccountEntity;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface AccountMapper {

    @Mapping(target = "avatarId", source = "avatar.id")
    @Mapping(target = "type", source = "accountType")
    @Mapping(target = "phone", source = "phoneNumber")
    AccountResponse toResponse(AccountEntity accountEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "accountType", source = "type")
    AccountEntity toEntity(SignUpRequest signUpRequest);

    void update(@MappingTarget AccountEntity accountEntity, AccountRequest accountRequest);
}