package com.jixianxueyuan.rest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springside.modules.web.MediaTypes;

import com.jixianxueyuan.config.MyErrorCode;
import com.jixianxueyuan.entity.VerificationCode;
import com.jixianxueyuan.rest.dto.MyResponse;
import com.jixianxueyuan.service.VerificationCodeService;

@RestController
@RequestMapping(value = "/api/v1/verification_code")
public class VerificationCodeRestController {
	
	private static final int TIMEOUT_SECONDS = 10;
	
	@Autowired
	private VerificationCodeService verificationCodeService;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
	public MyResponse get(@RequestParam (value = "phone", defaultValue = "") String phone){
		
		if(StringUtils.isBlank(phone)){
			return MyResponse.err(MyErrorCode.PHONE_EMPTY);
		}
		
		double radome = Math.random()*9000+1000;
		String verCode = String.valueOf(new Double(radome).intValue());
		
		boolean isSuccess = requestVerificationCode(phone, verCode);
		//boolean isSuccess = true;
		if(isSuccess){
			VerificationCode verificationCode = new VerificationCode();
			verificationCode.setChecked(0);
			verificationCode.setPhone(phone);
			verificationCode.setCode(verCode);
			verificationCodeService.saveVerificationCode(verificationCode);
			return MyResponse.ok(true);
		}
		
		return MyResponse.err(MyErrorCode.UNKNOW_ERROR);
	}

	@RequestMapping(value= "check",method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
	public MyResponse check(@RequestParam (value = "phone", defaultValue = "") String phone,
			@RequestParam (value = "code", defaultValue = "") String code){
		
		if(StringUtils.isEmpty(phone) || StringUtils.isEmpty(code)){
			return MyResponse.err(MyErrorCode.UNKNOW_ERROR);
		}
		
		VerificationCode verificationCode = verificationCodeService.getVerificationCodeByPhoneAndCode(phone, code);
		
		if(verificationCode != null){
			return MyResponse.ok(true);
		}
		
		
		return MyResponse.err(MyErrorCode.SMS_VERIFICATION_CODE_CHECK_ERROR);
	}
	
	private boolean requestVerificationCode(String phone,String verCode){
		
		
		String url = "http://121.199.16.178/webservice/sms.php" + "?method=Submit&account=cf_yumfee&password=yumfeeihuyi&mobile=" + phone + "&content=尊敬的滑板圈用户，您的验证码是：" + verCode + "，验证码" + "10分钟" + "内有效。如非本人操作，可不用理会！";
		
		try {
			
			String result = fetchContentByJDKConnection(url);
			
			boolean isStutas = result.contains("<code>2</code>");
			
			return isStutas;
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	private String fetchContentByJDKConnection(String contentUrl) throws IOException {
		
		String result;

		HttpURLConnection connection = (HttpURLConnection) new URL(contentUrl).openConnection();
		// 设置Socket超时
		connection.setReadTimeout(TIMEOUT_SECONDS * 1000);
		try {
			connection.connect();

			// 真正发出请求
			InputStream input;
			try {
				input = connection.getInputStream();
			} catch (FileNotFoundException e) {
				
				return null;
			}


			// 输出内容
			
			try {
				// 基于byte数组读取InputStream并直接写入OutputStream, 数组默认大小为4k.
				result = IOUtils.toString(input);
			} finally {
				// 保证InputStream的关闭.
				IOUtils.closeQuietly(input);
			}
		} finally {
			connection.disconnect();
		}
		
		return result;
	}
}
