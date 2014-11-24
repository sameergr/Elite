package com.innverse.elearn.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.innverse.elearn.bo.MemberPermissions;
import com.innverse.elearn.config.Config;
import com.innverse.elearn.contants.Constants;
import com.innverse.elearn.contants.PGConstants;
import com.innverse.elearn.eenum.MemberRole;
import com.innverse.elearn.eenum.RegistrationStatus;
import com.innverse.elearn.json.AddQuestions;
import com.innverse.elearn.json.CheckoutForm;
import com.innverse.elearn.json.CreateNewRoleForm;
import com.innverse.elearn.json.FeatureAccessLevelForm;
import com.innverse.elearn.json.GenrateAutoSeriesForm;
import com.innverse.elearn.json.SeriesForm;
import com.innverse.elearn.json.SubscriptionPlanForm;
import com.innverse.elearn.json.UserForm;
import com.innverse.elearn.model.Blog;
import com.innverse.elearn.model.Category;
import com.innverse.elearn.model.Content;
import com.innverse.elearn.model.Feature;
import com.innverse.elearn.model.FeatureAccessLevel;
import com.innverse.elearn.model.Invitation;
import com.innverse.elearn.model.Member;
import com.innverse.elearn.model.MemberFALPermission;
import com.innverse.elearn.model.Organization;
import com.innverse.elearn.model.Question;
import com.innverse.elearn.model.ScoreBoard;
import com.innverse.elearn.model.ScoreBoardCourse;
import com.innverse.elearn.model.SubscriptionPlan;
import com.innverse.elearn.model.UserAccount;
import com.innverse.elearn.model.UserPayment;
import com.innverse.elearn.model.UserProfile;
import com.innverse.elearn.services.CommonServiceImpl;
import com.innverse.elearn.services.PaymentTransactionServiceImpl;
import com.innverse.elearn.util.GetCompanyUtil;

public class AbstractController {

	@Autowired
	protected CommonServiceImpl commonService;

	@Autowired
	protected PaymentTransactionServiceImpl paymentService;

	@RequestMapping(value = "/library", method = RequestMethod.GET)
	public String libraryPage(HttpServletRequest req) {
		return "reg_library";
	}

	@RequestMapping(value = "/signOut", method = RequestMethod.GET)
	public ModelAndView userSignOut(HttpServletRequest req) {
		ModelAndView mv = new ModelAndView("organization_Page");
		if (!(req.getParameter("err")).equals("")) {
			mv.addObject("err", req.getParameter("err"));
		}
		req.getSession(false).removeAttribute("member");
		req.getSession(false).removeAttribute("memberPermisssion");
		req.getSession(false).removeAttribute("cn");
		return mv;
	}

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView userDashboard(HttpServletRequest req) {
		ModelAndView mv = new ModelAndView("reg_common_dashboard");
		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("member") != null) {
			return mv;
		} else {
			mv.setViewName("organization_Page");
			return mv;
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView userLogin(
			@ModelAttribute("userLogin") UserForm userForm,
			BindingResult result, HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		ModelAndView mv = new ModelAndView();
		try {
			UserAccount user = commonService.validateUser(userForm); // If user
			// is
			// registered
			if (user != null) {
				try {
					if (user.getUserProfile().getOrganization() != null) { // It
						// means
						// user
						// has
						// an
						// organization
						// &
						// he
						// is
						// Admin
						if ((user.getUserProfile().getOrganization()
								.getRegistrationStatus()) == (RegistrationStatus.PENDING)) {
							// If user organization is not registered
							mv.addObject("userEmail", user.getUserProfile()
									.getEmail());
							mv.addObject("organizationName", user
									.getUserProfile().getOrganization()
									.getName());
							mv.addObject(
									"id",
									String.valueOf(user.getUserProfile()
											.getOrganization()
											.getSubscription()
											.getSubscriptionPlan().getId()));
							mv.addObject("err",
									Constants.SUCCESSFULLY_REGISTERED);
							mv.setViewName("guest_user_create_organization");
							return mv;
						} else {
							Member organizationMember = commonService
									.getOrganizationMember(user
											.getUserProfile().getOrganization()
											.getId(), user.getUserProfile()
											.getId());
							MemberPermissions memberPermission = new MemberPermissions();
							MemberFALPermission memberFALPermissions = memberPermission
									.getMFALPermissions(organizationMember);
							session.setAttribute("member", organizationMember);
							session.setAttribute("memberPermisssion",
									memberFALPermissions);
						}
					} else {
						String companyName = GetCompanyUtil.getCompanyName(req);
						Organization org = commonService
								.getOrganizationByName(companyName);
						Member organizationMember = commonService
								.getOrganizationMember(org.getId(), user
										.getUserProfile().getId());
						MemberPermissions memberPermission = new MemberPermissions();
						MemberFALPermission memberFALPermissions = memberPermission
								.getMFALPermissions(organizationMember);
						session.setAttribute("member", organizationMember);
						session.setAttribute("memberPermisssion",
								memberFALPermissions);
						mv.setViewName("reg_common_dashboard");
						return mv;

					}
				} catch (Exception e) { // If user has No subscription plan / NO
					// Organization
					// It is an invited Member
				}
				mv.setViewName("reg_common_dashboard");
				return mv;
			}
		} catch (NoResultException e) {
			mv.addObject("err", Constants.USERNAME_PASSWORD_INCORRECT);
			mv.setViewName("organization_Page");
			return mv;
		}
		mv.addObject("err", Constants.NOT_REGISTERED);
		mv.setViewName("organization_Page");
		return mv;
	}

	@RequestMapping(value = { "/invitationSendFriend" }, method = RequestMethod.POST)
	public ModelAndView invitation(
			@ModelAttribute("invitationForm") Invitation invitations,
			BindingResult result, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		String host = "http://localhost:8080/ELMS";
		String baseurl = host + "/site/signUp";
		StringBuilder url = new StringBuilder(baseurl);
		ModelAndView mv = new ModelAndView("reg_invite_Friend");
		if (invitations.getFriendEmailId() == "") { // IF Required Fields are
			// Empty
			mv.addObject("err", Constants.EMPTY_FIELDS);
			mv.setViewName("reg_invite_Friend");
			return mv;
		}
		if (commonService.isUserExist(invitations.getFriendEmailId())) { // if
			// user
			// already
			// registered
			boolean userAlreadyinMemberList = commonService
					.isUserAlreadyMember(invitations.getFriendEmailId());
			if (userAlreadyinMemberList) { // If user already in member
				// table(member of someone may be
				// ours)
				Member memberAccount = (Member) session.getAttribute("member");
				UserProfile invitedMember = commonService
						.getUserProfileByEmailAdd(invitations
								.getFriendEmailId());
				List<Member> members = commonService
						.getMemberListbyProfileId(invitedMember.getId()); // Getting
				// member
				// list
				// of
				// invited
				// user
				for (Member memberList : members) {
					if ((String.valueOf(memberList.getOrganization().getId()))
							.equals((String.valueOf(memberAccount.getAccount()
									.getOrganization().getId())))) { // Checking
						// that
						// member
						// is
						// not
						// already
						// added
						// with
						// our
						// organization.
						mv.addObject("err",
								Constants.REGISTER_USER_EXIST_IN_LIST);
						mv.setViewName("reg_invite_Friend");
						return mv;
					}
				}
				commonService.sendInvitationMail(invitations, url.toString(),
						request, session);
				mv.addObject("err", Constants.SEND_SUCCESSFULLY);
				mv.setViewName("reg_invite_Friend");
				return mv;
			} else { // for those who are not in member table but registered
				boolean status = commonService.sendInvitationMail(invitations,
						url.toString(), request, session);
				if (status) {
					mv.addObject("err", Constants.REGISTER_USER_MSG);
					mv.setViewName("reg_invite_Friend");
				} else {
					mv.addObject("err", Constants.REGISTER_USER_EXIST_IN_LIST);
					mv.setViewName("reg_invite_Friend");
				}
				return mv;
			}
		} else { // if user is not registered & send invitation for new
			// registration
			commonService.sendInvitationMail(invitations, url.toString(),
					request, session);
			mv.addObject("err", Constants.SEND_SUCCESSFULLY);
			mv.setViewName("reg_invite_Friend");
		}
		return mv;
	}

	@RequestMapping(value = "/editProfile", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView editProfilePage(HttpServletRequest req,
			HttpSession session) {
		ModelAndView mv = new ModelAndView();
		List<SubscriptionPlan> allPlans = commonService
				.getAllSubscriptionPlans();
		mv.addObject("allPlans", allPlans);
		mv.setViewName("reg_editProfile_page");
		return mv;
	}

	@RequestMapping(value = "/loadFeatures/{id}", method = RequestMethod.POST)
	public @ResponseBody
	ModelAndView loadFeatures(@PathVariable("id") String id,
			HttpServletRequest req) {
		ModelAndView mv = new ModelAndView("load_features");
		if ((id).equals("0")) {
			return mv;
		}
		SubscriptionPlan choosenPlan = commonService
				.getSubscriptionPlanById(Long.valueOf(id));
		mv.addObject("plan", choosenPlan);
		return mv;
	}

	@RequestMapping(value = "/saveProfile", method = RequestMethod.POST)
	public ModelAndView saveProfilePage(
			@ModelAttribute("editProfile") UserForm userForm,
			@RequestParam("aPlans") String choosenPlan, HttpServletRequest req,
			HttpSession session) {
		ModelAndView mv = new ModelAndView("reg_editProfile_page");
		MultipartFile file = userForm.getLogo();
		String fileName = file.getOriginalFilename();
		try {
			InputStream inputStream = file.getInputStream();

			File uploadedFile = new File(Config.getMsg("image.path") + ""
					+ fileName);
			if (!uploadedFile.exists()) {
				uploadedFile.createNewFile();
			}
			OutputStream outputStream = new FileOutputStream(uploadedFile);
			int read = 0;
			byte[] bytes = file.getBytes();

			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
		} catch (Exception e) {
			e.printStackTrace();
			List<SubscriptionPlan> allPlans = commonService
					.getAllSubscriptionPlans();
			mv.addObject("allPlans", allPlans);
			return mv;
		}
		commonService.saveUserProfile(userForm, fileName);
		List<SubscriptionPlan> allPlans = commonService
				.getAllSubscriptionPlans();
		mv.addObject("allPlans", allPlans);
		return mv;
	}

	@RequestMapping(value = "/mysubscription", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView mySubscription(HttpServletRequest req,
			HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Member member = (Member) session.getAttribute("member");
		List<UserPayment> planHistory = commonService
				.getFullPlanHistorybyOrgId(member.getAccount()
						.getOrganization().getId());
		List<SubscriptionPlan> allPlans = commonService
				.getAllSubscriptionPlans();
		mv.addObject("plans", allPlans);
		mv.addObject("planHistory", planHistory);
		mv.setViewName("reg_mySubscription_page");
		return mv;
	}

	@RequestMapping(value = "/showAvaliablePlansForUser", method = RequestMethod.GET)
	public ModelAndView showAvaliablePlans(HttpServletRequest req,
			HttpSession session) {
		ModelAndView mv = new ModelAndView("guest_user_avaliable_plan");
		Member memberAccount = (Member) session.getAttribute("member");
		List<SubscriptionPlan> allSubscriptionPlans = commonService
				.getAllSubscriptionPlans();
		mv.addObject("currentPlan", memberAccount.getAccount()
				.getOrganization().getSubscription().getSubscriptionPlan()
				.getId());
		mv.addObject("planDetails", allSubscriptionPlans);
		return mv;
	}

	@RequestMapping(value = "/paymentDetails", method = RequestMethod.GET)
	public ModelAndView paymentDetails(HttpSession session) {
		ModelAndView mv = new ModelAndView("guest_user_planDetails");
		Member memberAccount = (Member) session.getAttribute("member");
		List<UserPayment> userPaymentDetails = commonService
				.getPaymentDetailsByUsername(memberAccount.getAccount()
						.getUsername());
		mv.addObject("userPayments", userPaymentDetails);
		return mv;
	}

	@RequestMapping(value = "/memberActivity", method = RequestMethod.GET)
	public ModelAndView viewMemberActivity(HttpSession session) {
		ModelAndView mv = new ModelAndView("member_activity");
		Member member = (Member) session.getAttribute("member");
		List<ScoreBoard> boards = commonService
				.getScoreBoardRecordByOrganizationId(member.getOrganization()
						.getId());
		List<ScoreBoardCourse> scoreBoardCourse = commonService
				.getCourseResultByOrganizationId(member.getOrganization()
						.getId());
		mv.addObject("boards", boards);
		mv.addObject("scoreBoardCourse", scoreBoardCourse);
		return mv;
	}

	@RequestMapping(value = "/loadPermisssions/{organizationId}", method = RequestMethod.POST)
	public @ResponseBody
	ModelAndView loadPermissions(
			@PathVariable("organizationId") String organizationId,
			@MatrixVariable String role, HttpServletRequest req) {
		ModelAndView mv = new ModelAndView("load_permissions");
		if ((organizationId).equals("0")) {
			return mv;
		} else {
			Organization memberOrganization = commonService
					.getOrganizationById(Long.valueOf(organizationId));
			Set<FeatureAccessLevel> featuresList = null;
			mv.addObject("memberPermissions",
					memberOrganization.getMemberFALPermissions());
			for (MemberFALPermission mFAL : memberOrganization
					.getMemberFALPermissions()) {
				MemberRole mr = mFAL.getMemberRole();
				if ((String.valueOf(mr.getCode())).equals(role)) {
					featuresList = mFAL.getFeatureAccessLevelList();
				}
			}
			mv.addObject("featureList", featuresList);
			return mv;
		}
	}

	@RequestMapping(value = "/buyPlan", method = RequestMethod.GET)
	public ModelAndView buyPlan(@RequestParam("aPlans") String choosePlanId,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		ModelAndView mv = new ModelAndView("guest_user_plan_form");
		SubscriptionPlan subscriptionPlan = commonService
				.getSubscriptionPlanById(Long.valueOf(choosePlanId));
		mv.addObject("planDetails", subscriptionPlan);
		session.setAttribute("planid", choosePlanId);
		return mv;
	}

	@RequestMapping(value = "/checkOutPlan", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView checkOutPlan(
			@ModelAttribute("planCheckout") CheckoutForm checkOut,
			BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
		request.getSession().setAttribute("PPCheckout", checkOut);
		ModelAndView mv = new ModelAndView("pp_checkout_page");
		mv.addObject("URL", "paypal/pg/submit");
		mv.addObject("message",
				"Please wait while you have been transfered to Paypal Payment Gateway");
		return mv;
	}

	@RequestMapping(value = "paypal/pg/submit")
	public String submitPPTransaction(HttpServletRequest request,
			HttpServletResponse response) {
		CheckoutForm checkout = (CheckoutForm) request.getSession()
				.getAttribute("PPCheckout");
		boolean status = paymentService.initPPTransaction(checkout, request);
		System.out.println("SESSION PP TOKEN ---> "
				+ request.getSession().getAttribute("PPTOKEN"));
		String redirectStr = "";
		if (!status) {
			redirectStr = "/org/mysubscription";
		} else {
			redirectStr = PGConstants.ppPaymentGateway
					+ request.getSession().getAttribute("PPTOKEN");
		}
		System.out.println("REDIRECT STR ---> " + redirectStr);
		return "redirect:" + redirectStr;
	}

	@RequestMapping(value = "paypal/pg/failed")
	public ModelAndView failedPPTransaction(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("pp_checkout_page");
		mv.addObject("URL", request.getContextPath() + "/org/mysubscription");
		mv.addObject("message",
				"Your Transaction has been Failed, Please try again later.");
		return mv;
	}

	@RequestMapping(value = "paypal/pg/cancel")
	public ModelAndView cancelPPTransaction(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("pp_checkout_page");
		mv.addObject("URL", request.getContextPath() + "/org/mysubscription");
		mv.addObject("message", "Your Transaction has been Cancelled");
		return mv;
	}

	@RequestMapping(value = "paypal/pg/success")
	public ModelAndView returnPPTransaction(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		String token = request.getParameter("token");
		String sessionToken = (String) request.getSession().getAttribute(
				"PPTOKEN");
		ModelAndView mv = new ModelAndView("pp_checkout_page");
		if (token.equalsIgnoreCase(sessionToken)) {
			boolean status = paymentService.getDetailPPTransaction(
					sessionToken, request);
			if (status) {
				mv.addObject("URL", request.getContextPath()
						+ "/org/onSuccessPPTrans");
				mv.addObject("message",
						"Please wait while, Your Transaction has been processed");
			} else {
				mv.addObject("URL", request.getContextPath()
						+ "/org/mysubscription");
				mv.addObject("message",
						"Please wait while, Your Transaction has been failed");
			}
		} else {
			mv.addObject("URL", request.getContextPath()
					+ "/org/mysubscription");
			mv.addObject("message",
					"Please wait while, Your Transaction has been failed");
		}
		return mv;
	}

	@RequestMapping(value = "onSuccessPPTrans")
	public ModelAndView onSuccessPPTransaction(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		UserPayment userPayment = paymentService.commitPPTransaction(request);
		String choosePlan = (String) request.getSession(false).getAttribute(
				"planid");
		Member member = (Member) request.getSession(false).getAttribute(
				"member");
		SubscriptionPlan newPlan = commonService.getSubscriptionPlanById(Long
				.valueOf(choosePlan));
		commonService
				.saveUserSubscribe(newPlan, member.getAccount().getEmail());
		if ((userPayment.getTransStatus()).equalsIgnoreCase("Completed")) {
			mv.addObject("err", Constants.SUBSCRIPTION_SAVED);
			mv.setViewName("redirect:/org/signOut");
			return mv;
		} else {
			mv.setViewName("redirect:/org/mysubscription");
			return mv;
		}
	}

	@RequestMapping(value = "/content", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView getMainCategoryPage(HttpServletRequest req) {
		ModelAndView mv = new ModelAndView();
		HttpSession session = req.getSession(false);
		if ((session.getAttribute("name") != null)
				|| (session.getAttribute("member") != null)) {
			mv.addObject("categoryMapData", commonService.getAllCategories());
			mv.setViewName("reg_main_category");
			return mv;
		} else {
			mv.setViewName("organization_Page");
			return mv;
		}
	}

	@RequestMapping(value = "/showSubcategory", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView getContentPage(
			@RequestParam("catid") String categoryId, HttpServletRequest req) {
		ModelAndView mv = new ModelAndView();
		HttpSession session = req.getSession(false);
		if ((session.getAttribute("name") != null)
				|| (session.getAttribute("member") != null)) {
			List<Category> subCategories = commonService.getSubCategories(Long
					.parseLong(categoryId));
			mv.addObject("categoryMapData", commonService.getAllCategories());
			mv.addObject("catid", categoryId);
			mv.addObject("subCategories", subCategories);
			mv.setViewName("reg_category");
			return mv;
		} else {
			mv.setViewName("organization_Page");
			return mv;
		}
	}

	@RequestMapping(value = "/addSubcategory", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView addSubCatagory(
			@ModelAttribute("addcategoryform") Category categoryForm,
			HttpServletRequest req) {
		ModelAndView mv = new ModelAndView("reg_category");
		commonService.saveOrUpdateNewCategory(categoryForm);
		List<Category> subCategories = commonService
				.getSubCategories(categoryForm.getId());
		mv.addObject("subCategories", subCategories);
		mv.addObject("catid", categoryForm.getId());
		return mv;
	}

	@RequestMapping(value = "/addQuestions", method = RequestMethod.GET)
	public ModelAndView catagoryPage(HttpServletRequest req) {
		ModelAndView mv = new ModelAndView("reg_addQuestion");
		mv.addObject("subCategoryId", req.getParameter("catid").toString());
		return mv;
	}

	@RequestMapping(value = "/submitQuestions", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView questionsSubmit(
			@ModelAttribute("submitQuestions") AddQuestions addQuestion,
			BindingResult result, HttpServletRequest req, HttpSession session) {
		String subCategoryId = req.getParameter("subCategoryId");
		ModelAndView mv = new ModelAndView();
		Member memberAccount = (Member) session.getAttribute("member");
		commonService.createNewContent(subCategoryId, memberAccount,
				addQuestion);
		mv.setViewName("sucess_page");
		return mv;
	}

	@RequestMapping(value = "/loadSubCategory/{Id}", method = {
			RequestMethod.GET, RequestMethod.POST })
	public ModelAndView getSubCategory(@PathVariable("Id") String Id,
			@ModelAttribute("createcSeries") SeriesForm series,
			ModelAndView view, BindingResult result,
			HttpServletRequest request, HttpServletResponse response) {
		List<Category> subCategoryList = commonService.getAllSubCategories(Id);
		ModelAndView modelAndView = new ModelAndView(
				"reg_teacher_createSeries_ajax");
		modelAndView.addObject("subCategoryList", subCategoryList);
		List<Category> categoryList = commonService.getAllMainCategories();
		modelAndView.addObject("categoryList", categoryList);
		return modelAndView;
	}

	@RequestMapping(value = "getQuestion/{id}", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView getQuestion(@PathVariable("id") String queId,
			HttpServletRequest req, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView("reg_play_ajax");
		Question question = commonService.getQuestionById(queId);
		modelAndView.addObject("question", question);
		return modelAndView;
	}

	/*
	 * @RequestMapping(value="/organization{key}", method = RequestMethod.GET)
	 * public ModelAndView showOrganizations(@RequestParam("key") String
	 * organizationId, HttpServletRequest req, HttpSession session){
	 * ModelAndView mv = new ModelAndView(); Organization organization =
	 * commonService.getOrganizationById(Long.valueOf(organizationId)); Member
	 * memberAccount = (Member)session.getAttribute("member"); SubscriptionPlan
	 * currentSubPlan = organization.getSubscription().getSubscriptionPlan();
	 * List<FeatureAccessLevel> featureAccessLevels = null;
	 * for(FeatureAccessLevel featurelevel : featureAccessLevels){ Feature
	 * feature = featurelevel.getFeature();
	 * if(feature.getFeatureName().equalsIgnoreCase("My Members")){
	 * if(!(featurelevel.getFeatureType()).equals("UNLIMITED")){
	 * session.setAttribute("memberLimit", featurelevel.getValue());
	 * System.out.println
	 * ("featurelevel.getValue()=====1======="+featurelevel.getValue()); } else{
	 * session.setAttribute("memberFeatureType", featurelevel.getFeatureType());
	 * System
	 * .out.println("featurelevel.getFeatureType()=====2======="+featurelevel
	 * .getFeatureType()); } } System.out.println(
	 * "Features and their value according to subscription plan :"
	 * +feature.getFeatureName() + " : --Value-- :"+featurelevel.getValue() +
	 * "--feature's type-- :" +featurelevel.getFeatureType());
	 * System.out.println("URL :" +feature.getImageURL()); } List<Member>
	 * membersOrganizations =
	 * commonService.getMemberListbyProfileId(memberAccount
	 * .getAccount().getId());
	 * session.setAttribute("allFeatures",featureAccessLevels);
	 * session.setAttribute("key", organizationId);
	 * session.setAttribute("userMemberOragnizations",membersOrganizations);
	 * mv.setViewName("reg_common_dashboard"); return mv; }
	 */

	@RequestMapping(value = "/createCategory", method = RequestMethod.GET)
	public ModelAndView createCategory() {
		ModelAndView modelAndView = new ModelAndView(
				"guest_user_create_category");
		return modelAndView;
	}

	@RequestMapping(value = "/saveCategory", method = RequestMethod.POST)
	public ModelAndView saveCategory(
			@ModelAttribute("saveCategory") Category category,
			HttpServletRequest req, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView(
				"create_new_category_subcategory");
		Category newCategory = commonService.insertCategory(category, session);
		if (newCategory != null)
			;
		{
			modelAndView.addObject("newCategory", newCategory);
			modelAndView.addObject("err", Constants.CREATED_CATEGORY_IS);
		}
		return modelAndView;
	}

	@RequestMapping(value = "/saveSubCategory", method = RequestMethod.POST)
	public ModelAndView saveSubCategory(
			@ModelAttribute("saveSubCategory") Category category,
			HttpServletRequest req, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView(
				"create_new_category_subcategory");
		if (category != null) {
			commonService.saveOrUpdateNewCategory(category);
		}
		List<Category> subCategories = commonService.getSubCategories(category
				.getId());
		modelAndView.addObject("subCategories", subCategories);
		modelAndView.addObject("newCategory",
				commonService.getCategoryById(category.getId()));
		return modelAndView;
	}

	@RequestMapping(value = "/findOrg", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView findOrg(HttpServletRequest req, HttpSession session) {
		ModelAndView mv = new ModelAndView("guest_user_FindOrg");
		List<Organization> organization = commonService.getOrganizationList();
		mv.addObject("organization", organization);
		return mv;
	}

	@RequestMapping(value = "/appCenter", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView appCenter(HttpServletRequest req, HttpSession session) {
		ModelAndView mv = new ModelAndView("guest_user_appCenter");
		List<Organization> organization = commonService.getOrganizationList();
		mv.addObject("organization", organization);
		return mv;
	}

	@RequestMapping(value = "subscriptionStart", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView getStart(
			@RequestParam(value = "p", required = false) String planValue,
			@ModelAttribute("subscribeplan") SubscriptionPlan plan,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("PLAN", planValue);
		mv.setViewName("guest_user_subscriptionPlan");
		return mv;
	}

	@RequestMapping(value = "subscriptionPlan", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView getPlaneValue(
			@RequestParam(value = "p", required = false) String planValue,
			@ModelAttribute("subscribeplan") SubscriptionPlan plan,
			HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		view.addObject("PLAN", planValue);
		commonService.getSubscribe(plan);
		view.setViewName("guest_user_subscription");
		return view;
	}

	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public ModelAndView upload(HttpServletRequest req) {
		ModelAndView mv = new ModelAndView("upload_page");
		return mv;
	}

	@RequestMapping(value = "/saveUpload", method = RequestMethod.POST)
	public ModelAndView uploadImage(HttpServletRequest req)
			throws FileUploadException, Exception {
		ModelAndView mv = new ModelAndView();
		return mv;
	}

	@RequestMapping(value = "/whiteBoard", method = RequestMethod.GET)
	public ModelAndView getWhiteBoard(HttpServletRequest req) {
		ModelAndView mv = new ModelAndView("whiteBoard_page");
		return mv;
	}

	@RequestMapping(value = "/webinars", method = RequestMethod.GET)
	public ModelAndView getWebinars(HttpServletRequest req) {
		ModelAndView mv = new ModelAndView("webinars_page");
		return mv;
	}

	@RequestMapping(value = "/questionWithProficiency/{value}/{subCatId}", method = {
			RequestMethod.GET, RequestMethod.POST })
	public ModelAndView questionWithProficiency(
			@PathVariable("value") String value,
			@PathVariable("subCatId") String subCatId, HttpSession session,
			HttpServletRequest req) {
		ModelAndView modelAndView = new ModelAndView(
				"org_questionWithProficiency_ajax");
		Member memberAccount = (Member) session.getAttribute("member");
		List<Content> contentList = commonService
				.getAllQuestionsWithProficiency(memberAccount.getAccount()
						.getOrganization().getId(), subCatId, value);
		modelAndView.addObject("question", contentList);
		return modelAndView;
	}

	@RequestMapping(value = "/seriesAutoGen", method = RequestMethod.GET)
	public ModelAndView seriesAutoGen(HttpServletRequest req) {
		ModelAndView mv = new ModelAndView("create_auto_series_page");
		List<Category> categoryList = commonService.getAllMainCategories();
		mv.addObject("categoryList", categoryList);
		return mv;
	}

	@RequestMapping(value = "/saveAutoGenSeries", method = RequestMethod.POST)
	public ModelAndView saveAutoGenSeries(
			@ModelAttribute("saveAutoSeries") GenrateAutoSeriesForm genrateAS,
			HttpServletRequest req, HttpSession session) {
		ModelAndView mv = new ModelAndView("create_auto_series_page");
		Member memberAccount = (Member) session.getAttribute("member");
		UserProfile userProfile = commonService.getUserDetailsById(String
				.valueOf(memberAccount.getAccount().getId()));
		commonService.saveAutoGenSeries(userProfile, genrateAS);
		List<Category> categoryList = commonService.getAllMainCategories();
		mv.addObject("categoryList", categoryList);
		mv.addObject("err", Constants.SERIES_CREATED);
		return mv;
	}

	@RequestMapping(value = "/newRole", method = RequestMethod.GET)
	public ModelAndView createNewRole(
			@ModelAttribute("createNewRole") CreateNewRoleForm createNewRoleForm,
			@RequestParam("orgId") long organizationId, HttpServletRequest req) {
		ModelAndView mv = new ModelAndView("create_new_role");
		List<Feature> featureList = new ArrayList<Feature>();
		Member member = (Member) req.getSession(false).getAttribute("member");
		SubscriptionPlan memberSubscriptionPlan = member.getOrganization()
				.getSubscription().getSubscriptionPlan();
		for (FeatureAccessLevel memberFAL : memberSubscriptionPlan
				.getFeatureAccessLevelList()) {
			featureList.add(memberFAL.getFeature()); // Setting the member
			// Features in the new
			// List
		}
		createNewRoleForm.addFeature(featureList);
		mv.addObject("plan", createNewRoleForm);
		mv.addObject("memberRole", MemberRole.values()); // MemberRole Enum
		// Values
		return mv;
	}

	@RequestMapping(value = "/createRole", method = RequestMethod.POST)
	public ModelAndView saveNewRoles(
			@ModelAttribute("createNewRole") CreateNewRoleForm createNewRoleForm,
			HttpServletRequest req) {
		ModelAndView mv = new ModelAndView("member_activity");
		commonService.saveNewRole(createNewRoleForm, req.getSession(false));
		mv.addObject("err", Constants.ROLE_CREATED);
		return mv;
	}

	// TODO Expert Recommendation
	@RequestMapping(value = "/expertRecommendation", method = RequestMethod.GET)
	public ModelAndView expertRecomndation(HttpServletRequest req) {
		List<Blog> blogs = commonService.getBlogDetails();
		ModelAndView mv = new ModelAndView();
		mv.addObject("blogDetail", blogs);
		mv.setViewName("expert_recommendation");
		return mv;
	}

	// TODO Blog Create
	@RequestMapping(value = "/blogCreate", method = RequestMethod.GET)
	public ModelAndView blogCreate(HttpServletRequest req) {
		ModelAndView mv = new ModelAndView("blog_create");
		return mv;
	}

	// TODO Blog Content Create
	@RequestMapping(value = "/blogSave", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView blogSave(@ModelAttribute("blogSave") Blog blog,
			HttpServletRequest req) {
		commonService.createblog(blog, req);
		List<Blog> blogs = commonService.getBlogDetails();
		ModelAndView mv = new ModelAndView();
		mv.addObject("blogDetail", blogs);
		mv.setViewName("expert_recommendation");
		return mv;
	}

	@RequestMapping(value = "/customizeDashboard", method = RequestMethod.GET)
	public ModelAndView customizeDashboard(HttpServletRequest req) {
		ModelAndView mv = new ModelAndView("customize_dashboard");
		return mv;
	}

	@RequestMapping(value = "/alertNotification", method = RequestMethod.GET)
	public ModelAndView alertNotification(HttpServletRequest req) {
		ModelAndView mv = new ModelAndView("alert_notification");
		return mv;
	}

	// TODO Calendar
	@RequestMapping(value = "/calendar", method = RequestMethod.GET)
	public ModelAndView calendar(HttpServletRequest req, HttpSession session) {
		ModelAndView mv = new ModelAndView("calendar_page");
		Member member = (Member) session.getAttribute("member");
		Date d = new Timestamp(new Date().getTime());
		for (ScoreBoardCourse scoreBoardMember : commonService
				.getCourseResultByOrganizationId(member.getOrganization()
						.getId())) {
			if ((scoreBoardMember.getStatus()).equalsIgnoreCase("incomplete")) {
				if ((scoreBoardMember.getEndTime()).before(d)) {
					mv.addObject("alert", "Please Completed Before: "
							+ new Date());
				}

			}
		}
		return mv;
	}
}