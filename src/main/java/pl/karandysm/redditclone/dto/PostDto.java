package pl.karandysm.redditclone.dto;

import java.time.LocalDate;

public class PostDto {

    private String title;
    private String content;

    private LocalDate creationTime;

    public PostDto(String title, String content, LocalDate creationTime) {
        this.title = title;
        this.content = content;
        this.creationTime = creationTime;
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

    public LocalDate getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDate creationTime) {
        this.creationTime = creationTime;
    }

    @Override
    public String toString() {
        return "PostDto{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}