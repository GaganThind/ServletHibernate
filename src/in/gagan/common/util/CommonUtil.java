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

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.bouncycastle.crypto.generators.PKCS5S2ParametersGenerator;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.encoders.Base64;

import in.gagan.common.constants.ApplicationConstants;

public class CommonUtil {
	private static Logger logger = LoggingUtil.getLoggerInsance();

	public static boolean checkIfNotNull(HttpServletRequest request, String fields) {
		String[] field = fields.split(",");
		for (int i = 0; i < field.length; i++) {
			String tmp = request.getParameter(field[i]);
			if (tmp.isEmpty() || tmp == null) {
				return false;
			}
		}
		return true;
	}

	public static void makeVariablesNull(Object[] fields) {
		for (Object obj : fields) {
			if (obj instanceof Integer) {
				obj = 0;
			} else if (obj instanceof Boolean) {
				obj = false;
			} else {
				obj = null;
			}
		}
	}

	public static Map<String, String> convertToHash(String passwordToSave) {
		// tuning parameters

		// these sizes are relatively arbitrary
		int seedBytes = 20;
		int hashBytes = 20;
		SecureRandom rng = null;
		byte[] tmpSalt = null;
		PKCS5S2ParametersGenerator kdf = null;
		byte[] tmpHash = null;
		String hash = null;
		String salt = null;

		// increase iterations as high as your performance can tolerate
		// since this increases computational cost of password guessing
		// which should help security
		int iterations = 1000;

		Map<String, String> hashedMap = new HashMap<String, String>();

		// to save a new password:
		try {
			rng = new SecureRandom();
			tmpSalt = rng.generateSeed(seedBytes);

			kdf = new PKCS5S2ParametersGenerator();
			kdf.init(passwordToSave.getBytes("UTF-8"), tmpSalt, iterations);

			tmpHash = ((KeyParameter) kdf.generateDerivedMacParameters(8 * hashBytes)).getKey();
			hash = new String(Base64.encode(tmpHash));
			salt = new String(Base64.encode(tmpSalt));
			hashedMap.put(ApplicationConstants.SALTS, salt);
			hashedMap.put(ApplicationConstants.HASHES, hash);
			return hashedMap;
		} catch (Exception e) {
			logger.error("CommonUtil.convert hashing failed " + e);
		} finally {
			makeVariablesNull(
					new Object[] { tmpHash, hash, seedBytes, hashBytes, rng, tmpSalt, kdf, salt, iterations });
		}
		return null;
	}

	public static String convertToHashForPasswordAuthentication(String salt, String password) {
		PKCS5S2ParametersGenerator kdf = null;
		int hashBytes = 20;
		int iterations = 1000;
		byte[] tmpHash = null;
		String hash = null;
		byte[] tmpSalt = null;
		try {
			tmpSalt = Base64.decode(salt);
			kdf = new PKCS5S2ParametersGenerator();
			kdf.init(password.getBytes("UTF-8"), tmpSalt, iterations);
			tmpHash = ((KeyParameter) kdf.generateDerivedMacParameters(8 * hashBytes)).getKey();
			hash = new String(Base64.encode(tmpHash));
			return hash;
		} catch (Exception e) {
			logger.error("CommonUtils.convertToHashForPasswordAuthentication error: " + e);
		}finally {
			makeVariablesNull(
					new Object[] { tmpHash, hash, hashBytes, tmpSalt, kdf, salt, iterations, password });
		}
		return null;

	}
}
