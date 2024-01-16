import Post from "../components/Post";

function PostsView({posts, user}) {
    return (
        <div className="posts-container">
            {posts.map(p => <Post key={p.id} author={p.author.username} community={p.community.communityName} creationDate={p.creationDate} title={p.title} content={p.content} user={user}/>)}
        </div>
    );
}

export default PostsView;
