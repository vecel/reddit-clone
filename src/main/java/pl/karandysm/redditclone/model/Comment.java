package pl.karandysm.redditclone.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "COMMENTS")
public class Comment {

	@Id
	@GeneratedValue
	private Long id;

	private Long postId;
	private Long authorId;
	private String content;
	private LocalDate creationDate;

	public Comment(Long postId, Long authorId, String content) {
		super();
		this.postId = postId;
		this.authorId = authorId;
		this.content = content;
		this.creationDate = LocalDate.now();
	}

}
