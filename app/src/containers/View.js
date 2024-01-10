import { useEffect, useState } from "react";
import CommunityView from "./CommunityView";
import DefaultView from "./DefaultView";
import PostsView from "./PostsView";

function View({communityId}) {

    const [community, setCommunity] = useState(null)

    useEffect(() => {
        if (communityId !== -1) {
            fetch('http://localhost:8080/api/community/' + communityId)
              .then(response => response.json())
              .then(data => setCommunity(data))
        }    
    }, [communityId])

    return (
        <div className="view">
            {community !== null ? 
                <>
                <CommunityView community={community}/> 
                <PostsView posts={community.posts}/>
                </>: 
                <DefaultView />}
        </div>
    );
}

export default View;
