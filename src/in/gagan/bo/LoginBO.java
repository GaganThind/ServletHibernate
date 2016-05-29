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
import in.gagan.hibernate.dao.LoginDAO;

public class LoginBO {
	private static Logger logger = LoggingUtil.getLoggerInsance();

	public boolean Authenticate(HttpServletRequest request) {
		String tmpPassword = null;
		String salt = null;
		String userName = null;
		String generatedHash = null;
		String hashFromDatabase = null;
		LoginDAO loginDAO = null;
		Map<String, String> passwordAndSalt = null;

		userName = request.getParameter("userName");
		tmpPassword = request.getParameter("password");
		try {
			loginDAO = new LoginDAO();
			passwordAndSalt = loginDAO.getUserPasswordAndSalt(userName);
			salt = passwordAndSalt.get(ApplicationConstants.SALTS);
			hashFromDatabase = passwordAndSalt.get(ApplicationConstants.HASHES);

			generatedHash = CommonUtil.convertToHashForPasswordAuthentication(salt, tmpPassword);
			if (generatedHash.equals(hashFromDatabase)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			logger.error("LoginBO.Authenticate error: " + e);
		} finally {
			CommonUtil.makeVariablesNull(new Object[] { tmpPassword, userName, loginDAO, passwordAndSalt, generatedHash,
					hashFromDatabase, salt });
		}
		return false;
	}
}
