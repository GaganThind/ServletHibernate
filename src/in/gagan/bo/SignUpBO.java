/*
 * Copyright (C) 2016 Gagandeep Singh Thind
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package in.gagan.bo;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import in.gagan.common.constants.ApplicationConstants;
import in.gagan.common.util.CommonUtil;
import in.gagan.common.util.LoggingUtil;
import in.gagan.hibernate.dao.SignUpDAO;

public class SignUpBO {
	private static Logger logger = LoggingUtil.getLoggerInsance();

	public boolean Register(HttpServletRequest request) {
		String userName = null;
		String firstName = null;
		String lastName = null;
		String dob = null;
		String phoneNumber = null;
		String tmpPassword = null;
		String salt = null;
		String passwordHash = null;

		SignUpDAO signUpDAO = new SignUpDAO();
		Map<String, String> passwordMap = null;

		userName = request.getParameter("userName");
		firstName = request.getParameter("firstName");
		lastName = request.getParameter("lastName");
		dob = request.getParameter("dob");
		phoneNumber = request.getParameter("phoneNumber");
		tmpPassword = request.getParameter("password");
		try {
			passwordMap = CommonUtil.convertToHash(tmpPassword);
			salt = passwordMap.get(ApplicationConstants.SALTS);
			passwordHash = passwordMap.get(ApplicationConstants.HASHES);
			return signUpDAO.Register(userName, firstName, lastName, dob, phoneNumber, passwordHash, salt);
		} catch (Exception e) {
			logger.error("SignUpBO.Register error: " + e);
		} finally {
			CommonUtil.makeVariablesNull(new Object[] { userName, firstName, lastName, dob, phoneNumber, tmpPassword,
					salt, passwordMap, passwordHash });
		}
		return false;
	}
}
