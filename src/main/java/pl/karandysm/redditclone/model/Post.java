package pl.karandysm.redditclone.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "POSTS")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
	private String content;
	private Long communityId;
	private Long authorId;
	private LocalDate creationDate;
	
	@ManyToOne
	@JoinColumn(name = "communityId", insertable = false, updatable = false)
	private Community community;
	
	@ManyToOne
	@JoinColumn(name = "authorId", insertable = false, updatable = false)
	private User author;
	
	private List<Long> upvoterIds = new ArrayList<>();
	private List<Long> downvoterIds = new ArrayList<>();
	private List<Long> commentIds = new ArrayList<>();
	
	public Post() {
	
	}

	public Post(String title, String content, Long communityId, Long authorId) {
		super();
		this.title = title;
		this.content = content;
		this.communityId = communityId;
		this.authorId = authorId;
		this.creationDate = LocalDate.now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getCommunityId() {
		return communityId;
	}

	public void setCommunityId(Long communityId) {
		this.communityId = communityId;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public List<Long> getUpvoterIds() {
		return upvoterIds;
	}

	public void setUpvoterIds(List<Long> upvoterIds) {
		this.upvoterIds = upvoterIds;
	}

	public List<Long> getDownvoterIds() {
		return downvoterIds;
	}

	public void setDownvoterIds(List<Long> downvoterIds) {
		this.downvoterIds = downvoterIds;
	}

	public List<Long> getCommentIds() {
		return commentIds;
	}

	public void setCommentIds(List<Long> commentIds) {
		this.commentIds = commentIds;
	}

	public Community getCommunity() {
		return community;
	}

	public void setCommunity(Community community) {
		this.community = community;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	
}
