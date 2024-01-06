import CommunityView from "./CommunityView";
import DefaultView from "./DefaultView";

function View({community}) {
    return (
        <div className="view">
            {community !== null ? <CommunityView community={community}/> : <DefaultView />}
        </div>
    );
}

export default View;
