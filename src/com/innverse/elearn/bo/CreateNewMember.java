package com.innverse.elearn.bo;

import org.springframework.beans.factory.annotation.Autowired;

import com.innverse.elearn.eenum.MemberRole;
import com.innverse.elearn.model.Invitation;
import com.innverse.elearn.model.MemberFALPermission;
import com.innverse.elearn.model.Organization;
import com.innverse.elearn.model.Permission;
import com.innverse.elearn.model.UserAccount;
import com.innverse.elearn.model.UserProfile;
import com.innverse.elearn.services.CommonServiceImpl;

public class CreateNewMember {
	
	public CreateNewMember(Invitation invitationDetails, CommonServiceImpl commonService){
		commonService.getUserDetailsByEmailAdd(invitationDetails.getFriendEmailId());
		Organization organization = commonService.getOrganizationById(invitationDetails.getOrganizationId());
		UserProfile userProfile = commonService.getUserProfileByEmailAdd(invitationDetails.getFriendEmailId());
		invitationDetails.setValid("Yes");
		invitationDetails.setActive(true);
		commonService.saveInvitation(invitationDetails);
		commonService.addMember(organization, userProfile);
	}
	
	public CreateNewMember(UserProfile memberProfile, String organizationId, String token, CommonServiceImpl commonService){
		Organization organization = commonService.getOrganizationById(Long.valueOf(organizationId));
		Invitation invitation = commonService.getInvitedMemberDetails(token);
		invitation.setValid("Yes");
		invitation.setActive(true);
		
		commonService.saveInvitation(invitation);
		commonService.addMember(organization, memberProfile);
	}

}
