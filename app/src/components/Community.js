import { Add } from '@mui/icons-material';
import { useEffect, useState } from 'react';

function Community({community, members, posts, user, handleCommunityJoinClick, handleAddPostClick}) {

    const [joined, setJoined] = useState(false)

    const onCommunityJoinClick = () => {
        handleCommunityJoinClick(community.id, user.id)
    }

    useEffect(() => {
        if (user === null) {
            setJoined(true)
        }
        if (user !== null) {
            const isMember = members.some(member => member.id === user.id)
            setJoined(isMember)
        }
    }, [user, members])

    return (
        <div className="community">
                <div className="community__baner"></div>
                <div className="community__logo"></div>
                <div className="community__title">{community.title}</div>
                <div className="community__description">
                    <p>{community.description}</p>
                </div>
                <div className="horizontal-line"></div>
                <div className="community__stats">
                    <div className="community__stats-element">Members: {members.length}</div>
                    <div className="community__stats-element">Posts: {posts.length}</div>
                </div>
                {
                    !joined &&
                    <a className="community__button community__button--join community__button--join-hover" onClick={onCommunityJoinClick}><Add />Join</a>
                }
                {
                    (user !== null) &&
                    <div className="community__button community__button--add-post community__button--add-post-hover" onClick={handleAddPostClick}><Add />Add post</div>
                }
                <div className="horizontal-line"></div>
            </div>
    );
};

export default Community;
