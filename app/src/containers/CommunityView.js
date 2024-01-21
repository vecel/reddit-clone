import '../styles/CommunityView.css';
import Community from '../components/Community';
import PostsView from './PostsView';
import PostEdit from '../components/PostEdit';
import { useEffect, useState } from 'react';

function CommunityView({id, loggedUser}) {

    const [community, setCommunity] = useState(null)
    const [posts, setPosts] = useState(null)
    const [members, setMembers] = useState(null)

    const [postEdit, setPostEdit] = useState(false)

    const fetchCommunityMembers = (communityId) => {
        fetch('http://localhost:8080/api/community/' + communityId + '/members')
            .then(response => response.json())
            .then(data => setMembers(data))
    }

    const fetchCommunityPosts = (communityId) => {
        fetch('http://localhost:8080/api/community/' + communityId + '/posts')
            .then(response => response.json())
            .then(data => setPosts(data))
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

    const handleAddPostClick = () => {
        setPostEdit(true)
    }

    const handlePostEditCancelClick = () => {
        setPostEdit(false)
    }

    const handlePostEditSubmitClick = (post) => {
        fetch('http://localhost:8080/api/post/add/' + id + '/' + loggedUser.id, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            credentials: 'include',
            body: JSON.stringify(post)
        })
        .then(response => {
            if (response.ok) {
                fetchCommunityPosts(id)
            } else {
                throw new Error('Can\'t add post')
            }
        })
        setPostEdit(false)
    }

    useEffect(() => {
        const prefix = 'http://localhost:8080/api/community/'
        fetch(prefix + id)
            .then(response => response.json())
            .then(data => setCommunity(data))

        fetchCommunityPosts(id)
        fetchCommunityMembers(id)
    }, [id])

    return (
        (community && posts && members) ?
        <>
            <Community community={community} members={members} posts={posts} user={loggedUser} handleCommunityJoinClick={handleCommunityJoinClick} handleAddPostClick={handleAddPostClick}/>
            {postEdit && <PostEdit handleSubmitClick={handlePostEditSubmitClick} handleCancelClick={handlePostEditCancelClick}/> }
            <PostsView posts={posts} user={loggedUser}/>
        </> :
        <>
            <div>Loading</div>
        </>
    );
};

export default CommunityView;
