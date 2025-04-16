package lbj.king.auth;

import java.io.ObjectInputFilter.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;
import lbj.king.proyecto.security.jwt.AuthResponse;
import lbj.king.proyecto.security.jwt.LoginRequest;
import lbj.king.proyecto.security.jwt.UserLoginService;

@RestController
@RequestMapping("/api/auth")
public class LoginController {


	
	@Autowired
	private UserLoginService userService;

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(
			@RequestBody LoginRequest loginRequest,
			HttpServletResponse response) {
		
		return userService.login(response, loginRequest);
	}

	@PostMapping("/refresh")
	public ResponseEntity<AuthResponse> refreshToken(
			@CookieValue(name = "RefreshToken", required = false) String refreshToken, HttpServletResponse response) {

		return userService.refresh(response, refreshToken);
	}

	// @PostMapping("/logout")
	// public ResponseEntity<AuthResponse> logOut(HttpServletResponse response) {
	// 	return ResponseEntity.ok(new AuthResponse(Status.SUCCESS, userService.logout(response)));
	// }

}
