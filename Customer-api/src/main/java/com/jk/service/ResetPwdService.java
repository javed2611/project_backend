package com.jk.service;

import com.jk.dto.ResetPwdDTO;

public interface ResetPwdService {
	public String resetPwd(ResetPwdDTO resetPwdDTO, String email);
}
