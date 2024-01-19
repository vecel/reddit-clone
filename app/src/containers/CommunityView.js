import '../styles/CommunityView.css';
import Community from '../components/Community';
import PostsView from './PostsView';
import { useEffect, useState } from 'react';

function CommunityView({id, loggedUser}) {

    const [community, setCommunity] = useState(null)
    const [posts, setPosts] = useState(null)
    const [members, setMembers] = useState(null)

    const fetchCommunityMembers = (communityId) => {
        fetch('http://localhost:8080/api/community/' + communityId + '/members')
            .then(response => response.json())
            .then(data => setMembers(data))
    }

    const handleCommunityJoinClick = (communityId, userId) => {
        fetch('http://localhost:8080/api/community/' + communityId + '/join/' + userId, {
              method: 'POST',
              credentials: 'include'
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Can\'t join community')
            }
        })

        fetchCommunityMembers(communityId)
      }    

    useEffect(() => {
        const prefix = 'http://localhost:8080/api/community/'
        fetch(prefix + id)
            .then(response => response.json())
            .then(data => setCommunity(data))

        fetch(prefix + id + '/posts')
            .then(response => response.json())
            .then(data => setPosts(data))

        fetchCommunityMembers(id)
    }, [id])

    return (
        (community && posts && members) ?
        <>
            <Community community={community} members={members} posts={posts} user={loggedUser} handleCommunityJoinClick={handleCommunityJoinClick}/>
            <PostsView posts={posts} user={loggedUser}/>
        </> :
        <>
            <div>Loading</div>
        </>
    );
};

export default CommunityView;
