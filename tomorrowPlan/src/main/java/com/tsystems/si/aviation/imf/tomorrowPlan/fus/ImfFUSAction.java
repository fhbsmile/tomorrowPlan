package com.tsystems.si.aviation.imf.tomorrowPlan.fus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tsystems.aviation.imf.client.commons.ImfServiceType;
import com.tsystems.aviation.imf.client.exception.ImfClientConnectionException;
import com.tsystems.aviation.imf.client.exception.ImfClientInvalidServiceTypeException;
import com.tsystems.aviation.imf.client.exception.ImfClientMessageValidationException;
import com.tsystems.aviation.imf.client.exception.ImfClientUsFailedException;
import com.tsystems.aviation.imf.client.exception.ImfServerUnknowException;
import com.tsystems.aviation.imf.client.subsystem.ImfUsClient;

public class ImfFUSAction {
	private static final Logger     logger               = LoggerFactory.getLogger(ImfFUSAction.class);
	public ImfUsClient  usClient = null;

	
	
	public void update(String xmlMessage){
		if(usClient==null){
			try {
				logger.info("Init USClient.........");
				usClient = ImfUsClient.getImfUsClient(ImfServiceType.FUS);
				logger.info("Init USClient Success.........");			
			} catch (ImfClientConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error(e.getMessage(),e);
			} catch (ImfClientInvalidServiceTypeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error(e.getMessage(),e);
			}
		}
		
		if(usClient!=null){

				try {
					logger.info("\n{}",xmlMessage);
					usClient.update(xmlMessage);
					logger.info("发送成功");
				} catch (ImfServerUnknowException e) {
					// TODO Auto-generated catch block
					//logger.error("\n{}",xmlMessage);
					e.printStackTrace();
					logger.error(e.getMessage(),e);
				} catch (ImfClientMessageValidationException e) {
					// TODO Auto-generated catch block
					//logger.error("\n{}",xmlMessage);
					e.printStackTrace();
					logger.error(e.getMessage(),e);
				} catch (ImfClientConnectionException e) {
					// TODO Auto-generated catch block

					e.printStackTrace();
					//logger.error("\n{}",xmlMessage);
					logger.error(e.getMessage(),e);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block

					e.printStackTrace();
					//logger.error(e.getMessage(),e);
				} catch (ImfClientUsFailedException e) {
					// TODO Auto-generated catch block

					e.printStackTrace();
					//logger.error("\n{}",xmlMessage);
					logger.error(e.getMessage(),e);
				}
			

		    }

		

	}
}
