package com.hfq.house.manager.util;

import java.security.Key;
import java.security.Security;

import javax.crypto.Cipher;

import com.alibaba.fastjson.JSONObject;

/**
 * DES加密和解密工具,可以对字符串进行加密和解密操作 。
 * 
 * @author fancie
 *         <p>
 *         2012-04-16
 *         </p>
 */
public class DesUtils {

	/** 字符串默认键值 */

	/** 加密工具 */
	private Cipher encryptCipher = null;

	/** 解密工具 */
	private Cipher decryptCipher = null;

	
	/**
	 * 将byte数组转换为表示16进制值的字符串， 如：byte[]{8,18}转换为：0813， 和public static byte[]
	 * hexStr2ByteArr(String strIn) 互为可逆的转换过程
	 * 
	 * @param arrB
	 *            需要转换的byte数组
	 * @return 转换后的字符串
	 * @throws Exception
	 *             本方法不处理任何异常，所有异常全部抛出
	 */
	public static String byteArr2HexStr(byte[] arrB) throws Exception {
		int iLen = arrB.length;
		// 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
		StringBuffer sb = new StringBuffer(iLen * 2);
		String s = "";
		for (int i = 0; i < iLen; i++) {
			int intTmp = arrB[i];
			s+= intTmp;
			// 把负数转换为正数
			while (intTmp < 0) {
				intTmp = intTmp + 256;
			}
			// 小于0F的数需要在前面补0
			if (intTmp < 16) {
				sb.append("0");
			}
			sb.append(Integer.toString(intTmp, 16));
		}
		return sb.toString();
	}

	/**
	 * 将表示16进制值的字符串转换为byte数组， 和public static String byteArr2HexStr(byte[] arrB)
	 * 互为可逆的转换过程
	 * 
	 * @param strIn
	 *            需要转换的字符串
	 * @return 转换后的byte数组
	 * @throws Exception
	 *             本方法不处理任何异常，所有异常全部抛出
	 * @author <a href="mailto:leo841001@163.com">LiGuoQing</a>
	 */
	public static byte[] hexStr2ByteArr(String strIn) throws Exception {
		byte[] arrB = strIn.getBytes("UTF-8");
		int iLen = arrB.length;

		// 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i = i + 2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}


	/**
	 * 指定密钥构造方法
	 * 
	 * @param strKey
	 *            指定的密钥
	 * @throws Exception
	 */
	public DesUtils(String strKey) throws Exception {
		Security.addProvider(new com.sun.crypto.provider.SunJCE());
		Key key = getKey(strKey.getBytes("UTF-8"));

		encryptCipher = Cipher.getInstance("DES");
		encryptCipher.init(Cipher.ENCRYPT_MODE, key);

		decryptCipher = Cipher.getInstance("DES");
		decryptCipher.init(Cipher.DECRYPT_MODE, key);
	}

	/**
	 * 加密字节数组
	 * 
	 * @param arrB
	 *            需加密的字节数组
	 * @return 加密后的字节数组
	 * @throws Exception
	 */
	public byte[] encrypt(byte[] arrB) throws Exception {
		return encryptCipher.doFinal(arrB);
	}

	/**
	 * 加密字符串
	 * 
	 * @param strIn
	 *            需加密的字符串
	 * @return 加密后的字符串
	 * @throws Exception
	 */
	public String encrypt(String strIn){
		try {
			return byteArr2HexStr(encrypt(strIn.getBytes("UTF-8")));
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 解密字节数组
	 * 
	 * @param arrB
	 *            需解密的字节数组
	 * @return 解密后的字节数组
	 * @throws Exception
	 */
	public byte[] decrypt(byte[] arrB) throws Exception {
		return decryptCipher.doFinal(arrB);
	}

	/**
	 * 解密字符串
	 * 
	 * @param strIn
	 *            需解密的字符串
	 * @return 解密后的字符串
	 * @throws Exception
	 */
	public String decrypt(String strIn) {
		try {
			return new String(decrypt(hexStr2ByteArr(strIn)));
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 从指定字符串生成密钥，密钥所需的字节数组长度为8位 不足8位时后面补0，超出8位只取前8位
	 * 
	 * @param arrBTmp
	 *            构成该字符串的字节数组
	 * @return 生成的密钥
	 * @throws java.lang.Exception
	 */
	private Key getKey(byte[] arrBTmp) throws Exception {
		// 创建一个空的8位字节数组（默认值为0）
		byte[] arrB = new byte[8];
		// 将原始字节数组转换为8位
		for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
			arrB[i] = arrBTmp[i];
		}
		// 生成密钥
		Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");
		return key;
	}
	
	/**
	 * 获得加密串
	 * @param str
	 * @return
	 */
	public static String getEncrypt(String str, String key){
		DesUtils des = null;
		try {
			des = new DesUtils(key);
		} catch (Exception e) {
			e.printStackTrace();
		}// 自定义密钥
		if(des!=null){
			return des.encrypt(str);
		}
		return "";
	}
	
	/**
	 * 获得解密串
	 * @param str
	 * @return
	 */
	public static String getDecrypt(String str, String key){
		DesUtils des = null;
		try {
			des = new DesUtils(key);
		} catch (Exception e) {
			e.printStackTrace();
		}// 自定义密钥
		if(des!=null){
			return des.decrypt(str);
		}
		return "";
	}
	
//	public static void main(String[] args) {
//		//{"idCard":"360729198907020018","mobile":"13026685624","money":"1000","bankNo":"6230580000030595529"}
//		JSONObject desParam = new JSONObject();
//		desParam.put("bankNo", "6230580000030595529");//还款银行卡卡号
//		desParam.put("idCard","360729198907020018");//身份证
//		desParam.put("mobile","13026685624");//手机号
//		desParam.put("money", "1000");//借款金额 
//		System.out.println(DesUtils.getEncrypt(desParam.toJSONString(), "463cb4379a740cc7636af926e5459c1c84b5d392d4479669"));
//		System.out.println(DesUtils.getDecrypt("99905bbb64ebb828d54c0210b4f6ebd75901d7f6e543df912ea15ce66b975f39ce7f0c7c87399b74439e0a15683d51742b674aa3770b4c08fbf8480561fd27bad6ed72941c525e90fb2111bee809cae9e1d9cbcdfc81ecddc8bef7a9915fdedc160cf3fab3207fbe", "463cb4379a740cc7636af926e5459c1c84b5d392d4479669"));
//	}
}