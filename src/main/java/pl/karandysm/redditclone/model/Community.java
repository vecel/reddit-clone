package pl.karandysm.redditclone.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "COMMUNITIES")
public class Community {

	@Id
	@GeneratedValue
	private Long id;

	private String communityName;
	private List<Long> postIds;
	private List<Long> memberIds;
	private String description;
	private LocalDate creationDate;

	public Community(String communityName, String description) {
		super();
		this.communityName = communityName;
		this.description = description;
		this.creationDate = LocalDate.now();
	}
	
	public Community(String communityName, String description, List<Long> memberIds) {
		super();
		this.communityName = communityName;
		this.description = description;
		this.memberIds = memberIds;
		this.creationDate = LocalDate.now();
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCommunityName() {
		return communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	public List<Long> getPostIds() {
		return postIds;
	}

	public void setPostIds(List<Long> postIds) {
		this.postIds = postIds;
	}

	public List<Long> getMemberIds() {
		return memberIds;
	}

	public void setMemberIds(List<Long> memberIds) {
		this.memberIds = memberIds;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

}
