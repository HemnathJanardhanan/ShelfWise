package com.shelfwise.backend.modules.LibraryPolicy;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/policy")
public class LibraryPolicyController {
    private LibraryPolicyService libraryPolicyService;

    public LibraryPolicyController(LibraryPolicyService libraryPolicyService) {
        this.libraryPolicyService = libraryPolicyService;
    }

    public ResponseEntity<LibraryPolicyDto> createPolicy(@RequestBody LibraryPolicyRequest req) {
        LibraryPolicyDto dto=libraryPolicyService.makePolicy(req);
        return  ResponseEntity.ok(dto);
    }

}
