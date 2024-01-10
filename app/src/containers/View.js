import { useEffect, useState } from "react";
import CommunityView from "./CommunityView";
import DefaultView from "./DefaultView";

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
                <CommunityView community={community}/> : 
                <DefaultView />}
        </div>
    );
}

export default View;
