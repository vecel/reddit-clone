import { ThumbDownAlt, ThumbUpAlt, QuestionAnswer } from "@mui/icons-material";
import '../styles/Post.css';

function Post({author, creationDate, title, content}) {
    return (
        <div className="post">
            <div className="post__header-short">
                <span className="post__header-text">Podmienic to</span>
                <span className="post__header-text post__header-text--end">{creationDate}</span>
            </div>
            <span className="post__title post__title--hover">{title}</span>
            <div className="post__content">{content}</div>
            <div className="post__stats">
                <div className="post__stats-element">
                    <ThumbUpAlt />
                    <span> -1</span>
                </div>
                <div className="post__stats-element">
                    <ThumbDownAlt />
                    <span> -1</span>
                </div>
                <div className="post__stats-element">
                    <QuestionAnswer />
                    <span> -1</span>
                </div>
            </div>
        </div>
    );
};

export default Post;
