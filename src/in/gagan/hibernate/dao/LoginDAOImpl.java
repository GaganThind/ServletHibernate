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

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import in.gagan.common.constants.ApplicationConstants;
import in.gagan.common.util.CommonUtil;
import in.gagan.common.util.HibernateUtil;
import in.gagan.common.util.LoggingUtil;
import in.gagan.hibernate.dto.UserLogin;

public class LoginDAOImpl implements LoginDAO {
	private static Logger logger = LoggingUtil.getLoggerInsance();
	
	@Override
	public Map<String,String> getUserPasswordAndSalt(String userName) {
		Session session = null;
		Object[] tmpData = null;
		Map<String,String> auth = null;
		List<UserLogin> passwordAndSaltToCheck = null;
		try{
			session = HibernateUtil.openSession();
			session.beginTransaction();
			Query query = session.getNamedQuery("UserLogin.authenticateByName");
			query.setString(0, userName);
			passwordAndSaltToCheck = (List<UserLogin>)query.list();
			Iterator itr = passwordAndSaltToCheck.iterator();
			if(itr.hasNext()){
				tmpData = (Object[]) itr.next();
				auth = new HashMap<String,String>();
				auth.put(ApplicationConstants.HASHES, tmpData[0].toString());
				auth.put(ApplicationConstants.SALTS, tmpData[1].toString());
			}			
			HibernateUtil.commitTransaction(session);
			return auth;
		}catch(Exception e){
			logger.error("LoginDAO.Authentication error. Cannot authenticate user" + e);
			HibernateUtil.rollBackTransaction(session);
		}finally {
			HibernateUtil.closeSession(session);	
			CommonUtil.makeVariablesNull(new Object[]{userName,auth});
		}
		return null;		
	}
}
