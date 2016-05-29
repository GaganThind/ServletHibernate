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
package in.gagan.hibernate.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import in.gagan.common.util.CommonUtil;
import in.gagan.common.util.HibernateUtil;
import in.gagan.common.util.LoggingUtil;
import in.gagan.hibernate.dto.UserDetails;
import in.gagan.hibernate.dto.UserLogin;

public class SignUpDAO {
	private static Logger logger = LoggingUtil.getLoggerInsance();

	public boolean Register(String userName, String firstName, String lastName, String dob, String phoneNumber,
			String passwordHash, String salt) {
		Session session = null;
		UserDetails userDetails = null;
		UserLogin userLogin = null;
		boolean result = false;
		try {
			userDetails = new UserDetails();
			userLogin = new UserLogin();
			session = HibernateUtil.openSession();
			session.beginTransaction();

			userDetails.setUserName(userName);
			userDetails.setFirstName(firstName);
			userDetails.setLastName(lastName);
			userDetails.setDob(dob);
			userDetails.setPhoneNumber(phoneNumber);

			userLogin.setPassword(passwordHash);
			userLogin.setUserName(userName);
			userLogin.setSalt(salt);
			userLogin.setUserDetails(userDetails);

			userDetails.setUserLogin(userLogin);
			// userDetails.setPermanentAddress(permanentAddress);
			// userDetails.setTemporaryAddress(temporaryAddress);

			session = HibernateUtil.openSession();
			session.beginTransaction();
			session.save(userDetails);
			HibernateUtil.commitTransaction(session);
			result = true;

		} catch (Exception e) {
			logger.error("SignUpDAO.Register error while registering user " + e);
			HibernateUtil.rollBackTransaction(session);
		} finally {
			HibernateUtil.closeSession(session);
			CommonUtil.makeVariablesNull(new Object[] { dob, firstName, lastName, passwordHash, phoneNumber, result,
					salt, userDetails, userLogin, userName });
		}
		return result;
	}
}
