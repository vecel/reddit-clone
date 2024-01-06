import CommunityListItem from "./CommunityListItem";

const CommunitiesList = ({communities, onCommunityClick}) => {

    return (
        <div className="sidebar__list">
			<span className="sidebar__list-title">COMMUNITIES</span>
			<div className="sidebar__list-content">
                {communities.map((community) => (
                    <CommunityListItem id={community.id} title={community.communityName} onCommunityClick={onCommunityClick}/>
                ))}
			</div>
		</div>
    );
};

export default CommunitiesList;
