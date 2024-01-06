const CommunityListItem = (props) => {
    return (
        <a className="sidebar__list-item sidebar__list-item--hover" href={props.href}>
            <div className="sidebar__list-item-logo"></div>
            <span className="sidebar__list-item-text">{props.communityName}</span>
        </a>
    );
};

export default CommunityListItem;
