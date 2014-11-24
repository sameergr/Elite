package com.innverse.elearn.bo;

import java.util.Set;

import com.innverse.elearn.eenum.MemberRole;
import com.innverse.elearn.model.Member;
import com.innverse.elearn.model.MemberFALPermission;

public class MemberPermissions {

	 public MemberFALPermission getMFALPermissions(Member member) {
		 
		 MemberFALPermission memberPermission = null;
		 Set<MemberFALPermission> memberFALPermissions =  member.getOrganization().getMemberFALPermissions();
			for(MemberFALPermission memberFalPermission : memberFALPermissions){
				if(memberFalPermission.getMemberRole().equals(member.getMemberRole())){
					memberPermission = memberFalPermission;
					break;
				}
			}
		 
		 return memberPermission;
		// TODO Auto-generated constructor stub
	}
	
}
