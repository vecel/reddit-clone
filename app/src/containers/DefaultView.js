import { useState, useEffect } from "react";
import PostsView from "./PostsView";

function DefaultView() {

    const [posts, setPosts] = useState([])

    useEffect(() => {
        fetch('http://localhost:8080/api/posts')
            .then(response => response.json())
            .then(data => setPosts(data))
    }, [])

    return (
        <>
            <PostsView posts={posts}/>
        </>
    );
};

export default DefaultView;
