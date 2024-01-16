import { Add } from '@mui/icons-material';
import { useEffect, useState } from 'react';

function Community({community, members, posts, user}) {

    const [joined, setJoined] = useState(false)

    useEffect(() => {
        if (user !== null) {
            const isMember = members.some(member => member.id === user.id)
            setJoined(isMember)
        }
        console.log('Community view refreshed')
    }, [members, user, joined])

    function handleClick() {
        fetch('http://localhost:8080/api/community/' + community.id + '/join/' + user.id, {
            method: 'POST',
            credentials: 'include'
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Can\'t join community')
            }
            setJoined(true)
            console.log('Joined')
        })
    }

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
                    (!joined) &&
                    <a className="community__button community__button--join community__button--join-hover" onClick={handleClick}><Add />Join</a>
                }
                <div className="community__button community__button--add-post community__add-post--hover"><Add />Add post</div>
                <div className="horizontal-line"></div>
            </div>
    );
};

export default Community;
