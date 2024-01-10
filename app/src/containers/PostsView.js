import Post from "../components/Post";

function PostsView({posts}) {
    return (
        <div className="posts-container">
            {posts.map(p => <Post key={p.id} creationDate={p.creationDate} title={p.title} content={p.content}/>)}
        </div>
    );
}

export default PostsView;
