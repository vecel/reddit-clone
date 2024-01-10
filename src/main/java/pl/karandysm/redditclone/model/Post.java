package pl.karandysm.redditclone.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Posts")
public class
Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
	private String content;
	private LocalDate creationDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	private Community community;

	@ManyToOne
	@JsonBackReference
	private User author;

	// ponizsze do zmiany
	private List<Long> upvoterIds = new ArrayList<>();
	private List<Long> downvoterIds = new ArrayList<>();
	private List<Long> commentIds = new ArrayList<>();

	public Post() {

	}

	public Post(String title, String content, Community community, User author) {
		super();
		this.title = title;
		this.content = content;
		this.community = community;
		this.author = author;
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

	public Community getCommunity() {
		return community;
	}

	public void setCommunity(Community community) {
		this.community = community;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
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

	@Override
	public int hashCode() {
		return Objects.hash(community, content, creationDate, id, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		return Objects.equals(community, other.community) && Objects.equals(content, other.content)
				&& Objects.equals(creationDate, other.creationDate) && Objects.equals(id, other.id)
				&& Objects.equals(title, other.title);
	}

}
