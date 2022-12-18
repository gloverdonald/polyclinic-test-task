package ru.itis.polyclinictesttask.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.polyclinictesttask.dto.response.FileResponse;

import java.util.UUID;

@RequestMapping("/api/v1/files")
public interface FileApi {

    @Operation(summary = "Upload new file")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(type = "Long", format = "int64")))})
    @PostMapping
    ResponseEntity<UUID> uploadFile(@RequestBody MultipartFile file);

    @Operation(summary = "Getting information about a file")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "File",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = FileResponse.class))})})
    @GetMapping("/{id}/info")
    ResponseEntity<FileResponse> getFileInfo(@PathVariable("id") UUID id);

    @Operation(summary = "Getting a file")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "File bytes")})
    @GetMapping(value = "/{id}")
    ResponseEntity<byte[]> showFile(@PathVariable("id") UUID id);
}