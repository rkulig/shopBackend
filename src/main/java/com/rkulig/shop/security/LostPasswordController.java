package com.rkulig.shop.security;

import com.rkulig.shop.security.model.User;
import com.rkulig.shop.security.model.dto.ChangePassword;
import com.rkulig.shop.security.model.dto.EmailObject;
import com.rkulig.shop.security.repository.UserRepository;
import com.rkulig.shop.security.service.LostPasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LostPasswordController {

    private final LostPasswordService lostPasswordService;

    @PostMapping("/lostPassword")
    public void lostPassword(@RequestBody EmailObject emailObject) {
        lostPasswordService.sendLostPasswordLink(emailObject);
    }

    @PostMapping("/changePassword")
    public void changePassword(@RequestBody ChangePassword changePassword) {
        lostPasswordService.changePassword(changePassword);
    }


}
