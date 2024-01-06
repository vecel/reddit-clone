import CommunityListItem from "./CommunityListItem";

const CommunitiesList = (props) => {

    const communities = props.communities;

    return (
        <div className="sidebar__list">
			<span className="sidebar__list-title">COMMUNITIES</span>
			<div className="sidebar__list-content">
                {communities.map((community) => (
                    <CommunityListItem href={"#"} communityName={community.communityName} />
                ))}
			</div>
		</div>
    );
};

export default CommunitiesList;
