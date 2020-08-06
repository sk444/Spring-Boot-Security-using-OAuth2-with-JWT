package com.pixelTrice.firstSpringBootApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration 
public class ConfigOAuth2 extends AuthorizationServerConfigurerAdapter{
	
	   private String clientId = "pixeltrice";
	   private String clientSecret = "pixeltrice-secret-key";
	   private String privateKey = "-----BEGIN RSA PRIVATE KEY-----\r\n" + 
	   		"MIIEowIBAAKCAQEAv5PgrPTbXNKz+FlZG4mwPAoi/IymwQieT4RHltnExAk0QBey\r\n" + 
	   		"JMtsZLUWFBhaxcnF796tnYZWUYZu+Z6LOqMsEWbweWW4rVL7PqRCNZ9F4pXwWwjx\r\n" + 
	   		"bCEyBZRovjmKHNWXE0X2rEOVY40V0YObnIduau1cwYmY45P1Nw90yzE3jvZLmkEv\r\n" + 
	   		"chax/W2XLe7HfCoXst/W+R72634kF/IwZyghmpuvcLvvkUaBJWctpUEcvqcyFJQS\r\n" + 
	   		"xkia0fB0J4ZJ/bAhRrnN75LXtccFlXvy8P0Awm9Gfp8+Zd9RvJYFI0DWJkJE3fRt\r\n" + 
	   		"XCw7UcdA5GkA9Mg0TAdPwpz6PlPCPReXSn1moQIDAQABAoIBADwQ3WzdCLo9xfej\r\n" + 
	   		"6/BaZ0sr0nRklRQI47oCu39sn+jnCd7ejxhCP6YDsPxz9mH1NX5TzxOIvx+oirrx\r\n" + 
	   		"Hq4v4u1B3TvyzMhrMCMf1TKC0aKNaGLilu9UKR7evUiHOep4fXmpggHY3snrvl+G\r\n" + 
	   		"aA/X1qLn8SEtRyZziXrN+cyLjePggkxzCE3bdstsnQVxlDwmHG1UYQtcI2x8SrC6\r\n" + 
	   		"n9j1rQ1+NmFgV5SXoCGDPfna9aoJPbAQdcmQtdbdsxKQExuMUu76ng3cY5Kl/IVD\r\n" + 
	   		"ft5uzV/LYzMXNxMIq/6wRXqfIL+TveQJDZKCQqUnyppU5Kzry823Zr+I/WyTJtSu\r\n" + 
	   		"hjjyc1ECgYEA3mRhem7mPmL3Ku34hATvQbDYy6G7wxKU1MkNOPeQlzXiN6avIaiD\r\n" + 
	   		"sSDrir6zZK+SWfpViWyhvwgV9q4gnW32SaCOldPrDEPnAuUMxfL/Q2opv46waCMX\r\n" + 
	   		"N9WPWdV2ZRz9r47AdCLFGlrln09agvg6pxhjFXh3JTxBajFAF2+ULjsCgYEA3Idi\r\n" + 
	   		"9jwaaqWiBcOxLAWgsMnanLZB+mFcox39hx0yTTT9/fLuJOifEnuLo7vu87+PJDvB\r\n" + 
	   		"lAoVsq4mtntNLzt2fCldI/gLDBea8z/1IRuFsM3SXzzycqnXtiRE9dH+hse6uP5i\r\n" + 
	   		"cvm3ZdfN2ZHfnDandN0Wvd22Co+LcwJBteHhJNMCgYEAj92oIaf0Uo97Dc58Ygs/\r\n" + 
	   		"K+PXm4O5D2zJ0w1s/FlKtEcrXIz75m45IzEErvDG5G+f4Y1MIGaiDA6TBs2GU9F6\r\n" + 
	   		"7g5rrwk0mO6kVv8N0FBuSZI4B6+q9vMcZgZFgfvRZ3/lrNhQL9D+aRo8SV7wUqF6\r\n" + 
	   		"b/Yawxz3FqNwPd42c/K+jVkCgYAvCsJVfGS2Mzbou+HjXh/1FloH6+kPHA3Czfbz\r\n" + 
	   		"B4tfZNp5fYTHYsfsDWawOrqyTD6goA6fIbeXMgcYDh1c4fZYbgpsTdEtdyuakB4e\r\n" + 
	   		"Pdy65mzl2/5r70cQl5QOTAlbfueZ5snhdNtmYi6LJs7oZ9A44aCkR9M5VX2NvwF9\r\n" + 
	   		"Zv27+QKBgEu5FP4soTzXmEnztNmuIVqFughT6gjG2DIhMhbX/irksnUYgh8mYHBf\r\n" + 
	   		"AjOG5A/XQTRmTJLFyib32HGzaxkyy1dXLL9D9geokouEDMxzLB6merHtEUERABjl\r\n" + 
	   		"pJGQRYUDpW9lIk+4zPkkMbZRgR6R45TMFoHySEZumEf0nm49kn4t\r\n" + 
	   		"-----END RSA PRIVATE KEY-----";
	   private String publicKey = "-----BEGIN PUBLIC KEY-----\r\n" + 
	   		"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAv5PgrPTbXNKz+FlZG4mw\r\n" + 
	   		"PAoi/IymwQieT4RHltnExAk0QBeyJMtsZLUWFBhaxcnF796tnYZWUYZu+Z6LOqMs\r\n" + 
	   		"EWbweWW4rVL7PqRCNZ9F4pXwWwjxbCEyBZRovjmKHNWXE0X2rEOVY40V0YObnIdu\r\n" + 
	   		"au1cwYmY45P1Nw90yzE3jvZLmkEvchax/W2XLe7HfCoXst/W+R72634kF/IwZygh\r\n" + 
	   		"mpuvcLvvkUaBJWctpUEcvqcyFJQSxkia0fB0J4ZJ/bAhRrnN75LXtccFlXvy8P0A\r\n" + 
	   		"wm9Gfp8+Zd9RvJYFI0DWJkJE3fRtXCw7UcdA5GkA9Mg0TAdPwpz6PlPCPReXSn1m\r\n" + 
	   		"oQIDAQAB\r\n" + 
	   		"-----END PUBLIC KEY-----";
	   
	   @Autowired
	   @Qualifier("authenticationManagerBean")
	   private AuthenticationManager authenticationManager;
	   
	   @Autowired
	   PasswordEncoder passwordEncoder;
	   
	   @Bean
	   public JwtAccessTokenConverter tokenEnhancer() {
	      JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
	      converter.setSigningKey(privateKey);
	      converter.setVerifierKey(publicKey);
	      return converter;
	   }
	   
	   @Bean
	   public JwtTokenStore tokenStore() {
	      return new JwtTokenStore(tokenEnhancer());
	   }
	   
	   @Override
	   public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
	      endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
	      .accessTokenConverter(tokenEnhancer());
	   }
	   @Override
	   public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
	      security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	   }
	   @Override
	   public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
	      clients.inMemory().withClient(clientId).secret(passwordEncoder.encode(clientSecret)).scopes("read", "write")
	         .authorizedGrantTypes("password", "refresh_token").accessTokenValiditySeconds(20000)
	         .refreshTokenValiditySeconds(20000);

	   }

}
