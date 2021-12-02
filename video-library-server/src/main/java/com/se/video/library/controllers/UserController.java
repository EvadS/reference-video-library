package com.se.video.library.controllers;


import com.se.video.library.config.constant.ControllerConstants;
import com.se.video.library.payload.request.CountryRequest;
import com.se.video.library.payload.request.UserRequest;
import com.se.video.library.payload.response.CountryResponse;
import com.se.video.library.payload.response.UserResponse;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {

    Set<UserResponse> userSet = new HashSet<>();

    @GetMapping("/profile/{id}")
    public ResponseEntity<UserResponse> profile(
            @Parameter(description = "unique identifier to be searched", required = true)
            @PathVariable(name = "id" )final Long id){

        return null;
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserResponse>> list(){

        List<UserResponse> userResponses = new ArrayList<>();

        UserResponse userResponse = new UserResponse(1l, "name", "user name", "mail@mail.com");
        userResponses.add(userResponse);

        userResponse = new UserResponse(2l, "name2", "user name2", "mail2@mail.com");
        userResponses.add(userResponse);

        userResponse = new UserResponse(3l, "name3", "user name3", "mail3@mail.com");
        userResponses.add(userResponse);

        userResponse = new UserResponse(3l, "name4", "user name4", "mail4@mail.com");
        userResponses.add(userResponse);

        return ResponseEntity.status(HttpStatus.OK).body(userResponses);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<UserResponse> update(
            @Parameter(description = "unique identifier to be searched", required = true)
            @PathVariable(name = "id" )final Long id,
            @Parameter(description = "user model")
            @RequestBody @Valid UserRequest userRequest) {
        UserResponse  userResponse = new UserResponse(3l, "name4", "user name4", "mail4@mail.com");

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity delete(
            @Parameter(description = "unique identifier to be searched", required = true)
            @PathVariable(name = "id" )final Long id) {
        //countryService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    /*

     @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getName());
        return userSummary;
    }

    @GetMapping("/user/checkUsernameAvailability")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
        Boolean isAvailable = !userRepository.existsByUsername(username);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/user/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
        Boolean isAvailable = !userRepository.existsByEmail(email);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/users/{username}")
    public UserProfile getUserProfile(@PathVariable(value = "username") String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        long pollCount = pollRepository.countByCreatedBy(user.getId());
        long voteCount = voteRepository.countByUserId(user.getId());

        UserProfile userProfile = new UserProfile(user.getId(), user.getUsername(), user.getName(), user.getCreatedAt(), pollCount, voteCount);

        return userProfile;
    }

    @GetMapping("/users/{username}/polls")
    public PagedResponse<PollResponse> getPollsCreatedBy(@PathVariable(value = "username") String username,
                                                         @CurrentUser UserPrincipal currentUser,
                                                         @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                         @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return pollService.getPollsCreatedBy(username, currentUser, page, size);
    }

    @GetMapping("/users/{username}/votes")
    public PagedResponse<PollResponse> getPollsVotedBy(@PathVariable(value = "username") String username,
                                                       @CurrentUser UserPrincipal currentUser,
                                                       @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                       @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return pollService.getPollsVotedBy(username, currentUser, page, size);
    }
     */
}
