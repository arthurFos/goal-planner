package fkal.goalplanner.goalplanner.controller;

import fkal.goalplanner.goalplanner.model.dto.UserDto;
import fkal.goalplanner.goalplanner.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/users")
@RequiredArgsConstructor
@Api(tags = "UserController", value = "users")
public class UserController {

    private final UserService userService;

    @GetMapping
    @ApiOperation("Returns all users (filter)")
    public ResponseEntity<List<UserDto>> getAllUsers() {

        return ResponseEntity.ok(userService.fetchAllUsers());
    }

    @GetMapping("/{id}")
    @ApiOperation("Returns user by its id")
    public ResponseEntity<UserDto> getUserById(
            @ApiParam(value = "userId", required = true)
            @PathVariable("id") String userId) throws NotFoundException {

        return ResponseEntity.ok(userService.fetchOneUser(userId));
    }

    @PostMapping
    @ApiOperation("Create a new user")
    public ResponseEntity<UserDto> createUser(
            @ApiParam(value = "UserDto", required = true) @RequestBody UserDto userDto) {

        return ResponseEntity.ok(userService.createUser(userDto));
    }

    @PatchMapping("/{id}")
    @ApiOperation("Update an existing user")
    public ResponseEntity<UserDto> updateUser(
            @ApiParam(value = "userId", required = true) @PathVariable("id") String userId,
            @ApiParam(value = "User lastName") @RequestParam(required = false) String lastName,
            @ApiParam(value = "User firstName") @RequestParam(required = false) String firstName) throws NotFoundException {

        return ResponseEntity.ok(userService.updateUser(userId, lastName, firstName));
    }
}
