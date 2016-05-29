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
package in.gagan.servlet.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;

import in.gagan.common.util.HibernateUtil;
import in.gagan.common.util.LoggingUtil;

/**
 * Application Lifecycle Listener implementation class ServletContextApplication
 *
 */
@WebListener
public class ServletContextApplication implements ServletContextListener {
	private static Logger logger = LoggingUtil.getLoggerInsance();
    /**
     * Default constructor. 
     */
    public ServletContextApplication() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         try{
        	 HibernateUtil.closeSessionFactory();
         }catch(Exception e){
        	 logger.error("ServLetContextApplication.contextDestroyed error: "+e);
         }
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
    	try{
       	 HibernateUtil.openSessionFactory();
        }catch(Exception e){
       	 logger.error("ServLetContextApplication.contextInitialized error: "+e);
        }
    }
	
}
