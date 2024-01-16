import { useEffect, useState } from "react";
import CommunityView from "./CommunityView";
import DefaultView from "./DefaultView";

function View({communityId, loggedUser}) {

    return (
        <div className="view">
            {communityId !== -1 ? 
                <CommunityView id={communityId} loggedUser={loggedUser}/> : 
                <DefaultView />}
        </div>
    );
}

export default View;
