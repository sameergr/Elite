package com.innverse.elearn.test;

import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.innverse.elearn.model.FeatureAccessLevel;
import com.innverse.elearn.model.MemberFALPermission;
import com.innverse.elearn.model.Organization;
import com.innverse.elearn2.service.TestService;

public class GetTestOrganization {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/com/innverse/elearn/test/test2ApplicationContext.xml");
		TestService testService = (TestService)applicationContext.getBean("testService");
		
		Organization organization = testService.getOrganization("1");
		
		System.out.println(organization.getUserProfile().getFirstName());
		System.out.println(organization.getName());
		Set<MemberFALPermission> memberFALPermissions = organization.getMemberFALPermissions();
		for(MemberFALPermission memberFALPermission : memberFALPermissions){
			if(memberFALPermission != null){
				System.out.println(" ------- " + memberFALPermission.getMemberRole().getMessage() + " ------- ");
				if(memberFALPermission.getFeatureAccessLevelList() != null)
				for(FeatureAccessLevel fal : memberFALPermission.getFeatureAccessLevelList()){
					System.out.println("Feature Name --> " + fal.getFeature().getFeatureName());
				}
			}
		}
		
		
		System.out.println(" ------- Subscription Plan FAL List ------- ");
		Set<FeatureAccessLevel> fals = organization.getSubscription().getSubscriptionPlan().getFeatureAccessLevelList();
		if(fals != null)
		for(FeatureAccessLevel fal : fals){
			System.out.println("Feature Name --> " + fal.getFeature().getFeatureName());
		}
		
		
	}

}
