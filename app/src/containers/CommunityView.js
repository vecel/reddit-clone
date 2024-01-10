import '../styles/CommunityView.css';
import Community from '../components/Community';
import PostsView from './PostsView';
import { useEffect, useState } from 'react';

function CommunityView({id}) {

    const [community, setCommunity] = useState(null)
    const [posts, setPosts] = useState(null)
    const [members, setMembers] = useState(null)

    useEffect(() => {
        const prefix = 'http://localhost:8080/api/community/'
        fetch(prefix + id)
            .then(response => response.json())
            .then(data => setCommunity(data))

        fetch(prefix + id + '/posts')
            .then(response => response.json())
            .then(data => setPosts(data))

        fetch(prefix + id + '/members')
            .then(response => response.json())
            .then(data => setMembers(data))
    }, [id])

    return (
        (community && posts && members) ?
        <>
            <Community title={community.communityName} description={community.description} membersNumber={members.length} postsNumber={posts.length}/>
            <PostsView posts={posts}/>
        </> :
        <>
            <div>Loading</div>
        </>
    );
};

export default CommunityView;
