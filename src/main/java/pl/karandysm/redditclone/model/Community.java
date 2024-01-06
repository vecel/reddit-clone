package pl.karandysm.redditclone.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Communities")
public class Community {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String communityName;
	private String description;
	private LocalDate creationDate = LocalDate.now();

	@OneToMany(mappedBy = "community",
			cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.REFRESH},
			orphanRemoval = true)
	@JsonManagedReference
	private Set<Post> posts;

	@ManyToMany
	@JsonBackReference
	private Set<User> members;

	public Community() {
		super();
	}

	public Community(String communityName, String description) {
		super();
		this.communityName = communityName;
		this.description = description;
	}

	public Community(String communityName, String description, Set<Post> posts, Set<User> members) {
		super();
		this.communityName = communityName;
		this.description = description;
		this.posts = posts;
		this.members = members;
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

	public Set<Post> getPosts() {
		return posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}

	public Set<User> getMembers() {
		return members;
	}

	public void setMembers(Set<User> members) {
		this.members = members;
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

	@Override
	public int hashCode() {
		return Objects.hash(communityName, creationDate, description, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Community other = (Community) obj;
		return Objects.equals(communityName, other.communityName) && Objects.equals(creationDate, other.creationDate)
				&& Objects.equals(description, other.description) && Objects.equals(id, other.id);
	}

}
