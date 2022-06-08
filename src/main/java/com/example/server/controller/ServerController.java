package com.example.server.controller;

import com.example.server.enumeration.Status;
import com.example.server.model.Server;
import com.example.server.model.ServerResponse;
import com.example.server.service.ServerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Map;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.util.MimeTypeUtils.IMAGE_PNG_VALUE;


@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
public class ServerController {
    private final ServerServiceImpl serverService;

    @GetMapping("/list")
    public ResponseEntity<ServerResponse> getServers() {
        return ResponseEntity.ok(
                ServerResponse.builder()
                        .timeStamp(now())
                        .data(Map.of("servers", serverService.list(30)))
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
    @GetMapping("/ping/{ipAddress}")
    public ResponseEntity<ServerResponse> getPingServer(@PathVariable("ipAddress") String ipAddress) throws IOException {
        Server server = serverService.ping(ipAddress);
        return ResponseEntity.ok(
                ServerResponse.builder()
                        .timeStamp(now())
                        .data(Map.of("servers", serverService.list(30)))
                        .message(server.getStatus() == Status.SERVER_UP ? "Ping success" : "Ping failed")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
    @PostMapping("/save")
    public ResponseEntity<ServerResponse> getPingServer(@RequestBody @Valid Server server) {
        return ResponseEntity.ok(
                ServerResponse.builder()
                        .timeStamp(now())
                        .data(Map.of("server", serverService.create(server)))
                        .message("Server created")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<ServerResponse> getPingServer(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                ServerResponse.builder()
                        .timeStamp(now())
                        .data(Map.of("server", serverService.get(id)))
                        .message("Server retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ServerResponse> getDeletedServer(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                ServerResponse.builder()
                        .timeStamp(now())
                        .data(Map.of("deleted", serverService.delete(id)))
                        .message("Server deleted")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
    @GetMapping(path = "/image/{fileName}", produces = IMAGE_PNG_VALUE)
    public byte[] getServerImage(@PathVariable("fileName") String fileName) throws IOException {
        return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "İndirilenler/images/" + fileName));
    }
}
