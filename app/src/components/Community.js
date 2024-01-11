import { Add } from '@mui/icons-material';

function Community({title, description, membersNumber, postsNumber}) {
    return (
        <div className="community">
                <div className="community__baner"></div>
                <div className="community__logo"></div>
                <div className="community__title">{title}</div>
                <div className="community__description">
                    <p>{description}</p>
                </div>
                <div className="horizontal-line"></div>
                <div className="community__stats">
                    <div className="community__stats-element">Members: {membersNumber}</div>
                    <div className="community__stats-element">Posts: {postsNumber}</div>
                </div>
                <a className="community__button community__button--join community__button--join-hover" href="/register"><Add />Join</a>
                <a className="community__button community__button--join community__button--join-hover"><Add />Join</a>
                <div className="community__button community__button--add-post community__add-post--hover"><Add />Add post</div>
                <div className="horizontal-line"></div>
            </div>
    );
};

export default Community;