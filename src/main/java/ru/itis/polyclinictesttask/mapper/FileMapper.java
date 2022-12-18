package ru.itis.polyclinictesttask.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import ru.itis.polyclinictesttask.dto.response.FileInfoResponse;
import ru.itis.polyclinictesttask.dto.response.FileResponse;
import ru.itis.polyclinictesttask.model.FileEntity;
import ru.itis.polyclinictesttask.model.FileStorageEntity;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface FileMapper {

    FileResponse toResponse(FileStorageEntity file);

    @Mapping(target = "type", source = "mimeType")
    @Mapping(target = "name", source = "originalFileName")
    FileInfoResponse toInfoResponse(FileEntity file);

    @Mapping(target = "type", source = "mimeType")
    @Mapping(target = "name", source = "originalFileName")
    FileResponse toResponse(FileEntity file);
}