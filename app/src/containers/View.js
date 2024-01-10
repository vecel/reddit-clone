import { useEffect, useState } from "react";
import CommunityView from "./CommunityView";
import DefaultView from "./DefaultView";

function View({communityId}) {

    return (
        <div className="view">
            {communityId !== -1 ? 
                <>
                <CommunityView id={communityId}/> 
                </>: 
                <DefaultView />}
        </div>
    );
}

export default View;
