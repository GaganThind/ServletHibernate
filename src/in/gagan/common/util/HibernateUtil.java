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
package in.gagan.common.util;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static Logger logger = LoggingUtil.getLoggerInsance();
	private static SessionFactory sessionFactory;
	/*
	 * static { try { sessionFactory = new
	 * Configuration().configure().buildSessionFactory(); } catch (Throwable ex)
	 * { logger.error("Initial SessionFactory creation failed." + ex); throw new
	 * ExceptionInInitializerError(ex); } }
	 */

	public static synchronized void openSessionFactory() {
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			logger.error("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static Session openSession() {
		return sessionFactory.openSession();
	}

	public static void commitTransaction(Session session) {
		session.getTransaction().commit();
	}

	public static void rollBackTransaction(Session session) {
		session.getTransaction().rollback();
	}

	public static void closeSession(Session session) {
		session.close();
	}

	public static void closeSessionFactory(){
		try{
		if(sessionFactory!=null){
		sessionFactory.close();
		}
		}catch(Exception e){
			logger.error("Failed to close session Factory object: "+ e);
		}
	}
}
