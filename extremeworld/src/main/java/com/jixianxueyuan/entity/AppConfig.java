package com.jixianxueyuan.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_app_config")
public class AppConfig extends IdEntity{

	private Boolean openInvitation;
	private String invitationDesTitle;
	private String invitationDesUrl;
	private String iosAppVersionLimit;

	public Boolean getOpenInvitation() {
		return openInvitation;
	}

	public void setOpenInvitation(Boolean openInvitation) {
		this.openInvitation = openInvitation;
	}

	public String getInvitationDesTitle() {
		return invitationDesTitle;
	}

	public void setInvitationDesTitle(String invitationDesTitle) {
		this.invitationDesTitle = invitationDesTitle;
	}

	public String getInvitationDesUrl() {
		return invitationDesUrl;
	}

	public void setInvitationDesUrl(String invitationDesUrl) {
		this.invitationDesUrl = invitationDesUrl;
	}
	
	public String getIosAppVersionLimit() { return iosAppVersionLimit; }
	public void setIosAppVersionLimit(String iosAppVersionLimit) { this.iosAppVersionLimit = iosAppVersionLimit; }

}
